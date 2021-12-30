package kowale.userInterface;

import kowale.database.StringConstant;

import java.awt.event.ActionEvent;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JButton;

public class BrowseEventsOrganizerFrame extends BasicTableFrame {
    /*
    Frame to browse events from organizer perspective.
    Organizer has option to modify or remove his events.
    Constructor takes two parameters:
    :data: String[][]
        table of data about events
    :columnNames: String[]
        names of the columns of the table (probably: "Name", "Location", "Date and time")
        organizer is kinda obvious
    */

    JButton removeButton;

    public BrowseEventsOrganizerFrame(String[][] data, String[] columnNames) {
        super(data, columnNames, "Modify");

        removeButton = new JButton("Remove");
        removeButton.setFocusable(false);
        removeButton.setBounds(850, 100, 120, 50);
        removeButton.addActionListener(this);
        this.add(removeButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==actionButton){
            int index = table.getSelectedRow(); // returns index of selected row

            // ============ for testing ==================
            System.out.println("Modify button clicked. Index of selected row:");
            System.out.println(index);
            // ===========================================

        } else if (event.getSource()==cancelButton) {
            // some code if cancel
            System.out.println("Cancel button clicked");
        } else if (event.getSource()==removeButton) {
            // some code if remove
            int index = table.getSelectedRow(); // returns index of selected row
            System.out.println("Remove button clicked");
            System.out.println(index);
        }
    }
}
