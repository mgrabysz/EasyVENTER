package kowale.userInterface;
import kowale.database.GlobalVariables;
// import kowale.ticket.Ticket;
import kowale.database.EasyVENT;

import java.util.HashMap;

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

    // private int numOfSectors;
    private String[][] tableData;
    // private HashMap<String, HashMap<String, Integer>> tickets;


    public InputSectorDataFrame(String[][] data) {
        super(data, new String[]{"Number of sector", "Number of seats", "Ticket price"} , "Confirm", true);
        // numOfSectors = table.getRowCount();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==actionButton){
            String[][] tableData = getTableData();
            // something to do with tableData
            // System.out.println(tableData[0][1]);

            System.out.println(tableData[0][0]);
            System.out.println(tableData[0][1]);
            System.out.println(tableData[0][2]);

            HashMap<String, HashMap<String, Integer>> tickets = new HashMap<String, HashMap<String, Integer>>();

            TableModel dtm = table.getModel();
            int nRow = dtm.getRowCount();
            for (int row = 0 ; row < nRow ; row++) {
                HashMap<String, Integer> numberPrice = new HashMap<String, Integer>();
                int number = Integer.parseInt(tableData[row][1]);
                int price = Integer.parseInt(tableData[row][2]);
                numberPrice.put("Number", number);
                numberPrice.put("Price", price);
                // System.out.println(numberPrice);

                tickets.put(tableData[row][0], numberPrice);
            }

            System.out.println(tickets);
            GlobalVariables.EVENT.setTickets(tickets);
            EasyVENT.database.createEvent();
        } else if (event.getSource()==cancelButton) {
            // something to do when cancelled
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