package kowale.userInterface;

import kowale.database.StringConstant;

import java.awt.event.ActionEvent;

public class ViewEventsFrame extends BasicTableFrame {

    public ViewEventsFrame(String[][] data, String[] columnNames) {
        super(data, columnNames, "Buy");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==actionButton){
            StringConstant.FRAME_TYPE = "buy";
            int index = table.getSelectedRow(); // returns index of selected row

            // ============ for testing ==================
            System.out.println("Buy button clicked. Index of selected row:");
            System.out.println(index);
            // ===========================================

        } else if (event.getSource()==cancelButton) {
            StringConstant.FRAME_TYPE = "cancel";
        }
    }
}
