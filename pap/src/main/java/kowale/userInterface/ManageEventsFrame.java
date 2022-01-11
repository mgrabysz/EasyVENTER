package kowale.userInterface;

import java.awt.event.ActionEvent;

public class ManageEventsFrame extends BasicTableFrame {
    /*
    Frame to browse events from organizer perspective.
    Organizer has option to modify or remove his events.
    Constructor takes parameter:
    :data: String[][]
        table of data about events
    */

    public ManageEventsFrame(String[][] data) {
        super(data, new String[]{"Name", "City", "Address", "Date and time"}, "Modify", false);
        this.setTitle("Manage your events");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==actionButton){
            int index = table.getSelectedRow(); // returns index of selected row
            selectedIndex = index;

            option = "modify";

        } else if (event.getSource()==cancelButton) {

            option = "cancel";
        }
    }

}
