package kowale.userInterface;

import kowale.database.StringConstant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

// import java.awt.*;
// import javax.swing.*;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ViewEventsFrame extends JFrame implements ActionListener {

    Border border;
    JButton buyButton, cancelButton;
    ButtonGroup group;

    public ViewEventsFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(1000, 600));

        border = BorderFactory.createLineBorder(new Color(0x004169E1), 3);

        String[][] data = {
            { "Meczyk jakiś", "Firma Krzak", "Bydgoszcz", "35.19.2022 25:72" },
            { "Ludzie biegający w kółko", "Google", "Warszafka", "48.17.2023 29:81" }
        };
        String[] columnNames = {"Name", "Organizer", "Location", "Date and time"};

        JTable table = new JTable(data, columnNames);
        // table.setBounds(30, 30, 500, 300);
        // this.add(table);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 30, 800, 500);
        this.add(sp);


        buyButton = new JButton();
        buyButton.setText("Buy");
        buyButton.setFocusable(false);
        buyButton.setBounds(850, 200, 120, 50);
        buyButton.addActionListener(this);
        this.add(buyButton);

        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setBounds(850, 300, 120, 50);
        cancelButton.addActionListener(this);
        this.add(cancelButton);

        group = new ButtonGroup();
        group.add(buyButton);
        group.add(cancelButton);

        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buyButton){
            StringConstant.FRAME_TYPE = "buy";
        } else if (e.getSource()==cancelButton) {
            StringConstant.FRAME_TYPE = "cancel";
        }

    }
}
