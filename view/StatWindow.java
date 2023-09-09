package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Market;
import model.ItemId;

public class StatWindow extends JDialog{
    public StatWindow(String title, boolean modal, ItemId itemId, Market market){
        super( GameWindow.getInstance(), title, modal);

        setTitle("Statistics of "+itemId.toString());
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(GameWindow.getInstance());       


        getContentPane().setLayout(new BorderLayout());

        //title
        add(new JLabel("Statistics of "+itemId.toString()), BorderLayout.NORTH);

        //graph with stats of item in middle
        List<Integer> listToDraw = market.getHistory(itemId);
        
        // Create a custom graph panel and add it to the center
        GraphPanel graphPanel = new GraphPanel(listToDraw);
        add(graphPanel, BorderLayout.CENTER);


        //active events in south
        add(new JLabel("Active events: "), BorderLayout.SOUTH);

        setVisible(true);
    }
}
