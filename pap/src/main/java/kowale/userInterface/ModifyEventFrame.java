package kowale.userInterface;

import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModifyEventFrame extends CreateEventFrame {

    private String year, month, day, hour, minute;

    public ModifyEventFrame(HashMap<String, String> stringData) {
        super();
        this.setTitle("Modify event details");
        this.remove(sectorsLabel);
        this.remove(sectorCombo);

        name = stringData.get("name");
        country = stringData.get("country");
        city = stringData.get("city");
        address = stringData.get("address");
        year = stringData.get("year");
        month = stringData.get("month");
        day = stringData.get("day");
        hour = stringData.get("hour");
        minute = stringData.get("minute");

        nameTextField.setText(name);
        nameTextField.setEditable(false);
        cityTextField.setText(city);
        addressTextField.setText(address);

        countryCombo.setSelectedItem(country);
        yearCombo.setSelectedItem(year);
        monthCombo.setSelectedItem(month);
        dayCombo.setSelectedItem(day);
        hourCombo.setSelectedItem(hour);
        minuteCombo.setSelectedItem(minute);

        createButton.setText("Confirm changes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==createButton){
            address = addressTextField.getText();
            city = cityTextField.getText();
            country = (String) countryCombo.getSelectedItem();

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
        }
        else if (e.getSource()==cancelButton) {
            option = "cancel";
        }
    }
}
