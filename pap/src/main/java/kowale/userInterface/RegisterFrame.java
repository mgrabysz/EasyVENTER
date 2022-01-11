package kowale.userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class RegisterFrame extends JFrame implements ActionListener {

    JButton confirmButton;
    JRadioButton clientButton, organizerButton;
    JLabel titleLabel, loginLabel, passwordLabel, nameLabel, surnameLabel, accountLabel;
    JTextField loginTextField, nameTextField, surnameTextField;
    JPasswordField passwordTextField;
    Border border;
    ButtonGroup group;

    private String userLogin, userPassword, userName, userSurname;
    private int accountType;    // 0 if client, 1 if organizer
    private boolean isReady;

    public RegisterFrame() {

        isReady = false;

        this.setTitle("Register");
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

        organizerButton = new JRadioButton("Event organizer");
        organizerButton.setBounds(345, 65, 250, 35);
        organizerButton.setFocusable(false);
        clientButton = new JRadioButton("Client", true); // Client is selected as default option
        clientButton.setBounds(345, 90, 250, 35);
        clientButton.setFocusable(false);

        group = new ButtonGroup();
        group.add(organizerButton);
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
        this.add(organizerButton);
        this.add(confirmButton);
        this.setVisible(true);
        this.getRootPane().setDefaultButton(confirmButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmButton) {
            userLogin = loginTextField.getText();
            userPassword = new String(passwordTextField.getPassword());
            userName = nameTextField.getText();
            userSurname = surnameTextField.getText();

            if (organizerButton.isSelected()) {
                accountType = 1;    // 1 for organizer account
            } else {
                accountType = 0;    // 0 for client account
            }

            isReady = true;
        }
    }

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean b) {
        isReady = b;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public int getAccountType() {
        return accountType;
    }
}
