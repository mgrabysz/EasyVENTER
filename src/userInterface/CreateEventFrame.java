package userInterface;

import database.StringConstant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    JTextField nameTextField, locationTextField;
    JComboBox<String> dayCombo, monthCombo, yearCombo, hourCombo, minuteCombo;
    Border border;
    ButtonGroup group;

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
        dayCombo = new JComboBox<String>(days);
        dayCombo.setBounds(50, 240, 50, 30);
        // daysCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(dayCombo);
        
        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthCombo = new JComboBox<String>(months);
        monthCombo.setBounds(100, 240, 110, 30);
        // monthsCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(monthCombo);

        Vector<String> years = new Vector<String>();
        for (int i = 2021; i < 2051; i++) {
            years.add(String.valueOf(i));
        }
        yearCombo = new JComboBox<String>(years);
        yearCombo.setBounds(210, 240, 60, 30);
        // yearsCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(yearCombo);

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
        hourCombo = new JComboBox<String>(hours);
        hourCombo.setBounds(50, 320, 110, 30);
        // monthsCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(hourCombo);

        Vector<String> minutes = new Vector<String>();
        for (int i = 0; i < 60; i++) {
            String minute = String.valueOf(i);
            if (minute.length() == 1) {
                minute = "0" + minute;
            }
            minutes.add(minute);
        }
        minuteCombo = new JComboBox<String>(minutes);
        minuteCombo.setBounds(160, 320, 110, 30);
        // monthsCombo.setHorizontalAlignment(JLabel.LEFT);
        this.add(minuteCombo);

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

        group = new ButtonGroup();
        group.add(createButton);
        group.add(cancelButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==createButton){
            // String name = nameTextField.getText();
            // String location = locationTextField.getText();
            String day = dayCombo.getSelectedItem().toString();
            if (day.length() == 1) {
                day = "0" + day;
            }
            String dateTimeString = day; 
            dateTimeString += ".";
            String month = Integer.toString(monthCombo.getSelectedIndex() + 1);
            if (month.length() == 1) {
                month = "0" + month;
            }
            dateTimeString += month;
            dateTimeString += ".";
            dateTimeString += yearCombo.getSelectedItem().toString();
            dateTimeString += " ";
            dateTimeString += hourCombo.getSelectedItem().toString();
            dateTimeString += ":";
            dateTimeString += minuteCombo.getSelectedItem().toString();

            // System.out.println(dateTimeString);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd.MM.yyyy HH:mm"
            );
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        
            StringConstant.FRAME_TYPE = "create";
        } else if (e.getSource()==cancelButton) {
            StringConstant.FRAME_TYPE = "cancel";
        }

    }
}

