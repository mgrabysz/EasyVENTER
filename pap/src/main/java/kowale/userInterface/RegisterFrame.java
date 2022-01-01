package kowale.userInterface;

import kowale.database.EasyVENT;
import kowale.database.GlobalVariables;
import kowale.user.Client;
import kowale.user.EventOrganizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class RegisterFrame extends JFrame implements ActionListener {

    JButton confirmButton;
    JRadioButton clientButton, organiserButton;
    JLabel titleLabel, loginLabel, passwordLabel, nameLabel, surnameLabel, accountLabel;
    JTextField loginTextField, nameTextField, surnameTextField;
    JPasswordField passwordTextField;
    Border border;
    ButtonGroup group;

    String userLogin, userPassword, userName, userSurname;
    int accountType;    // 0 if client, 1 if organiser

    public RegisterFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(600, 450));
        this.setLocationRelativeTo(null);

        // title label "EasyVENTER"
        border = BorderFactory.createLineBorder(new Color(0x004169E1), 3);
        titleLabel = new JLabel();
        titleLabel.setText("EasyVENTER");
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 22));
        titleLabel.setBounds(50, 50, 200, 50);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(border);
        titleLabel.setBackground(Color.lightGray);
        titleLabel.setOpaque(true);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 120, 200, 30);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);

        surnameLabel = new JLabel("Surname:");
        surnameLabel.setBounds(50, 200, 200, 30);
        surnameLabel.setHorizontalAlignment(JLabel.LEFT);

        loginLabel = new JLabel("Login:");
        loginLabel.setBounds(350, 120, 200, 30);
        loginLabel.setHorizontalAlignment(JLabel.LEFT);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(350, 200, 200, 30);
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);

        accountLabel = new JLabel("I want to register as: ");
        accountLabel.setBounds(350, 35, 200, 40);
        accountLabel.setHorizontalAlignment(JLabel.LEFT);

        nameTextField = new JTextField();
        nameTextField.setBounds(50, 150, 200, 30);

        surnameTextField = new JTextField();
        surnameTextField.setBounds(50, 230, 200, 30);

        loginTextField = new JTextField();
        loginTextField.setBounds(350, 150, 200, 30);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(350, 230, 200, 30);

        organiserButton = new JRadioButton("Event organiser");
        organiserButton.setBounds(345, 65, 250, 35);
        organiserButton.setFocusable(false);
        clientButton = new JRadioButton("Client", true); // Client is selected as default option
        clientButton.setBounds(345, 90, 250, 35);
        clientButton.setFocusable(false);

        group = new ButtonGroup();
        group.add(organiserButton);
        group.add(clientButton);

        confirmButton = new JButton();
        confirmButton.setText("Confirm");
        confirmButton.setFocusable(false);
        confirmButton.setBounds(200, 300, 200, 50);
        confirmButton.addActionListener(this);

        this.add(titleLabel);
        this.add(nameLabel);
        this.add(surnameLabel);
        this.add(loginLabel);
        this.add(passwordLabel);
        this.add(accountLabel);

        this.add(nameTextField);
        this.add(surnameTextField);
        this.add(loginTextField);
        this.add(passwordTextField);

        this.add(clientButton);
        this.add(organiserButton);
        this.add(confirmButton);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmButton){

            userLogin = loginTextField.getText();
            userPassword = new String(passwordTextField.getPassword());
            userName = nameTextField.getText();
            userSurname = surnameTextField.getText();

            if (organiserButton.isSelected()) {
                accountType = 1;    // 1 for organiser account
            } else {
                accountType = 0;    // 0 for client account
            }

            loginTextField.setText(null);
            passwordTextField.setText(null);
            nameTextField.setText(null);
            surnameTextField.setText(null);

            boolean is_correct = true;
            if (is_correct) {

                //create user object
                if (accountType == 0){
                    Client new_user = new Client(userName, userSurname, userLogin, userPassword);
                    EasyVENT.database.register_new_user(new_user);
                } else {
                    EventOrganizer new_user = new EventOrganizer(userName, userSurname, userLogin, userPassword);
                    EasyVENT.database.register_new_user(new_user);
                }

                GlobalVariables.FRAME_TYPE = "welcome";  // set global variable to open welcome frame

                this.dispose(); // closes window

            } else {

                JOptionPane.showMessageDialog(
                    null,
                    "This data is not correct",
                    "Invalid user input",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                    );

            }
        }
    }
}
