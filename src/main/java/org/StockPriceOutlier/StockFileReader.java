package org.StockPriceOutlier;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockFileReader {
    public static List<StockData> readCSV(String filePath) throws IOException {
        List<StockData> stockDataList = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("Stock-ID", "Timestamp", "Stock Price")
                    .withSkipHeaderRecord(true)
                    .parse(reader);

            for (CSVRecord record : records) {
                String stockId = record.get("Stock-ID");
                String timestamp = record.get("Timestamp");
                double price = Double.parseDouble(record.get("Stock Price"));
                stockDataList.add(new StockData(stockId, timestamp, price));
            }
        }

        return stockDataList;
    }
}
