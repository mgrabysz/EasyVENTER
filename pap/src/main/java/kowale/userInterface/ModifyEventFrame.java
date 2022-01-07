package kowale.userInterface;

import java.util.HashMap;
import java.awt.event.ActionEvent;

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

            // something to do when clicked

        }
        else if (e.getSource()==cancelButton) {

            // something to do when cancelled
        }
    }
}
