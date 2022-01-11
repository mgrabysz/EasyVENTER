package kowale.userInterface;
import javax.swing.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SliderFrame extends JFrame implements ChangeListener, ActionListener {
    /*
     * Frame to be displayed while cancelling tickets
     * Slider allows to choose number of tickets to cancel
     */

    JSlider slider;
    JLabel label;
    JButton confirmButton;
    boolean isReady = false;

    private int numberOfTickets;

    public SliderFrame(int max) {

        this.setTitle("Cancel tickets");
        this.setSize(300, 300);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        slider = new JSlider(1, max, 1);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        slider.setBounds(40, 40, 220, 50);
        this.add(slider);

        // set spacing
        slider.setMajorTickSpacing(max/5);

        label = new JLabel();
        label.setBounds(30, 100, 240, 50);
        label.setText("Number of tickets to cancel = " + slider.getValue());
        this.add(label);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        confirmButton.setBounds(40, 180, 220, 50);
        this.add(confirmButton);

        this.setVisible(true);
    }


    @Override
    public void stateChanged(ChangeEvent arg0) {
        label.setText("Number of tickets to cancel = " + slider.getValue());

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmButton) {
            isReady = true;
            // do something when confirmed
            numberOfTickets = slider.getValue();
        }

    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public boolean getReady(){
        return isReady;
    }

}

