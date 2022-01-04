package kowale.userInterface;

import java.awt.event.ActionEvent;

public class RegisterOrganizerFrame extends RegisterFrame {

    private String email, telephone, company;

    public RegisterOrganizerFrame() {
        super();
        this.setTitle("Enter contact details");

        // variable names are not precise because frame
        // inherits from registerFrame

        nameLabel.setText("Email:");
        loginLabel.setText("Telephone:");
        surnameLabel.setText("Company:");

        this.remove(passwordLabel);
        this.remove(passwordTextField);
        this.remove(accountLabel);
        this.remove(organizerButton);
        this.remove(clientButton);

        titleLabel.setBounds(200, 50, 200, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmButton) {
            email = nameTextField.getText();
            telephone = loginTextField.getText();
            company = surnameTextField.getText();
        }
    }

    public String getEmail() {
        return email;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getCompany() {
        return company;
    }
}
