package org.StockPriceOutlier;

import java.util.*;

public class StockDataProcessor {
    public static List<StockData> getRandomSample(List<StockData> stockDataList) {
        Random rand = new Random();
        int startIndex = rand.nextInt(stockDataList.size() - 30);
        return stockDataList.subList(startIndex, startIndex + 30);
    }

    public static List<Outlier> findOutliers(List<StockData> sample, double mean, double stdDev) {
        List<Outlier> outliers = new ArrayList<>();
        for (StockData data : sample) {
            double deviation = data.getPrice() - mean;
            double percentageDeviation = Math.abs(deviation) / mean * 100;

            if (Math.abs(deviation) > 2 * stdDev) {
                outliers.add(new Outlier(data.getStockId(), data.getTimestamp().toString(), data.getPrice(), mean, deviation, percentageDeviation));
            }
        }
        return outliers;
    }

    public static double calculateStandardDeviation(List<StockData> sample, double mean) {
        double sumSquaredDifferences = sample.stream()
                .mapToDouble(data -> Math.pow(data.getPrice() - mean, 2))
                .sum();
        return Math.sqrt(sumSquaredDifferences / sample.size());
    }
}
