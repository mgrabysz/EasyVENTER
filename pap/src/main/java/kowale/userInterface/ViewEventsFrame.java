package kowale.userInterface;

// import kowale.database.GlobalVariables;

import java.awt.event.ActionEvent;

public class ViewEventsFrame extends BasicTableFrame {
    /*
    Frame to browse available events from client's perspective.
    Constructor takes parameter:
    :data: String[][]
        table of data about events
    */

    public ViewEventsFrame(String[][] data) {
        super(data, new String[]{"Name", "Oity", "Address", "Date and time"}, "Details", false);
        this.setTitle("Browse events");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==actionButton){
            selectedIndex = table.getSelectedRow(); // returns index of selected row

            if (selectedIndex < 0)
            {
                selectedIndex = 0;
            }

            // System.out.println(selectedIndex);

            // ============ for testing ==================
            System.out.println("Details button clicked. Index of selected row:");
            System.out.println(selectedIndex);
            // ===========================================

            // isReady = true;
            option = "details";

        } else if (event.getSource()==cancelButton) {
            option = "cancel";
        }
    }


}
