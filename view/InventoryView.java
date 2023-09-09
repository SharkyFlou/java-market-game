package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedHashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Insets;

import model.InventoryObserver;
import model.ItemId;
import model.ItemsInfo;
import model.WeightObserver;

public class InventoryView extends JPanel implements InventoryObserver, WeightObserver {
    private LinkedHashMap<ItemId, ItemView> items;
    private int maxWeight;
    private JLabel weightLabel;
    private JPanel itemsPanel;
    private int weight;

    private static final java.awt.Color GRAY = new java.awt.Color(200, 200, 200);

    public InventoryView(LinkedHashMap<ItemId, ItemView> items, int maxWeight, int width, int height) {
        this.items = items;
        this.maxWeight = maxWeight;

        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        setBackground(GRAY);
        setBorder(javax.swing.BorderFactory.createTitledBorder("Inventory"));

        weightLabel = new JLabel();
        updateWeight();
        add(weightLabel, BorderLayout.SOUTH);

        itemsPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollableTextArea = new JScrollPane(itemsPanel);  
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 

        add(scrollableTextArea, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(3, 3, 3, 3);
        for (ItemView itemView : items.values()) {
            itemsPanel.add(itemView, c);
            c.gridx++;
            if (c.gridx == 3) {
                c.gridx = 0;
                c.gridy++;
            }
        }
    }

    public void updateInventory(ItemId id, int quantity) {
        // check if item exists
        if (items.containsKey(id)) {
            // update quantity
            items.get(id).updateQuantityOrPrice(quantity);
            // remove if fall to 0
            if (items.get(id).getQuantityOrPrice() <= 0) {
                itemsPanel.remove(items.get(id));
                items.remove(id);
                redrawAllItems();
            }
        } else {
            // add item
            ItemView itemView = new ItemView(ItemsInfo.getInstance().getItem(id), quantity, false);
            items.put(id, itemView);
            GridBagConstraints gbc = findNextSpot();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(3, 3, 3, 3);
            itemsPanel.add(itemView, gbc);
        }
        updateWeight();
    }

    public void updateMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        updateWeight();
    }

    private void updateWeight() {
        weightLabel.setText("Weight: " + weight + "/" + maxWeight);
    }

    private GridBagConstraints findNextSpot(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        GridBagLayout layout = (GridBagLayout)itemsPanel.getLayout();
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
        return gbc;
    }

    private Component findItemByCoords(int x, int y) {
        Component match = null;
        GridBagLayout layout = (GridBagLayout)itemsPanel.getLayout();
        for (Component comp : itemsPanel.getComponents()) {
            GridBagConstraints gbc = layout.getConstraints(comp);
            if (gbc.gridx == x && gbc.gridy == y) {
                match = comp;
                break;
            }
        }
        return match;
    }

    private GridBagConstraints findCoordsByItem(Component item){
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = (GridBagLayout)itemsPanel.getLayout();
        gbc = layout.getConstraints(item);
        return gbc;
    }

    //is called after an item is removed from the inventory, because it has now a hole, repositionate each items
    private void redrawAllItems(){
        itemsPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (ItemView item : items.values()) {
            itemsPanel.add(item,gbc);
            gbc.gridx++;
            if (gbc.gridx == 3) {
                gbc.gridx = 0;
                gbc.gridy++;
            }

            
        }

    }

    public void updateWeight(int weight) {
        this.weight=weight;
    }

}
