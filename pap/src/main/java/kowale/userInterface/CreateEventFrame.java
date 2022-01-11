package kowale.userInterface;

import kowale.database.GlobalVariables;

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
    JTextField nameTextField, addressTextField, cityTextField;
    JComboBox<String> dayCombo, monthCombo, yearCombo, hourCombo, minuteCombo, countryCombo;
    JComboBox<Integer> sectorCombo;
    JLabel addressLabel, nameLabel, dateLabel, timeLabel, sectorsLabel, cityLabel, countryLabel;
    Border border;

    protected String name, address, city, country;
    protected int numOfSectors;
    protected LocalDateTime dateTime;
    protected String option = "";

    public CreateEventFrame() {
        this.setTitle("Create a new event");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(640, 550));
        this.setLocationRelativeTo(null);

        border = BorderFactory.createLineBorder(new Color(0x004169E1), 3);

        // left column
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 220, 30);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(50, 80, 220, 30);
        this.add(nameTextField);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 130, 220, 30);
        addressLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(addressLabel);

        addressTextField = new JTextField();
        addressTextField.setBounds(50, 160, 220, 30);
        this.add(addressTextField);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 210, 220, 30);
        dateLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(dateLabel);

        Vector<String> days = new Vector<String>();
        for (int i = 1; i < 32; i++) {
            days.add(String.valueOf(i));
        }
        dayCombo = new JComboBox<String>(days);
        dayCombo.setBounds(50, 240, 50, 30);
        this.add(dayCombo);

        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthCombo = new JComboBox<String>(months);
        monthCombo.setBounds(100, 240, 110, 30);
        this.add(monthCombo);

        Vector<String> years = new Vector<String>();
        for (int i = 2021; i < 2051; i++) {
            years.add(String.valueOf(i));
        }
        yearCombo = new JComboBox<String>(years);
        yearCombo.setBounds(210, 240, 60, 30);
        this.add(yearCombo);

        timeLabel = new JLabel("Time:");
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
        this.add(minuteCombo);

        // right column
        cityLabel = new JLabel("City:");
        cityLabel.setBounds(370, 50, 220, 30);
        cityLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(cityLabel);

        cityTextField = new JTextField();
        cityTextField.setBounds(370, 80, 220, 30);
        this.add(cityTextField);

        countryLabel = new JLabel("Country:");
        countryLabel.setBounds(370, 130, 220, 30);
        countryLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(countryLabel);

        countryCombo = new JComboBox<String>(GlobalVariables.COUNTRIES);
        countryCombo.setBounds(370, 160, 220, 30);
        this.add(countryCombo);

        sectorsLabel = new JLabel("Number of sectors:");
        sectorsLabel.setBounds(370, 210, 220, 30);
        sectorsLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(sectorsLabel);

        Vector<Integer> sectors = new Vector<Integer>();
        for (int i = 1; i <= 10; i++) {
            sectors.add(i);
        }
        sectorCombo = new JComboBox<Integer>(sectors);
        sectorCombo.setBounds(370, 240, 220, 30);
        this.add(sectorCombo);

        createButton = new JButton();
        createButton.setText("Create");
        createButton.setFocusable(false);
        createButton.setBounds(370, 410, 220, 50);
        createButton.addActionListener(this);
        this.add(createButton);

        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setBounds(50, 410, 220, 50);
        cancelButton.addActionListener(this);
        this.add(cancelButton);

        this.getRootPane().setDefaultButton(createButton);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==createButton){

            name = nameTextField.getText();
            address = addressTextField.getText();
            city = cityTextField.getText();
            country = (String) countryCombo.getSelectedItem();
            numOfSectors = (Integer) sectorCombo.getSelectedItem();

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

            option = "confirm";

        } else if (e.getSource()==cancelButton) {
            option = "cancel";
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

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getNumOfSectors() {
        return numOfSectors;
    }

    public void displayMessageDialog() {
        JOptionPane.showMessageDialog(
            null,
            "This data is not correct",
            "Invalid user input",
            JOptionPane.ERROR_MESSAGE    // ads red "x" picture
        );
    }
}

