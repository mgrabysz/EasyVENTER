package kowale.userInterface;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class ViewEventsOrganizerFrame extends BasicTableFrame {
    /*
    Frame to browse events from organizer perspective.
    Organizer has option to modify or remove his events.
    Constructor takes parameter:
    :data: String[][]
        table of data about events
    */

    JButton removeButton;
    private String option = "";
    private int index = -1;

    public ViewEventsOrganizerFrame(String[][] data) {
        super(data, new String[]{"Name", "City", "Address", "Date and time"}, "Modify", false);

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
            option = "modify";
            this.index = index;
        } else if (event.getSource()==cancelButton) {
            // some code if cancel
            System.out.println("Cancel button clicked");
            option = "cancel";
        } else if (event.getSource()==removeButton) {
            // some code if remove
            int index = table.getSelectedRow(); // returns index of selected row
            System.out.println("Remove button clicked");
            System.out.println(index);
            option = "remove";
            this.index = index;
        }
    }

    public String getOption() {
        return option;
    }

    public int getIndex() {
        return index;
    }
}
