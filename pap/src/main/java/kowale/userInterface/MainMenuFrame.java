package kowale.userInterface;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class MainMenuFrame extends JFrame implements ActionListener{

    JButton viewEvents, createEvent, logout, manageEvents, manageTickets;
    private String userType;
    private String option = "";

    public MainMenuFrame(String userType){
        this.userType = userType;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        viewEvents = new JButton("View Events");
        createEvent = new JButton("Create Event");
        manageEvents = new JButton("Manage Events");
        manageTickets = new JButton("Manage Tickets");
        logout = new JButton("Logout");

        if (this.userType == "client"){
            viewEvents.addActionListener(this);
            panel.add(viewEvents);
            manageTickets.addActionListener(this);
            panel.add(manageTickets);
        }
        else if (this.userType == "organizer"){
            createEvent.addActionListener(this);
            panel.add(createEvent);
            manageEvents.addActionListener(this);
            panel.add(manageEvents);
        }

        logout.addActionListener(this);
        panel.add(logout);

        this.setVisible(true);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==logout){
            option = "logout";
        }
        else if (e.getSource()==viewEvents){
            option = "viewEvents";
        }
        else if (e.getSource()==createEvent){
            option = "createEvent";
        }
        else if (e.getSource()==manageEvents){
            option = "manageEvents";
        }
        else if (e.getSource()==manageTickets){
            option = "manageTickets";
        }
    }

    public String getOption() {
        return option;
    }
}
