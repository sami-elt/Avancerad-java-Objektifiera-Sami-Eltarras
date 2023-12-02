import com.eclipsesource.json.*;


import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadJson {

    DefaultTableModel tableModel;

    ReadJson(String filePaths, DefaultTableModel tableModel){
        this.tableModel = tableModel;

        //resettar varje gång man väljer fil
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);


        try {
            Scanner sc = new Scanner(new File(filePaths));
            String page = "";
            while (sc.hasNext()){
                String line = sc.nextLine();
                page+= line;
            }
            sc.close();

            JsonValue jv = Json.parse(page);

            JsonArray ja = jv.asArray();

            JsonObject jo = ja.get(0).asObject();

            for (int i = 0; i < ja.size()-1; i++){
                JsonObject j = ja.get(i).asObject();
            }





        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
