package userInterface;

// =================================================================
// INSTRUKCJA OBSŁUGI KLASY RegisterFrame()
// autor: Marcin
//
// RegisterFrame() to ekran jestracji
// Użytkownik wpisuje następujące dane:
// Imię, nazwisko, nazwa użytkownika, hasło
// zaznacza typ konta organizator/klient
//
// Aby uruchomić okno, należy utworzyć obiekt klasy RegisterFrame(), na przykład:
// RegisterFrame window = new RegisterFrame();
// albo prościej:
// new RegisterFrame();
//
// Zmodyfikujcie sobie metodę actionPerformed(), która wykonuje się
// w momencie kliknięcia przycisku
//
// input użytkownika jest zapisany w atrybutach klasy:
// String userLogin, userPassword, userName, userSurname;
// int accountType;    // 0 if client, 1 if organiser
//
// pozdrawiam cieplutko
// =============================================================================

import database.EasyVENT;
import database.StringConstant;
import user.Client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
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
    JTextField loginTextField, passwordTextField, nameTextField, surnameTextField;
    Border border;
    ButtonGroup group;

    String userLogin, userPassword, userName, userSurname;
    int accountType;    // 0 if client, 1 if organiser

    public RegisterFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(600, 450));

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

        passwordTextField = new JTextField();
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
            userPassword = passwordTextField.getText();
            userName = nameTextField.getText();
            userSurname = surnameTextField.getText();

            if (organiserButton.isSelected()) {
                accountType = 1;    // 1 for organiser account
            } else {
                accountType = 0;    // 0 for client account
            }

            // mało elegancki sposób na wyczyszczenie textfielda
            loginTextField.setText(null);
            passwordTextField.setText(null);
            nameTextField.setText(null);
            surnameTextField.setText(null);

// ==========================================================================
//  Tę metodę sobie zmodyfikuj!!!

            // poniższy kod to tylko sugestia i wskazówka, gdzie co umieścić,
            // myślę, że w instrukcji if() można sprawdzić poprawność danych, np.
            // czy nie są pustym stringiem
            // ale to zostawiłem na razie, żeby to robiła jakaś inna funkcja na przykład

            boolean is_correct = true;
            if (is_correct) {

                //create user object
                Client new_user = new Client(10, userName, userSurname, userLogin, userPassword);

                boolean register_successful = EasyVENT.database.register_new_user(new_user);

                System.out.println("name: " + userName);
                System.out.println("surname: " + userSurname);
                System.out.println("login: " + userLogin);
                System.out.println("password: " + userPassword);

                if (accountType == 0){
                    System.out.println("Client account");
                } else {
                    System.out.println("Organiser account");
                }

                StringConstant.FRAME_TYPE = "login";  // set global variable to open login frame

                this.dispose(); // zamyka okno

            } else {

                // dane nieprawidłowe
                // wyświetla się komunikat, że dane są nieprawidłowe
                // i użytkownik może wpisać dane ponownie

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
