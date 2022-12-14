package kowale.userInterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class WelcomeFrame extends JFrame implements ActionListener {

    JButton logButton, registerButton;
    JLabel titleLabel;
    Border border;

    private String option = "";

    public WelcomeFrame() {
        this.setTitle("EasyVENTER");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(300, 450));
        this.setLocationRelativeTo(null);

        border = BorderFactory.createLineBorder(new Color(0x004169E1), 3);

        titleLabel = new JLabel();
        titleLabel.setText("EasyVENTER");
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 22));
        titleLabel.setBounds(50, 50, 200, 50);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(border);
        titleLabel.setBackground(Color.lightGray);
        titleLabel.setOpaque(true);


        logButton = new JButton();
        logButton.setText("Log in");
        logButton.setFocusable(false);
        logButton.setBounds(50, 200, 200, 50);
        logButton.addActionListener(this);

        registerButton = new JButton();
        registerButton.setText("Register (new user)");
        registerButton.setFocusable(false);
        registerButton.setBounds(50, 300, 200, 50);
        registerButton.addActionListener(this);

        this.add(titleLabel);
        this.add(logButton);
        this.add(registerButton);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==registerButton){
            option = "register";
        } else if (e.getSource()==logButton){
            option = "login";
        }
    }

    public String getOption() {
        return option;
    }

}

