package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import model.Inventory;
import model.InventoryObserver;
import model.Item;
import model.ItemId;
import model.ItemState;
import model.ItemsInfo;
import model.Market;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;



public class SalesView extends JPanel implements InventoryObserver{
    private List<ItemView> itemsView;
    private Inventory inventory;
    private ItemId sellingItem;
    private JSlider slider;
    private JButton sellButton;
    private ItemView sellingItemView;
    private JPanel salePanel;
    private int quantity;
    
    public SalesView(Inventory inventory) {
        this.inventory = inventory;
        this.quantity = 0;
        itemsView = new ArrayList<ItemView>();

        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(-1, 200));
        add(scrollPane, BorderLayout.CENTER);

        JPanel itemsPanel = new JPanel();
        scrollPane.setViewportView(itemsPanel);
        itemsPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new java.awt.Insets(3, 3, 3, 3);

        ItemsInfo itemsInfo = ItemsInfo.getInstance();
        for (ItemId itemId : ItemId.values()) {
            if(!itemsInfo.getItem(itemId).isRawMaterial()){
                ItemView itemView = new ItemView(itemsInfo.getItem(itemId), inventory.getItemQuantity(itemId), ItemState.SELL);
                itemView.setSalesView(this);
                itemsView.add(itemView);
                itemsPanel.add(itemView,c);
                c.gridx++;
                if(c.gridx == 6){
                    c.gridx = 0;
                    c.gridy++;
                }
            }
        }

        salePanel = new JPanel();
        salePanel.setPreferredSize(new Dimension(-1, 200));
        add(salePanel, BorderLayout.SOUTH);

        salePanel.setLayout(new GridBagLayout());

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new java.awt.Insets(3, 3, 3, 3);
        c.fill = GridBagConstraints.HORIZONTAL;

        sellingItem = itemsView.get(0).getItem();
        sellingItemView = new ItemView(itemsInfo.getItem(sellingItem), inventory.getItemQuantity(sellingItem), ItemState.INVENTORY);
        salePanel.add(sellingItemView,c);

        slider = new JSlider();
        updateSlider();
        
        c.gridx = 1;
        c.gridwidth = 2;
        salePanel.add(slider,c);

        sellButton = new JButton("Sell : " + slider.getValue());
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventory.addMoney(quantity * Market.getInstance().getItemPrice(sellingItem));
                inventory.updateItem(sellingItem, -quantity);
            }
        });
        c.gridx = 3;
        c.gridwidth = 1;
        salePanel.add(sellButton,c);
    }

    public void updateInventory(ItemId id, int quantity) {
        for(ItemView itemView : itemsView){
            itemView.updateQuantityOrPrice(inventory.getItemQuantity(itemView.getItem()));
        }
        updateSlider();
        sellButton.setText("Sell : " + quantity * Market.getInstance().getItemPrice(sellingItem) +"$");

        sellingItemView.updateQuantityOrPrice(inventory.getItemQuantity(sellingItem));
    }

    public void setSellingItem(ItemId itemId){
        sellingItem = itemId;

        salePanel.remove(sellingItemView);
        sellingItemView = new ItemView(ItemsInfo.getInstance().getItem(sellingItem), inventory.getItemQuantity(sellingItem), ItemState.INVENTORY);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new java.awt.Insets(3, 3, 3, 3);
        c.fill = GridBagConstraints.HORIZONTAL;
        salePanel.add(sellingItemView,c);

        updateSlider();

        sellButton.setText("Sell : " + quantity * Market.getInstance().getItemPrice(sellingItem) +"$");
        salePanel.updateUI();
    }

    private void updateSlider(){
        salePanel.remove(slider);
        slider = new JSlider();
        slider.setValue(inventory.getItemQuantity(sellingItem));
        quantity = slider.getValue();

        slider.setMinimum(0);
        slider.setMaximum(inventory.getItemQuantity(sellingItem));
        slider.setMajorTickSpacing(inventory.getItemQuantity(sellingItem)/5);
        slider.setMinorTickSpacing(inventory.getItemQuantity(sellingItem)/10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);

        slider.addChangeListener( new ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                quantity = slider.getValue();
                sellButton.setText("Sell : " + quantity * Market.getInstance().getItemPrice(sellingItem) +"$");
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new java.awt.Insets(3, 3, 3, 3);
        c.fill = GridBagConstraints.HORIZONTAL;

        salePanel.add(slider,c);
    }


}
