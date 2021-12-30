package kowale.userInterface;

import kowale.event.Event;

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


public class EventDetailsFrame extends JFrame implements ActionListener {

    JButton buyButton, cancelButton;
    JRadioButton secOnebutton, secTwoButton, secThreeButton;
    JLabel locationLabel, nameLabel, dateLabel, timeLabel, secOneLabel, secTwoLabel, secThreeLabel;
    JLabel locationInfoLabel, nameInfoLabel, dateInfoLabel, secOnePriceLabel, secTwoPriceLabel, secThreePriceLabel;
    JLabel totalPriceLabel, numberOfTicketsLabel;
    Border border;

    public EventDetailsFrame(Event event) {

        String name = event.getName();
        String organizer = event.getOrganizer();
        String location = event.getLocation();

        // Custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        // Format LocalDateTime
        String formattedDateTime = event.getDateTime().format(formatter);

        this.setTitle(name);
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

        nameInfoLabel = new JLabel(name);
        nameInfoLabel.setBounds(40, 80, 220, 30);
        this.add(nameInfoLabel);

        locationLabel = new JLabel("Location:");
        locationLabel.setBounds(40, 130, 200, 30);
        locationLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(locationLabel);

        locationInfoLabel = new JLabel(location);
        locationInfoLabel.setBounds(40, 160, 220, 30);
        this.add(locationInfoLabel);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(40, 210, 200, 30);
        dateLabel.setHorizontalAlignment(JLabel.LEFT);
        this.add(dateLabel);

        dateInfoLabel = new JLabel(formattedDateTime);
        dateInfoLabel.setBounds(40, 160, 220, 30);
        this.add(dateInfoLabel);

        buyButton = new JButton();
        buyButton.setText("Create");
        buyButton.setFocusable(false);
        buyButton.setBounds(40, 400, 220, 50);
        buyButton.addActionListener(this);
        this.add(buyButton);
    }

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
    // }

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
    }
}

