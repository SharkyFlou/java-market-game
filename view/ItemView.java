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
import model.Market;

public class ItemView extends JPanel {
    private Item item;
    private int quantityOrPrice;
    private JLabel quantityOrPriceLabel;
    private boolean isPrice;
    private ShopView shopView;

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

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(quantityOrPriceLabel, BorderLayout.WEST);

        add(bottomPanel, BorderLayout.SOUTH);

        // add item image to the center
        BufferedImage backgroundImage = item.getImage();
        add(new JLabel(new ImageIcon(backgroundImage.getScaledInstance(35, 35, Image.SCALE_FAST))),
                BorderLayout.CENTER);

        if (isPrice) {
            BufferedImage infoImage = getImage("img/other/info.png");
            JLabel imgInfo = new JLabel(new ImageIcon(infoImage.getScaledInstance(15, 15, Image.SCALE_FAST)));
            imgInfo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    new StatWindow("Statistics", true, item.getId(), Market.getInstance());
                }
            });
            addMouseHover(imgInfo);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if(shopView != null)
                        shopView.addItem(item.getId());
                }
            });
            bottomPanel.add(imgInfo,BorderLayout.EAST);
        } else {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    new StatWindow("Statistics", true, item.getId(), Market.getInstance());
                }
            });
        }

        setToolTipText(item.getDescription());

        addMouseHover(this);

    }

    public void setShopView(ShopView shopView) {
        this.shopView = shopView;
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

    public void addMouseHover(JComponent component){
        component.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

}
