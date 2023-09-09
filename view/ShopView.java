package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Inventory;
import model.ItemId;
import model.ItemsInfo;
import model.Market;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ShopView extends JPanel {
    private Inventory inventory;
    private JPanel shop;
    private JPanel inventoryShippedContainer;
    private JLabel moneyLabel;
    private JLabel weightLabel;
    private LinkedHashMap<ItemId, ItemView> itemsToShip;
    private HashMap<ItemId, Integer> itemsToShipQuantity;


    public ShopView(Inventory inventory) {
        this.inventory = inventory;
        itemsToShip = new LinkedHashMap<ItemId, ItemView>();
        itemsToShipQuantity = new HashMap<ItemId, Integer>();

        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        shop = new JPanel(new GridBagLayout());
        shop.setBorder(javax.swing.BorderFactory.createTitledBorder("SHOP"));
        add(shop, BorderLayout.CENTER);
        updateShop();

        JPanel tempInventory = new JPanel(new BorderLayout());
        tempInventory.setBorder(javax.swing.BorderFactory.createTitledBorder("Will be shiped next month"));
        tempInventory.setPreferredSize(new Dimension(-1, 200));
        add(tempInventory, BorderLayout.SOUTH);

        inventoryShippedContainer = new JPanel(new GridBagLayout());
        tempInventory.add(inventoryShippedContainer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        tempInventory.add(bottomPanel, BorderLayout.SOUTH);

        moneyLabel = new JLabel("Cost : 0");
        bottomPanel.add(moneyLabel, BorderLayout.EAST);

        weightLabel = new JLabel("Weight : 0 / "+ (inventory.getMaxWeight()-200));
        bottomPanel.add(weightLabel, BorderLayout.WEST);

    }

    public void updateShop(){
        shop.removeAll();
        shop.updateUI();

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(3, 3, 3, 3);
        ItemsInfo itemsInfo = ItemsInfo.getInstance();
        Market market = Market.getInstance();
        for (ItemId itemId : ItemId.values()) {
            if (itemsInfo.getItem(itemId).isRawMaterial()) {
                ItemView itemView = new ItemView(itemsInfo.getItem(itemId), market.getItemPrice(itemId) , true);
                itemView.setShopView(this);
                shop.add(itemView, c);
                c.gridx++;
                if (c.gridx >= 5) {
                    c.gridx = 0;
                    c.gridy++;
                }
            }
        }
    }

    public void addItem(ItemId itemId){
        if(itemsToShip.containsKey(itemId)){
            itemsToShipQuantity.put(itemId, itemsToShipQuantity.get(itemId)+1);
            itemsToShip.get(itemId).updateQuantityOrPrice(itemsToShipQuantity.get(itemId));
        }else{
            ItemView itemView = new ItemView(ItemsInfo.getInstance().getItem(itemId), 1, false);
            itemsToShip.put(itemId, itemView);
            itemsToShipQuantity.put(itemId, 1);
            GridBagConstraints gbc = findNextSpot();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(3, 3, 3, 3);
            inventoryShippedContainer.add(itemView, gbc);
            inventoryShippedContainer.updateUI();
        }
        
    }

    private GridBagConstraints findNextSpot(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        while(true){
            if(findItemByCoords(gbc.gridx, gbc.gridy) == null)
                break;
            //if very last item in grid stop
            if(gbc.gridx == 2 && gbc.gridy == (int)ItemId.values().length / 3)
                break;

            gbc.gridx++;
            if(gbc.gridx == 3){
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);
        return gbc;
    }

    private Component findItemByCoords(int x, int y) {
        Component match = null;
        GridBagLayout layout = (GridBagLayout)inventoryShippedContainer.getLayout();
        for (Component comp : inventoryShippedContainer.getComponents()) {
            GridBagConstraints gbc = layout.getConstraints(comp);
            if (gbc.gridx == x && gbc.gridy == y) {
                match = comp;
                break;
            }
        }
        return match;
    }
}
