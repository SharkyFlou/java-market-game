package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Event;

public class EventView extends JPanel{
    private JLabel lastingMonths;
    private int duration = 0;
    private int currentDay=-1;


    public EventView(Event event) {
        this.duration = event.getDuration();

        // set size
        setPreferredSize(new Dimension(90, 90));

        // create thin black border
        if(event.isGoodEvent())
            setBorder(BorderFactory.createLineBorder(Color.green));
        else
            setBorder(BorderFactory.createLineBorder(Color.red));

        // set layout as border layout
        setLayout(new BorderLayout());

        // add event name to the north
        add(new JLabel(event.getName()), BorderLayout.NORTH);

        // add item month or quantity to the south
        lastingMonths = new JLabel("Month 0 / "+Integer.toString(duration));

        
        add(lastingMonths, BorderLayout.SOUTH);

        // add item image to the center
        BufferedImage backgroundImage = event.getImage();
        add(new JLabel(new ImageIcon(backgroundImage.getScaledInstance(40, 40, Image.SCALE_FAST))),
                BorderLayout.CENTER);


        setToolTipText(event.getDescription());
    }

    public void update(){
        currentDay++;
        lastingMonths.setText("Month "+Integer.toString(currentDay+1)+"/"+Integer.toString(duration));
    }

    public int getDay(){
        return currentDay;
    }

    public int getDuration(){
        return duration;
    }
}
