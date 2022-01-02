package kowale.userInterface;

import kowale.event.Event;
import kowale.userInterface.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    JPanel dateTimePanel, locationPanel, organizerPanel;
    Border border;

    private String name, country, city, address, organizer;
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
        organizer = "Polish Meme Association";

        // Custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        // Format LocalDateTime
        String formattedDateTime = dateTime.format(formatter);

        this.setTitle(name);

        sp.setBounds(30, 280, 800, 100);

        nameLabel = new JLabel(name);
        nameLabel.setBounds(45, 30, 800, 70);
        nameLabel.setFont(new Font("MV Boli", Font.PLAIN, 50));
        this.add(nameLabel);

        dateTimePanel = new JPanel();
        this.add(dateTimePanel);
        dateTimePanel.setBounds(30, 120, 260, 140);
        dateTimePanel.setLayout(null);

        locationPanel = new JPanel();
        this.add(locationPanel);
        locationPanel.setBounds(290, 120, 260, 140);
        locationPanel.setLayout(null);

        organizerPanel = new JPanel();
        this.add(organizerPanel);
        organizerPanel.setBounds(550, 120, 260, 140);
        organizerPanel.setLayout(null);

        dateTimeTitleLabel = new JLabel("Date:");
        dateTimePanel.add(dateTimeTitleLabel);
        Dimension dateTimeTitleLabelSize = dateTimeTitleLabel.getPreferredSize();
        dateTimeTitleLabel.setBounds(0, 0, dateTimeTitleLabelSize.width, dateTimeTitleLabelSize.height);

        dateLabel = new JLabel(formattedDateTime);
        dateTimePanel.add(dateLabel);
        Dimension dateLabelSize = dateLabel.getPreferredSize();
        dateLabel.setBounds(0, 30, dateLabelSize.width, dateLabelSize.height);

        locationTitleLabel = new JLabel("Location:");
        locationPanel.add(locationTitleLabel);
        Dimension locationTitleLabelSize = locationTitleLabel.getPreferredSize();
        locationTitleLabel.setBounds(0, 0, locationTitleLabelSize.width, locationTitleLabelSize.height);

        addressLabel = new JLabel(address);
        locationPanel.add(addressLabel);
        Dimension addressLabelSize = addressLabel.getPreferredSize();
        addressLabel.setBounds(0, 30, addressLabelSize.width, addressLabelSize.height);

        cityLabel = new JLabel(city);
        locationPanel.add(cityLabel);
        Dimension cityLabelSize = cityLabel.getPreferredSize();
        cityLabel.setBounds(0, 60, cityLabelSize.width, cityLabelSize.height);

        countryLabel = new JLabel(country);
        locationPanel.add(countryLabel);
        Dimension countryLabelSize = countryLabel.getPreferredSize();
        countryLabel.setBounds(0, 90, countryLabelSize.width, countryLabelSize.height);

        organizerTitleLabel = new JLabel("Organizer:");
        organizerPanel.add(organizerTitleLabel);
        Dimension organizerTitleLabelSize = organizerTitleLabel.getPreferredSize();
        organizerTitleLabel.setBounds(0, 0, organizerTitleLabelSize.width, organizerTitleLabelSize.height);

        organizerLabel = new JLabel(organizer);
        organizerPanel.add(organizerLabel);
        Dimension organizerLabelSize = organizerLabel.getPreferredSize();
        organizerLabel.setBounds(0, 30, organizerLabelSize.width, organizerLabelSize.height);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buyButton){
            System.out.println("Buy button clicked");
        }
    }
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


