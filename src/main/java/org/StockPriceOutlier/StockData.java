package org.StockPriceOutlier;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StockData {
    private String stockId;
    private LocalDate timestamp;
    private double price;

    public StockData(String stockId, String timestamp, double price) {
        this.stockId = stockId;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.timestamp = LocalDate.parse(timestamp, formatter);
        this.price = price;
    }

    public String getStockId() {
        return stockId;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return stockId + "," + timestamp + "," + price;
    }
}
