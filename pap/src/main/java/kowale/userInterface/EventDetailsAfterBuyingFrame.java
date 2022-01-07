package kowale.userInterface;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;

import java.util.HashMap;


public class EventDetailsAfterBuyingFrame extends BasicTableFrame {
    /*
    Frame to check event details from client's perspective
    for an event to which client already has bought tickets.
    Allows to cancel reservation
    Constructor params:
    eventDetails : HashMap<String, String>
        organizer, address, etc.
    ticketsData : String[][]
        data to insert into table: category, number of seats
    sectorName : String[][]
        name of sector (assumption is that client only has ticket in one sector)
    */

    JLabel nameLabel, dateTimeTitleLabel, locationTitleLabel, organizerTitleLabel;
    JLabel dateLabel, countryLabel, cityLabel, addressLabel, organizerLabel;
    JLabel chooseSectorLabel;
    JPanel dateTimePanel, locationPanel, organizerPanel;
    Border border;

    private String name, country, city, address, organizer, dateTime;
    private boolean isReady, isCancelled;


    public EventDetailsAfterBuyingFrame(HashMap<String, String> eventDetails, String[][] ticketsData, String sectorName) {
        super(ticketsData, new String[]{"Category", "Number of seats"} , "Remove", false);
        isReady = isCancelled = false;

        name = eventDetails.get("name");
        country = eventDetails.get("country");
        city = eventDetails.get("city");
        address = eventDetails.get("address");
        dateTime = eventDetails.get("dateTime");
        organizer = eventDetails.get("organizer");

        this.setTitle(name);

        sp.setBounds(30, 280, 800, 100);
        table.changeSelection(0, 0, false, false);

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

        dateLabel = new JLabel(dateTime);
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


        // table

        chooseSectorLabel = new JLabel("You have tickets in sector " + sectorName);
        this.add(chooseSectorLabel);
        chooseSectorLabel.setBounds(30, 250, 400, 30);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==actionButton){

            // remove this ticket from user's tickets

        } else if (e.getSource()==cancelButton){
            isCancelled = true;
            option = "cancel";
        }
    }

    public boolean getIsReady() {
        return isReady;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}

