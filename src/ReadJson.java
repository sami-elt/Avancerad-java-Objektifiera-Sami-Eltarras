import com.eclipsesource.json.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadJson {

    DefaultTableModel tableModel;
    ArrayList<String> arrJson = new ArrayList<String>();
    ArrayList<ArrayList<String>> dataList = new ArrayList<>();

    //konstruktor med parametrar för fil och table
    ReadJson(String filePaths, DefaultTableModel tableModel){
        this.tableModel = tableModel;

        //resettar varje gång man väljer fil
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        try {
            //går igenom varje rad för fil och sparar i String page.
            Scanner sc = new Scanner(new File(filePaths));
            String page = "";
            while (sc.hasNext()){
                String line = sc.nextLine();
                page+= line;
            }
            sc.close();

            //parsa igenom page
            JsonValue jv = Json.parse(page);

            //Skapar array
            JsonArray ja = jv.asArray();

            //hämtar column namnen från jsonfil
            JsonObject jo = ja.get(0).asObject();
            arrJson.addAll(jo.names());


            for (int i = 0; i < ja.size(); i++) {
                JsonObject col = ja.get(i).asObject();

                //iterera igenom min array för att hämta värde för varje column för att kunna lägga in i min data arraylist.
                ArrayList<String> rowsfill = new ArrayList<>();
                for (int j = 0; j < arrJson.size(); j++) {
                    /* Adds the data in record */
                    rowsfill.add(String.valueOf(col.get(arrJson.get(j))));
                }
                dataList.add(new ArrayList<>(rowsfill));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // lägger in data in i table på samma vis som ReadCsv med for each för row och column.
        for (String column : arrJson){
            tableModel.addColumn(column);
        }
        for (ArrayList<String> row : dataList) {
            tableModel.addRow(row.toArray());
        }
    }
}
