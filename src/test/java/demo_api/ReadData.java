package demo_api;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import utils.CsvReader;
import utils.Helper;

public class ReadData {

    static String date_limit = "2019-05-09 23:59:59";
    static String file_name = "Bold.csv";
    Helper helper = new Helper();
    private static Logger log = LoggerFactory.getLogger(ReadData.class);

    @Test(enabled = true, priority = 1)
    public void readDataWithConditions() throws ParseException{
        int nameIndex = 1;  // As we already know so put it static 
        int timeIndex = 3;  // As we already know so put it static
        Date check_date_limit = helper.stringToDate(date_limit);
        ArrayList<String> headers = null;
        ArrayList<String> ex_names = new ArrayList<>();
        Map<Integer, ArrayList<String>> csvData = CsvReader.readCsv(file_name);
        for(Integer index : csvData.keySet()){
            if(index == 1){
                headers = csvData.get(index);
            }else{
                ArrayList<String> data = csvData.get(index);
                if(data.size() == headers.size()){
                    String name = data.get(nameIndex);
                    String time = data.get(timeIndex);
                    Date date = helper.stringToDate(time);
                    if(date.after(check_date_limit)){
                        ex_names.add(name);
                    }
                }   
            }
        }

        log.info("After "+check_date_limit+ "added users name : "+ex_names.toString());
    }
}