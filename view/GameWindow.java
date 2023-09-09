package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Dictionary;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.ItemId;
import model.Market;

public final class GameWindow extends JFrame {
    private static GameWindow gameWindow = null;


    // dimensions of the window
    private int WIDTH;
    private int HEIGHT;

    // colors
    private static final java.awt.Color LIGHT_GRAY = new java.awt.Color(240, 240, 240);
    private static final java.awt.Color GRAY = new java.awt.Color(200, 200, 200);

    private GameWindow(InventoryView inventoryView, int width, int height, MoneyView moneyView, EventsView eventsView, ShopView shopView) {
        this.WIDTH = width;
        this.HEIGHT = height;
        setTitle("Market game");
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // box layout for the window (horizontal)
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        // main panels
        

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(WIDTH / 3 * 2, HEIGHT));
        rightPanel.setBackground(LIGHT_GRAY);

  
        this.add(inventoryView);
        this.add(rightPanel);

        // right panel

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JPanel topRightPanel = new JPanel();
        topRightPanel.setPreferredSize(new Dimension(WIDTH / 3 * 2, HEIGHT / 12));
        topRightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topRightPanel.setLayout(new BoxLayout(topRightPanel, BoxLayout.X_AXIS));
        rightPanel.add(topRightPanel);

        //adding the money
        JPanel moneyPanel = new JPanel();
        moneyPanel.setPreferredSize(new Dimension((WIDTH / 3 * 2 / 5)*4, HEIGHT / 12));
        moneyPanel.add(moneyView);
        topRightPanel.add(moneyPanel);

        //adding the button to change the month
        JPanel nextMonthPanel = new JPanel();
        nextMonthPanel.setPreferredSize(new Dimension(WIDTH / 3 * 2 / 5, HEIGHT / 12));
        topRightPanel.add(nextMonthPanel);

        JButton nextMonthButton = new JButton("Next month");
        nextMonthButton.setPreferredSize(new Dimension(WIDTH / 3 * 2 / 5, HEIGHT / 12-10));
        nextMonthButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Market.getInstance().passMonth();
            }
        });
        nextMonthPanel.add(nextMonthButton);


        JTabbedPane bottomRightPanel = new JTabbedPane();
        bottomRightPanel.setPreferredSize(new Dimension(WIDTH / 3 * 2, HEIGHT / 12 * 11));
        bottomRightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.add(bottomRightPanel);


        bottomRightPanel.addTab("Buy   ", null, shopView,
                "Here you buy things");
        bottomRightPanel.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panelSale = new JPanel();
        bottomRightPanel.addTab("Sell   ", null, panelSale,
                "Here you sell things");
        bottomRightPanel.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panelCraft = new JPanel();
        bottomRightPanel.addTab("Craft   ", null, panelCraft,
                "Here you craft things");
        bottomRightPanel.setMnemonicAt(2, KeyEvent.VK_3);


        //event panel
        bottomRightPanel.addTab("Event   ", null, eventsView,
                "Here you see the events");
        bottomRightPanel.setMnemonicAt(2, KeyEvent.VK_3);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static GameWindow getInstance() {
        if (gameWindow != null) {
            return gameWindow;
        }
        return null;
    }

    public static GameWindow getInstance(InventoryView inventoryView, int width, int height, MoneyView moneyView, EventsView eventsView, ShopView shopView){
        if (gameWindow == null) {
            gameWindow = new GameWindow(inventoryView, width, height, moneyView, eventsView, shopView);
        }
        return gameWindow;
    }


    public void updateWeight(int weight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWeight'");
    }

    public void updateMoney(int money) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMoney'");
    }

    public void updateInventory(Dictionary<ItemId, Integer> inventory) {
        throw new UnsupportedOperationException("Unimplemented method 'updateInventory'");
    }

}
