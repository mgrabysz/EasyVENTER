package kowale.userInterface;

import java.awt.event.ActionEvent;

public class ViewEventsFrame extends BasicTableFrame {
    /*
    Frame to browse available events from client's perspective.
    Constructor takes parameter:
    :data: String[][]
        table of data about events
    */

    public ViewEventsFrame(String[][] data) {
        super(data, new String[]{"Name", "City", "Address", "Date and time"}, "Details", false);
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

            option = "details";

        } else if (event.getSource()==cancelButton) {
            option = "cancel";
        }
    }


}
