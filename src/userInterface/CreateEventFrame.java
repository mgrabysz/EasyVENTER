package userInterface;

import database.StringConstant;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.*;
import javax.swing.*;

public class CreateEventFrame extends JFrame implements ActionListener {

    JButton createButton, cancelButton;
    Border border;

    public CreateEventFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(320, 650));
    
        border = BorderFactory.createLineBorder(new Color(0x004169E1), 3);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 220, 30);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(nameLabel);
        
        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(50, 80, 220, 30);
        this.add(nameTextField);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(50, 130, 220, 30);
        locationLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(locationLabel);

        JTextField locationTextField = new JTextField();
        locationTextField.setBounds(50, 160, 220, 30);
        this.add(locationTextField);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 210, 220, 30);
        dateLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(dateLabel);

        Vector<String> days = new Vector<String>();
        for (int i = 1; i < 32; i++) {
            days.add(String.valueOf(i));
        }
        JComboBox daysCombo = new JComboBox(days);
        daysCombo.setBounds(50, 240, 50, 30);
        // daysCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(daysCombo);
        
        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JComboBox monthsCombo = new JComboBox(months);
        monthsCombo.setBounds(100, 240, 110, 30);
        // monthsCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(monthsCombo);

        Vector<String> years = new Vector<String>();
        for (int i = 2021; i < 2051; i++) {
            years.add(String.valueOf(i));
        }
        JComboBox yearsCombo = new JComboBox(years);
        yearsCombo.setBounds(210, 240, 60, 30);
        // yearsCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(yearsCombo);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(50, 290, 220, 30);
        timeLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(timeLabel);

        Vector<String> hours = new Vector<String>();
        for (int i = 0; i < 24; i++) {
            String hour = String.valueOf(i);
            if (hour.length() == 1) {
                hour = "0" + hour;
            }
            hours.add(hour);
        }
        JComboBox hoursCombo = new JComboBox(hours);
        hoursCombo.setBounds(50, 320, 110, 30);
        // monthsCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(hoursCombo);

        Vector<String> minutes = new Vector<String>();
        for (int i = 0; i < 60; i++) {
            String minute = String.valueOf(i);
            if (minute.length() == 1) {
                minute = "0" + minute;
            }
            minutes.add(minute);
        }
        JComboBox minutesCombo = new JComboBox(minutes);
        minutesCombo.setBounds(160, 320, 110, 30);
        // monthsCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(minutesCombo);

        createButton = new JButton();
        createButton.setText("Create");
        createButton.setFocusable(false);
        createButton.setBounds(50, 400, 220, 50);
        createButton.addActionListener(this);
        this.add(createButton);

        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setBounds(50, 480, 220, 50);
        cancelButton.addActionListener(this);
        this.add(cancelButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==createButton){
            StringConstant.FRAME_TYPE= "create";

        } else if (e.getSource()==cancelButton){
            StringConstant.FRAME_TYPE = "cancel";
        }

    }
}

