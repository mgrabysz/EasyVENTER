package userInterface;

import database.EasyVENT;
import database.NewUserData;
import database.StringConstant;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
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
    JTextField loginTextField;
    JPasswordField passwordTextField;
    Border border;
    NewUserData user_data;

    String userLogin, userPassword;

    public LoginFrame() {

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

        passwordTextField = new JPasswordField();
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
            userPassword = new String(passwordTextField.getPassword());

            // mało elegancki sposób na wyczyszczenie textfielda
            loginTextField.setText(null);
            passwordTextField.setText(null);

            boolean login_successfully = EasyVENT.database.logIntoDatabase(userLogin, userPassword);

            if (login_successfully){

                System.out.println("Log in was successful");

                StringConstant.FRAME_TYPE = "after login";

                this.dispose(); // closes window

            } else {

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
