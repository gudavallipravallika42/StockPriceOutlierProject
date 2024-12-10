package org.StockPriceOutlier;

public class Outlier {
    private String stockId;
    private String timestamp;
    private double actualPrice;
    private double mean;
    private double deviation;
    private double percentageDeviation;

    public Outlier(String stockId, String timestamp, double actualPrice, double mean, double deviation, double percentageDeviation) {
        this.stockId = stockId;
        this.timestamp = timestamp;
        this.actualPrice = actualPrice;
        this.mean = mean;
        this.deviation = deviation;
        this.percentageDeviation = percentageDeviation;
    }

    public String toCSVString() {
        return stockId + "," + timestamp + "," + actualPrice + "," + mean + "," + deviation + "," + percentageDeviation;
    }
}
