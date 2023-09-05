package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import model.Item;
import model.ItemId;
import model.Market;

public class ItemView extends JPanel {
    private Item item;
    private int quantityOrPrice;
    private JLabel quantityOrPriceLabel;
    private boolean isPrice;

    public ItemView(Item item, int quantityOrPrice, boolean isPrice) {
        this.item = item;
        this.quantityOrPrice = quantityOrPrice;
        this.isPrice = isPrice;

        // set size
        setPreferredSize(new Dimension(70, 70));

        // create thin black border
        setBorder(BorderFactory.createLineBorder(Color.black));

        // set layout as border layout
        setLayout(new BorderLayout());

        // add item name to the north
        add(new JLabel(item.getName()), BorderLayout.NORTH);

        // add item price or quantity to the south

        quantityOrPriceLabel = new JLabel(Integer.toString(quantityOrPrice) + (isPrice ? "$" : ""));

        
        add(quantityOrPriceLabel, BorderLayout.SOUTH);

        // add item image to the center
        BufferedImage backgroundImage = item.getImage();
        add(new JLabel(new ImageIcon(backgroundImage.getScaledInstance(35, 35, Image.SCALE_FAST))),
                BorderLayout.CENTER);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                StatWindow statWindow = new StatWindow(item.getId(), Market.getInstance());
            }
        });

    }

    public void updateQuantityOrPrice(int quantityOrPrice) {
        this.quantityOrPrice = quantityOrPrice;
        // update the label
        if (quantityOrPriceLabel != null) {
            quantityOrPriceLabel.setText(Integer.toString(quantityOrPrice) + (isPrice ? "$" : ""));
        }
    }

    public int getQuantityOrPrice() {
        return quantityOrPrice;
    }

    public ItemId getItem() {
        return item.getId();
    }

}
