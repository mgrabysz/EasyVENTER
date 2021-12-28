package kowale.userInterface;

import kowale.database.StringConstant;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Color;

public class ViewEventsFrame extends JFrame implements ActionListener {

    Border border;
    JButton buyButton, cancelButton;
    JTable table;
    JScrollPane sp;

    public ViewEventsFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(1000, 600));
        this.setLocationRelativeTo(null);

        border = BorderFactory.createLineBorder(new Color(0x004169E1), 3);

        String[][] data = {
            { "Meczyk jakiś", "Firma Krzak", "Bydgoszcz", "35.19.2022 25:72" },
            { "Ludzie biegający w kółko", "Google", "Warszafka", "48.17.2023 29:81" }
        };
        String[] columnNames = {"Name", "Organizer", "Location", "Date and time"};

        table = new JTable(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column){
                // prevents from editing cells
                return false;
            }
        };

        // prevents from selecting multiple rows
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // prevents from column dragging
        table.getTableHeader().setReorderingAllowed(false);

        sp = new JScrollPane(table);
        sp.setBounds(30, 30, 800, 500);
        this.add(sp);


        buyButton = new JButton();
        buyButton.setText("Buy");
        buyButton.setFocusable(false);
        buyButton.setBounds(850, 200, 120, 50);
        buyButton.addActionListener(this);
        this.add(buyButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setBounds(850, 300, 120, 50);
        cancelButton.addActionListener(this);
        this.add(cancelButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==buyButton){
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
