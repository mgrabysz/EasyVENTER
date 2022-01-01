package kowale.userInterface;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class MainMenuFrame extends JFrame implements ActionListener{

    JButton seeEvents, makeEvent, logout, manageEvent, manageTicket;
    private String user_type;
    private boolean see = false;
    private boolean make = false;
    private boolean manageE = false;
    private boolean manageT = false;
    private boolean cancel = false;
    public int decision = 0;

    public MainMenuFrame(String type){
        this.user_type = type;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 200);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        seeEvents = new JButton("Explore Events");
        makeEvent = new JButton("Make Event");
        manageEvent = new JButton("Manage Events");
        manageTicket = new JButton("Manage Tickets");
        logout = new JButton("Logout");

        if (this.user_type == "client"){
            seeEvents.addActionListener(this);
            panel.add(seeEvents);
            manageTicket.addActionListener(this);
            panel.add(manageTicket);
        }
        else if (this.user_type == "organizer"){
            makeEvent.addActionListener(this);
            panel.add(makeEvent);
            manageEvent.addActionListener(this);
            panel.add(manageEvent);
        }

        logout.addActionListener(this);
        panel.add(logout);

        this.setVisible(true);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==logout){
            cancel = true;
            this.dispose();
        }
        else if (e.getSource()==seeEvents){
            see = true;
            this.dispose();
        }
        else if (e.getSource()==makeEvent){
            make = true;
            this.dispose();
        }
        else if (e.getSource()==manageEvent){
            manageE = true;
            this.dispose();
        }
        else if (e.getSource()==manageTicket){
            manageT = true;
            this.dispose();
        }
    }

    public boolean getCancel(){
        return cancel;
    }

    public boolean getSee(){
        return see;
    }

    public boolean getMake(){
        return make;
    }

    public boolean getManageT(){
        return manageT;
    }

    public boolean getManageE(){
        return manageE;
    }
}
