package kowale.userInterface;

import java.util.HashMap;

public class ModifyEventFrame extends CreateEventFrame {

    private String year, month, day, hour, minute;

    public ModifyEventFrame(HashMap<String, String> stringData) {
        super();
        this.setTitle("Modify event details");
        this.remove(sectorsLabel);
        this.remove(sectorCombo);

        name = stringData.get("name");
        city = stringData.get("city");
        street = stringData.get("street");
        country = stringData.get("country");
        year = stringData.get("year");
        month = stringData.get("month");
        day = stringData.get("day");
        hour = stringData.get("hour");
        minute = stringData.get("minute");

        nameTextField.setText(name);
        cityTextField.setText(city);
        streetTextField.setText(street);

        countryCombo.setSelectedItem(country);
        yearCombo.setSelectedItem(year);
        monthCombo.setSelectedItem(month);
        dayCombo.setSelectedItem(day);
        hourCombo.setSelectedItem(hour);
        minuteCombo.setSelectedItem(minute);




    }
}
