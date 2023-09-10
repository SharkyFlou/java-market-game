package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import model.Item;
import model.ItemId;
import model.ItemState;
import model.Market;

public class ItemView extends JPanel {
    private Item item;
    private int quantityOrPrice;
    private JLabel quantityOrPriceLabel;
    private ItemState itemState;
    private ShopView shopView;

    public ItemView(Item item, int quantityOrPrice, ItemState itemState) {
        this.item = item;
        this.quantityOrPrice = quantityOrPrice;
        this.itemState = itemState;

        // set size
        setPreferredSize(new Dimension(70, 70));

        // create thin black border
        setBorder(BorderFactory.createLineBorder(Color.black));

        // set layout as border layout
        setLayout(new BorderLayout());

        // add item name to the north
        add(new JLabel(item.getName()), BorderLayout.NORTH);

        // add item image to the center
        BufferedImage backgroundImage = item.getImage();
        add(new JLabel(new ImageIcon(backgroundImage.getScaledInstance(35, 35, Image.SCALE_FAST))),
                BorderLayout.CENTER);

        // add item price or quantity to the south
        if (itemState == ItemState.INVENTORY || itemState == ItemState.SHOP || itemState == ItemState.TO_BE_SHIPPED) {
            quantityOrPriceLabel = new JLabel(Integer.toString(quantityOrPrice) + ((itemState == ItemState.SHOP) ? "$" : ""));
        }
        else if(itemState == ItemState.CRAFT){
            quantityOrPriceLabel = new JLabel("?/"+Integer.toString(quantityOrPrice));
        }
        

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(quantityOrPriceLabel, BorderLayout.WEST);

        add(bottomPanel, BorderLayout.SOUTH);

        if (itemState == ItemState.SHOP) {
            BufferedImage infoImage = getImage("img/other/info.png");
            JLabel imgInfo = new JLabel(new ImageIcon(infoImage.getScaledInstance(15, 15, Image.SCALE_FAST)));
            imgInfo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    new StatWindow("Statistics", true, item.getId(), Market.getInstance());
                }
            });
            imgInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (shopView != null)
                        shopView.addItem(item.getId());
                }
            });
            bottomPanel.add(imgInfo, BorderLayout.EAST);
        } else {

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    new StatWindow("Statistics", true, item.getId(), Market.getInstance());
                }

            });

        }

        setToolTipText(item.getDescription());

        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public void setShopView(ShopView shopView) {
        this.shopView = shopView;

        // if in the item to be shipped
        if (itemState == ItemState.TO_BE_SHIPPED && getMouseListeners().length > 0) {
            removeMouseListener(getMouseListeners()[0]);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    shopView.removeItem(item.getId());
                }
            });
        }

    }

    public void updateQuantityOrPrice(int quantityOrPrice) {
        this.quantityOrPrice = quantityOrPrice;
        // update the label
        if (quantityOrPriceLabel != null) {
            quantityOrPriceLabel.setText(Integer.toString(quantityOrPrice) + ((itemState == ItemState.SHOP) ? "$" : ""));
        }
    }

    public int getQuantityOrPrice() {
        return quantityOrPrice;
    }

    public ItemId getItem() {
        return item.getId();
    }

    public void updateQttCraft(int qtt) {
        if(ItemState.CRAFT != itemState)
            return;
        quantityOrPriceLabel.setText(qtt + "/" + quantityOrPrice);
    }

    private BufferedImage getImage(String srcString) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(srcString));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return image;
    }

}
