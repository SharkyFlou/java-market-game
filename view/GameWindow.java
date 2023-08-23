package view;

import java.awt.Dimension;
import java.util.Dictionary;

import javax.swing.JFrame;

import model.MoneyObserver;
import model.WeightObserver;
import model.InventoryObserver;
import model.ItemId;

public class GameWindow extends JFrame implements InventoryObserver, MoneyObserver, WeightObserver{

    public GameWindow() {
        setTitle("Market game");
        setSize(new Dimension(800, 600));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setVisible(true);
        setLocationRelativeTo(null);
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
