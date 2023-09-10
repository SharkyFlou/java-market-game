package view;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Inventory;
import model.ItemId;
import model.ItemState;
import model.ItemsInfo;

public class CraftView extends JPanel {
    private LinkedHashMap<ItemId, Integer> items;
    private List<ItemView> itemsView;
    private Inventory inventory;

    public CraftView(LinkedHashMap<ItemId, Integer> itemsT, Inventory inventory) {
        this.inventory = inventory;
        this.items = itemsT;
        itemsView = new ArrayList<ItemView>();

        setPreferredSize(new java.awt.Dimension(200, 80));
        setLayout(new GridBagLayout());
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        // add margin

        // get 1st element of items
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = (items.size() - 2) * 2 + 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;

        ItemId firstItemId = items.keySet().iterator().next();
        ItemView itemView = new ItemView(ItemsInfo.getInstance().getItem(firstItemId), items.get(firstItemId),
                ItemState.CRAFT);
        itemsView.add(itemView);
        add(itemView, c);

        // add the arrow
        BufferedImage arrowImage = getImage("img/other/arrow.png");
        JLabel imgArrow = new JLabel(new ImageIcon(arrowImage.getScaledInstance(35, 35, Image.SCALE_FAST)));
        imgArrow.setToolTipText("Craft");
        imgArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imgArrow.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                tryToCraft();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }
        });


        c.gridx = c.gridx - 1;
        add(imgArrow, c);

        // add all other items with the plus sign
        int count = 0;
        for (ItemId itemId : items.keySet()) {
            count++;
            if (count == 1) {
                continue;
            }

            itemView = new ItemView(ItemsInfo.getInstance().getItem(itemId), items.get(itemId), ItemState.CRAFT);
            itemsView.add(itemView);
            c.gridx = c.gridx - 1;
            add(itemView, c);

            if (count != items.size()) {
                BufferedImage plusImage = getImage("img/other/plus.png");
                JLabel imgPlus = new JLabel(new ImageIcon(plusImage.getScaledInstance(35, 35, Image.SCALE_FAST)));
                c.gridx = c.gridx - 1;
                add(imgPlus, c);
            }

        }
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

    public void updateQuantity(){
        for(ItemView itemView : itemsView){
            itemView.updateQttCraft(inventory.getItemQuantity(itemView.getItem()));
        }
    }

    public void tryToCraft(){
         ItemId firstItemId = items.keySet().iterator().next();
        for(ItemId itemId : items.keySet()){
            if(itemId == firstItemId){
                continue;
            }
            if(inventory.getItemQuantity(itemId) < items.get(itemId)){
                return;
            }
        }
        inventory.updateItem(firstItemId, items.get(firstItemId));
        for(ItemId itemId : items.keySet()){
            if(itemId == firstItemId){
                continue;
            }
            inventory.updateItem(itemId, -items.get(itemId));
        }

    }

}
