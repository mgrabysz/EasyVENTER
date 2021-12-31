package kowale.userInterface;

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

public class BasicTableFrame extends JFrame implements ActionListener {

    Border border;
    JButton actionButton, cancelButton;
    JTable table;
    JScrollPane sp;
    boolean isEditable;

    public BasicTableFrame(String[][] data, String[] columnNames, String actionButtonText, boolean isTableEditable) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(1000, 600));
        this.setLocationRelativeTo(null);

        isEditable = isTableEditable;

        border = BorderFactory.createLineBorder(new Color(0x004169E1), 3);

        actionButton = new JButton(actionButtonText);
        actionButton.setFocusable(false);
        actionButton.setBounds(850, 200, 120, 50);
        actionButton.addActionListener(this);
        this.add(actionButton);

        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setBounds(850, 300, 120, 50);
        cancelButton.addActionListener(this);
        this.add(cancelButton);

        table = new JTable(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column){
                // provides from editing cells
                return isEditable;
            }
        };

        // prevents from selecting multiple rows
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // prevents from column dragging
        table.getTableHeader().setReorderingAllowed(false);

        sp = new JScrollPane(table);
        sp.setBounds(30, 30, 800, 500);
        this.add(sp);

        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==actionButton){
            table.getSelectedRow();
        } else if (event.getSource()==cancelButton) {

        }
    }
}

