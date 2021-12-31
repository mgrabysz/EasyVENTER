package kowale.userInterface;

import java.awt.event.ActionEvent;

import javax.swing.table.TableModel;

// Przykładowe parametry dla konstruktora:
// int number = 10;   // dowolna liczba sektorów
// String[][] sectors = new String[number][3];

// for (int i=0; i<number; ++i) {
//     String iStr = String.valueOf(i+1);
//     String[] sector = {iStr, "0", "0", "0"};
//     sectors[i] = sector;
// }




public class InputSectorDataFrame extends BasicTableFrame {
    /*
    Frame to set number of seats and ticket price of each sector
    Constructor takes parameter:
    :data: String[][]
        table of data about sectors
    */

    private int numOfSectors;
    private String[][] tableData;


    public InputSectorDataFrame(String[][] data) {
        super(data, new String[]{"Number of sector", "Number of seats", "Ticket price"} , "Confirm", true);
        numOfSectors = table.getRowCount();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==actionButton){
            String[][] tableData = getTableData();
            // something to do with tableData
            System.out.println(tableData[0][1]);
        } else if (event.getSource()==cancelButton) {
            // something to do whel cancelled
        }
    }

    public String[][] getTableData () {
        TableModel dtm = table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        tableData = new String[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++)
                tableData[i][j] = (String)dtm.getValueAt(i,j);
        return tableData;
    }


}