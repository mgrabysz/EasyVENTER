package userInterface;

// ============================================================
// Aby wywołać okienko, należy utworzyć obiekt klasy MainMenu, na przykład:
// new MainMenu()

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class MainMenuFrame extends JFrame implements ActionListener{

    JButton seeEvents, makeEvent, logout;
    private String user_type;
    public int decision = 0;

    public MainMenuFrame(String type)
    {
        this.user_type = type;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 200);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        seeEvents = new JButton("Explore Events");
        makeEvent = new JButton("Make Event");
        logout = new JButton("Logout");

        if (this.user_type == "client")
        {
            seeEvents.addActionListener(this);
            panel.add(seeEvents);
        }
        else if (this.user_type == "organizer")
        {
            makeEvent.addActionListener(this);
            panel.add(makeEvent);
        }

        logout.addActionListener(this);
        panel.add(logout);

        this.setVisible(true);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==logout)
        {
            this.decision = 2;
            this.dispose();
        }
        else if (e.getSource()==seeEvents)
        {
            this.decision = 1;
            this.dispose();
        }
        if (e.getSource()==makeEvent)
        {
            this.decision = 1;
            this.dispose();
        }
    }
}
