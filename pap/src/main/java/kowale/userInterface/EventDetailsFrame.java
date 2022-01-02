package kowale.userInterface;

import kowale.event.Event;
import kowale.userInterface.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public class EventDetailsFrame extends BasicTableFrame {

    JButton buyButton, cancelButton;
    JLabel nameLabel, dateTimeTitleLabel, locationTitleLabel, organizerTitleLabel;
    JLabel dateLabel, countryLabel, cityLabel, addressLabel, organizerLabel;
    JLabel totalPriceLabel, numberOfTicketsLabel;
    Border border;

    private String name, country, city, address;
    private LocalDateTime dateTime;

    public EventDetailsFrame(String[][] data) {
        super(data, new String[]{"Number of sector", "Number of seats", "Ticket price"} , "Confirm", false);

        // String name = event.getName();
        // String organizer = event.getOrganizer();
        // String location = event.getLocation();

        // =============== uwaga hardkodowanie ===================
        name = "Otwarcie parasola";
        country = "Wielka Bolzga";
        city = "Warszawa";
        address = "Nowowiejska 15/19";
        dateTime = LocalDateTime.now();

        // Custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        // Format LocalDateTime
        String formattedDateTime = dateTime.format(formatter);

        this.setTitle(name);

        sp.setBounds(30, 280, 800, 100);

        nameLabel = new JLabel(name);
        nameLabel.setBounds(30, 50, 800, 70);
        nameLabel.setFont(new Font("MV Boli", Font.PLAIN, 50));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(nameLabel);

        dateTimeTitleLabel = new JLabel("Date:");
        dateTimeTitleLabel.setBounds(40, 150, 200, 30);
        this.add(dateTimeTitleLabel);

        dateLabel = new JLabel(formattedDateTime);
        dateLabel.setBounds(40, 180, 200, 30);
        this.add(dateLabel);

        locationTitleLabel = new JLabel("Location:");
        locationTitleLabel.setBounds(300, 150, 200, 30);
        this.add(locationTitleLabel);

        addressLabel = new JLabel(address);
        addressLabel.setBounds(300, 180, 200, 30);
        this.add(addressLabel);

        cityLabel = new JLabel(city);
        cityLabel.setBounds(300, 210, 200, 30);
        this.add(cityLabel);

        countryLabel = new JLabel(country);
        countryLabel.setBounds(300, 240, 200, 30);
        this.add(countryLabel);





    //     locationLabel = new JLabel("Location:");
    //     locationLabel.setBounds(40, 130, 200, 30);
    //     locationLabel.setHorizontalAlignment(JLabel.LEFT);
    //     this.add(locationLabel);

    //     locationInfoLabel = new JLabel(location);
    //     locationInfoLabel.setBounds(40, 160, 220, 30);
    //     this.add(locationInfoLabel);

    //     dateLabel = new JLabel("Date:");
    //     dateLabel.setBounds(40, 210, 200, 30);
    //     dateLabel.setHorizontalAlignment(JLabel.LEFT);
    //     this.add(dateLabel);

    //     dateInfoLabel = new JLabel(formattedDateTime);
    //     dateInfoLabel.setBounds(40, 160, 220, 30);
    //     this.add(dateInfoLabel);

    //     buyButton = new JButton();
    //     buyButton.setText("Create");
    //     buyButton.setFocusable(false);
    //     buyButton.setBounds(40, 400, 220, 50);
    //     buyButton.addActionListener(this);
    //     this.add(buyButton);
    // }

        // right column

    //     secOneLabel = new JLabel("Price of ticket in sector 1: ");
    //     secOneLabel.setBounds(340, 50, 200, 30);
    //     secOneLabel.setHorizontalAlignment(JLabel.LEFT);
    //     this.add(secOneLabel);

    //     secOneField = new JTextField();
    //     secOneField.setBounds(340, 80, 220, 30);
    //     this.add(secOneField);

    //     secTwoLabel = new JLabel("Price of ticket in sector 2: ");
    //     secTwoLabel.setBounds(340, 130, 200, 30);
    //     secTwoLabel.setHorizontalAlignment(JLabel.LEFT);
    //     this.add(secTwoLabel);

    //     secTwoField = new JTextField();
    //     secTwoField.setBounds(340, 160, 220, 30);
    //     this.add(secTwoField);

    //     secThreeLabel = new JLabel("Price of ticket in sector 3: ");
    //     secThreeLabel.setBounds(340, 210, 200, 30);
    //     secThreeLabel.setHorizontalAlignment(JLabel.LEFT);
    //     this.add(secThreeLabel);

    //     secThreeField = new JTextField();
    //     secThreeField.setBounds(340, 240, 220, 30);
    //     this.add(secThreeField);

    //     cancelButton = new JButton();
    //     cancelButton.setText("Cancel");
    //     cancelButton.setFocusable(false);
    //     cancelButton.setBounds(340, 400, 220, 50);
    //     cancelButton.addActionListener(this);
    //     this.add(cancelButton);

    //     this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buyButton){
            System.out.println("Buy button clicked");
        }


    // public void displayMessageDialog() {

    //     secOneField.setText(null);
    //     secTwoField.setText(null);
    //     secThreeField.setText(null);

    //     JOptionPane.showMessageDialog(
        //         null,
        //         "This data is not correct",
        //         "Invalid user input",
        //         JOptionPane.ERROR_MESSAGE    // ads red "x" picture
        //     );
        // }


}}