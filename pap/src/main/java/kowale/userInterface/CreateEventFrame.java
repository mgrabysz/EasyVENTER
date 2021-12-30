package kowale.userInterface;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class CreateEventFrame extends JFrame implements ActionListener {

    JButton createButton, cancelButton;
    JTextField nameTextField, locationTextField, secOneField, secTwoField, secThreeField;
    JComboBox<String> dayCombo, monthCombo, yearCombo, hourCombo, minuteCombo;
    JLabel locationLabel, nameLabel, dateLabel, timeLabel, secOneLabel, secTwoLabel, secThreeLabel;
    Border border;

    String name, location;
    float[] prices;
    LocalDateTime dateTime;

    public CreateEventFrame() {
        this.setTitle("Create a new event");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(600, 550));
        this.setLocationRelativeTo(null);

        border = BorderFactory.createLineBorder(new Color(0x004169E1), 3);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(40, 50, 200, 30);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(40, 80, 220, 30);
        this.add(nameTextField);

        locationLabel = new JLabel("Location:");
        locationLabel.setBounds(40, 130, 200, 30);
        locationLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(locationLabel);

        locationTextField = new JTextField();
        locationTextField.setBounds(40, 160, 220, 30);
        this.add(locationTextField);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(40, 210, 200, 30);
        dateLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(dateLabel);

        Vector<String> days = new Vector<String>();
        for (int i = 1; i < 32; i++) {
            days.add(String.valueOf(i));
        }
        dayCombo = new JComboBox<String>(days);
        dayCombo.setBounds(40, 240, 50, 30);
        this.add(dayCombo);

        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthCombo = new JComboBox<String>(months);
        monthCombo.setBounds(90, 240, 110, 30);
        this.add(monthCombo);

        Vector<String> years = new Vector<String>();
        for (int i = 2021; i < 2051; i++) {
            years.add(String.valueOf(i));
        }
        yearCombo = new JComboBox<String>(years);
        yearCombo.setBounds(200, 240, 60, 30);
        this.add(yearCombo);

        timeLabel = new JLabel("Time:");
        timeLabel.setBounds(40, 290, 220, 30);
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
        hourCombo.setBounds(40, 320, 110, 30);
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
        minuteCombo.setBounds(150, 320, 110, 30);
        this.add(minuteCombo);

        createButton = new JButton();
        createButton.setText("Create");
        createButton.setFocusable(false);
        createButton.setBounds(40, 400, 220, 50);
        createButton.addActionListener(this);
        this.add(createButton);

        // right column

        secOneLabel = new JLabel("Price of ticket in sector 1: ");
        secOneLabel.setBounds(340, 50, 200, 30);
        secOneLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(secOneLabel);

        secOneField = new JTextField();
        secOneField.setBounds(340, 80, 220, 30);
        this.add(secOneField);

        secTwoLabel = new JLabel("Price of ticket in sector 2: ");
        secTwoLabel.setBounds(340, 130, 200, 30);
        secTwoLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(secTwoLabel);

        secTwoField = new JTextField();
        secTwoField.setBounds(340, 160, 220, 30);
        this.add(secTwoField);

        secThreeLabel = new JLabel("Price of ticket in sector 3: ");
        secThreeLabel.setBounds(340, 210, 200, 30);
        secThreeLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(secThreeLabel);

        secThreeField = new JTextField();
        secThreeField.setBounds(340, 240, 220, 30);
        this.add(secThreeField);

        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setBounds(340, 400, 220, 50);
        cancelButton.addActionListener(this);
        this.add(cancelButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==createButton){

            name = nameTextField.getText();
            location = locationTextField.getText();
            prices = new float[3];

            // get prices in each sector
            String[] pricesStrings = new String[3];
            pricesStrings[0] = secOneField.getText();
            pricesStrings[1] = secTwoField.getText();
            pricesStrings[2] = secThreeField.getText();

            // check if prices are numbers
            boolean pricesCorrect = true;
            for (String i : pricesStrings) {    // for each loop
                if (!isNumeric(i)) {
                    pricesCorrect = false;
                    break;
                }
            }

            if (!pricesCorrect) {
                displayMessageDialog();
                return;
            }

            // check if prices are positive
            for (int i=0; i<3; ++i) {

                String numStr = pricesStrings[i];
                float num = Float.parseFloat(numStr);
                prices[i] = num;

                if (num < 0) {
                    pricesCorrect = false;
                    break;
                }
            }

            if (!pricesCorrect) {
                displayMessageDialog();
                return;
            }

            // get datetime
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd.MM.yyyy HH:mm"
            );
            dateTime = LocalDateTime.parse(dateTimeString, formatter);

            // variables to use: name, location, prices, dateTime

        } else if (e.getSource()==cancelButton) {
            // something to do
            System.out.println("calcelled");
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Float.parseFloat(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void displayMessageDialog() {

        secOneField.setText(null);
        secTwoField.setText(null);
        secThreeField.setText(null);

        JOptionPane.showMessageDialog(
            null,
            "This data is not correct",
            "Invalid user input",
            JOptionPane.ERROR_MESSAGE    // ads red "x" picture
        );
    }
}

