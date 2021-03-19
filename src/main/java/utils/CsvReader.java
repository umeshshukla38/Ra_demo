package utils;
import org.slf4j.Logger;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.LoggerFactory;

import common.GlobalPath;

public class CsvReader{

    private static Logger log = LoggerFactory.getLogger(CsvReader.class);

    public static Map<Integer, ArrayList<String>> readCsv(String file) {
        int row_count = 1;
        Map<Integer, ArrayList<String>> csv_data = new HashMap<>();
        try {
            FileReader reader = new FileReader(GlobalPath.CSV_PATH+"/"+file);
            CSVReader csvReader = new CSVReaderBuilder(reader).build();
            List<String[]> allData = csvReader.readAll();

            for(String[] row : allData) {
                int count = 0;
                ArrayList<String> col_data = new ArrayList<>();
                for (String cell : row) {
                    String value = cell;
                    col_data.add(value);
                    count++;
                }
                if(count == row.length){
                    csv_data.put(row_count, col_data);
                    row_count++;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return csv_data;
    }

    public static void main(String[] args) {
        log.info("==> "+readCsv(""));
    }
}