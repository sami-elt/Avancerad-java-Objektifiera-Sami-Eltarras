import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame implements ActionListener {

    JFrame frame;
    JTable table;
    DefaultTableModel tableModel;

    GUI() {

        //sätta upp frame
        frame = new JFrame();
        frame.setSize(500, 500);

        //sätta upp table och defaulttablemodel med sortering
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        //en enkel knapp för att komma åt filechooser
        JButton button = new JButton("File");
        button.addActionListener(this);
        frame.add(button, BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // när man trycker på knappen startar man filechooser med genväg till src
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File("src"));

        if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            //för att få upp rätt fil i min table
           String filePaths = file.getSelectedFile().getPath();

           //om det slutar på csv så öppnar vi readcsv annars json
           if (filePaths.toLowerCase().endsWith(".csv")){
               //hämtar filepath och lägger in allt från min readcsv metod så allt kommer in i table
               new ReadCsv(filePaths, tableModel);
           } else if (filePaths.toLowerCase().endsWith(".json")){
               new ReadJson(filePaths, tableModel);
           }

        }
    }
}

