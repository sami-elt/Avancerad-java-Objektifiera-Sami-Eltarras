import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCsv {
    DefaultTableModel tableModel;
    ArrayList<String[]> arrCsv = new ArrayList<>();

    //konstruktor med parametrar för table
    ReadCsv(String filePaths, DefaultTableModel tableModel) {
        this.tableModel = tableModel;

        //resettar varje gång man väljer ny fil så det alltid är tomt
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        try {
            Scanner sc = new Scanner(new File(filePaths));

            //skapa header för table
            if (sc.hasNext()){
                String line = sc.nextLine();
                String[] headers = line.split(",");
                for (String header : headers){
                    tableModel.addColumn(header);
                }
            }
            //läser filen och sparar det i min arr
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] value = line.split(",");
                arrCsv.add(value);
            }
            sc.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //använder addRow metoden för att få rätt antal rader hämtat från datan i min arraylist (arr)
        for (String[] row : arrCsv) {
            tableModel.addRow(row);
        }
    }
}
