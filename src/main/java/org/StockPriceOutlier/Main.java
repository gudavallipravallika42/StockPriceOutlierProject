package org.StockPriceOutlier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/input_data/ASH.csv";
        List<StockData> stockDataList = StockFileReader.readCSV(filePath);

        List<StockData> randomSample = StockDataProcessor.getRandomSample(stockDataList);

        double mean = randomSample.stream().mapToDouble(StockData::getPrice).average().orElse(0.0);
        double stdDev = StockDataProcessor.calculateStandardDeviation(randomSample, mean);

        List<Outlier> outliers = StockDataProcessor.findOutliers(randomSample, mean, stdDev);

        writeOutliersToCSV(randomSample, mean, stdDev, outliers, "output_outliers.csv");
    }

    private static void writeOutliersToCSV(List<StockData> randomSample, double mean, double stdDev,
                                           List<Outlier> outliers, String outputFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write("Stock-ID,Timestamp,Actual Stock Price,Mean of 30 Data Points,Deviation,Percentage Deviation\n");

            for (StockData data : randomSample) {
                double deviation = data.getPrice() - mean;
                double percentageDeviation = Math.abs(deviation) / mean * 100;

                writer.write(data.getStockId() + "," + data.getTimestamp() + "," + data.getPrice() + "," +
                        mean + "," + deviation + "," + percentageDeviation + "\n");
            }

            for (Outlier outlier : outliers) {
                writer.write(outlier.toCSVString() + "\n");
            }
        }
    }
}