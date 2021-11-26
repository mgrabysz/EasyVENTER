package userInterface;

// =================================================================
// INSTRUKCJA OBSŁUGI KLASY LoginFrame()
// autor: Marcin
//
// LoginFrame() to ekran logowania
// Zawiera dwa pola do pisania: login i hasło
// oraz przycisk zatwierdzający dane
//
// Aby uruchomić okno, należy utworzyć obiekt klasy LoginFrame(), na przykład:
// LoginFrame window = new LoginFrame();
// albo prościej:
// new LoginFrame();
//
// Zmodyfikujcie sobie metodę actionPerformed(), która wykonuje się
// w momencie kliknięcia przycisku
//
// input użytkownika jest zapisany w zmiennych lokalnych userLogin oraz userPassword
//
// pozdrawiam cieplutko
// =============================================================================

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class LoginFrame extends JFrame implements ActionListener {


    JButton logButton;
    JLabel titleLabel, loginLabel, passwordLabel;
    JTextField loginTextField, passwordTextField;
    Border border;

    String userLogin, userPassword;

    LoginFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(300, 450));

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

        loginLabel = new JLabel("Login:");
        loginLabel.setBounds(50, 120, 200, 30);
        loginLabel.setHorizontalAlignment(JLabel.LEFT);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(50, 200, 200, 30);
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);

        loginTextField = new JTextField();
        loginTextField.setBounds(50, 150, 200, 30);

        passwordTextField = new JTextField();
        passwordTextField.setBounds(50, 230, 200, 30);

        logButton = new JButton();
        logButton.setText("Log in");
        logButton.setFocusable(false);
        logButton.setBounds(50, 300, 200, 50);
        logButton.addActionListener(this);

        this.add(titleLabel);
        this.add(loginLabel);
        this.add(loginTextField);
        this.add(passwordLabel);
        this.add(passwordTextField);
        this.add(logButton);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==logButton){

            userLogin = loginTextField.getText();
            userPassword = passwordTextField.getText();

            // mało elegancki sposób na wyczyszczenie textfielda
            loginTextField.setText(null);
            passwordTextField.setText(null);

// ==========================================================================
//  Tę metodę sobie zmodyfikuj!!!

            // poniższy kod to tylko sugestia i wskazówka, gdzie co umieścić,
            // sprawdzanie danych logowania oczywiście powinno być lepiej
            // zaimplementowane

            if (userLogin.equals("expectedLogin") && userPassword.equals("expectedPassword")){

                // jakiś kod że jest super
                this.dispose(); // zamyka okno

            } else {

                // hasło nieprawidłowe
                // wyświetla się komunikat, że dane są nieprawidłowe
                // i użytkownik może wpisać dane ponownie

                JOptionPane.showMessageDialog(
                    null,
                    "Login or password incorrect",
                    "Invalid user input",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                    );

            }
        }

    }

}
