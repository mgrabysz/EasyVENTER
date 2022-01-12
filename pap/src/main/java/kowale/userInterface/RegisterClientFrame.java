package kowale.userInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class RegisterClientFrame extends RegisterOrganizerFrame {

    JComboBox<String> dayCombo, monthCombo, yearCombo, genderCombo;

    private String email, telephone, gender;
    private LocalDate date;
    private boolean isReady = false;

    public RegisterClientFrame() {
        super();

        this.setTitle("Enter details to create an account");
        surnameLabel.setText("Birth Date:");
        passwordLabel.setText("Gender:");
        this.add(passwordLabel);
        this.remove(surnameTextField);

        Vector<String> days = new Vector<String>();
        for (int i = 1; i < 32; i++) {
            days.add(String.valueOf(i));
        }
        dayCombo = new JComboBox<String>(days);
        dayCombo.setBounds(50, 230, 50, 30);
        this.add(dayCombo);

        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthCombo = new JComboBox<String>(months);
        monthCombo.setBounds(100, 230, 110, 30);
        this.add(monthCombo);

        Vector<String> years = new Vector<String>();
        for (int i = 2021; i > 1919; i--) {
            years.add(String.valueOf(i));
        }
        yearCombo = new JComboBox<String>(years);
        yearCombo.setBounds(210, 230, 60, 30);
        this.add(yearCombo);

        Vector<String> genders = new Vector<String>();
        genders.add("Female");
        genders.add("Male");
        genders.add("Not specified");
        genderCombo = new JComboBox<String>(genders);
        genderCombo.setBounds(350, 230, 200, 30);
        this.add(genderCombo);

        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmButton) {

            // get date
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy ");
            date = LocalDate.parse(dateTimeString, formatter);
            email = nameTextField.getText();
            telephone = loginTextField.getText();
            gender = (String) genderCombo.getSelectedItem();

            isReady = true;
        }
    }

    public String getEmail() {
        return email;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getGender() {
        return gender;
    }
    public LocalDate getDate() {
        return date;
    }

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean b) {
        isReady = b;
    }
}
