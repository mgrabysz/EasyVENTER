package kowale.userInterface;
import java.awt.event.ActionEvent;

import javax.swing.table.TableModel;


public class InputSectorDataFrame extends BasicTableFrame {
    /*
    Frame to set number of seats and ticket price of each sector
    Constructor takes parameter:
    :data: String[][]
        table of data about sectors
    */

    private String[][] tableData;

    public InputSectorDataFrame(String[][] data) {
        super(data, new String[]{"Sector Name", "Number of seats", "Ticket price"} , "Confirm", true);
        this.setTitle("Enter number of seats and price of normal ticket");

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==actionButton){

            tableData = readTableData();
            option = "confirm";

        } else if (event.getSource()==cancelButton) {
            option = "cancel";
        }
    }

    public String[][] readTableData () {
        TableModel dtm = table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        tableData = new String[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++)
                tableData[i][j] = (String)dtm.getValueAt(i,j);
        return tableData;
    }

    public String[][] getTableData() {
        return tableData;
    }
}