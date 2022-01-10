package kowale.userInterface;

import kowale.database.GlobalVariables;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;

import java.util.Vector;
import java.util.HashMap;
import java.text.DecimalFormat;


public class EventDetailsFrame extends BasicTableFrame {

    JLabel nameLabel, dateTimeTitleLabel, locationTitleLabel, organizerTitleLabel;
    JLabel dateLabel, countryLabel, cityLabel, addressLabel, organizerLabel;
    JLabel chooseSectorLabel, chooseTicketsLabel, childrenLabel, adultLabel, vipLabel;
    JLabel totalPriceTitleLabel, totalPriceLabel;
    JPanel dateTimePanel, locationPanel, organizerPanel;
    Border border;
    JComboBox<Integer> childCombo, adultCombo, vipCombo;

    private String name, country, city, address, organizer, dateTime;
    private boolean isReady, isCancelled;
    private int numberAdults, numberChildren, numberVips, sector;


    public EventDetailsFrame(HashMap<String, String> eventDetails, String[][] ticketsData) {
        super(ticketsData, new String[]{"Sector", "Number of seats", "Adult ticket price"} , "Buy", false);
        isReady = isCancelled = false;

        name = eventDetails.get("name");
        country = eventDetails.get("country");
        city = eventDetails.get("city");
        address = eventDetails.get("address");
        dateTime = eventDetails.get("dateTime");
        organizer = eventDetails.get("organizer");

        this.setTitle(name);

        sp.setBounds(30, 280, 800, 100);
        table.changeSelection(0, 0, false, false); // provides that only one row can be selected

        // providing an action listener for changing a selection
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
               updatePrice();
            }
        });

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

        chooseSectorLabel = new JLabel("Choose sector");
        this.add(chooseSectorLabel);
        chooseSectorLabel.setBounds(30, 250, 200, 30);

        chooseTicketsLabel = new JLabel("Choose tickets");
        this.add(chooseTicketsLabel);
        chooseTicketsLabel.setBounds(30, 400, 200, 30);

        childrenLabel = new JLabel("Child (30% discount)");
        this.add(childrenLabel);
        childrenLabel.setBounds(30, 430, 200, 30);

        adultLabel = new JLabel("Adult");
        this.add(adultLabel);
        adultLabel.setBounds(290, 430, 200, 30);

        vipLabel = new JLabel("VIP ticket (150% price)");
        this.add(vipLabel);
        vipLabel.setBounds(550, 430, 200, 30);

        Vector<Integer> tickets = new Vector<Integer>();
        for (int i = 0; i <= 10; i++) {
            tickets.add(i);
        }
        childCombo = new JComboBox<Integer>(tickets);
        this.add(childCombo);
        childCombo.setBounds(30, 460, 220, 30);
        childCombo.addActionListener(this);

        adultCombo = new JComboBox<Integer>(tickets);
        this.add(adultCombo);
        adultCombo.setBounds(290, 460, 220, 30);
        adultCombo.addActionListener(this);

        vipCombo = new JComboBox<Integer>(tickets);
        this.add(vipCombo);
        vipCombo.setBounds(550, 460, 220, 30);
        vipCombo.addActionListener(this);

        totalPriceTitleLabel = new JLabel("Total price: ");
        this.add(totalPriceTitleLabel);
        totalPriceTitleLabel.setBounds(30, 500, 200, 30);

        totalPriceLabel = new JLabel("0" + " PLN");
        this.add(totalPriceLabel);
        totalPriceLabel.setBounds(115, 500, 200, 30);

        this.revalidate();
        this.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==actionButton){
            numberChildren = (Integer)childCombo.getSelectedItem();
            numberAdults = (Integer)adultCombo.getSelectedItem();
            numberVips = (Integer)vipCombo.getSelectedItem();
            sector = table.getSelectedRow() + 1;   //  +1 because table is indexed from 0
            option = "buy";
        } else if (e.getSource()==childCombo || e.getSource()==adultCombo || e.getSource()==vipCombo){
            updatePrice();
        } else if (e.getSource()==cancelButton){
            isCancelled = true;
            option = "cancel";
        }
    }

    public double calculateTotalPrice() {

        int children = (Integer)childCombo.getSelectedItem();
        int adults = (Integer)adultCombo.getSelectedItem();
        int vips = (Integer)vipCombo.getSelectedItem();
        int row = table.getSelectedRow();
        double price = Double.parseDouble( table.getValueAt(row, 2).toString() );
        double childrenCoef = GlobalVariables.CHILDREN_COEF;
        double vipCoef = GlobalVariables.VIP_COEF;

        double total = children * childrenCoef + vips * vipCoef + adults;
        total = total * price;
        return total;
    }

    public String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(d);
    }

    public void updatePrice() {
        String to_print = formatDouble(calculateTotalPrice());
        totalPriceLabel.setText(to_print + " PLN");
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    // public boolean getIsCancelled() {
    //     return isCancelled;
    // }
    
    public int getNumberChildren() {
        return numberChildren;
    }
    public int getNumberAdults() {
        return numberAdults;
    }
    public boolean getIsReady() {
        return isReady;
    }
    public int getNumberVips() {
        return numberVips;
    }
    public int getSector() {
        return sector;
    }
}

