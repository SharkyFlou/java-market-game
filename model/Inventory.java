package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import view.InventoryView;

public class Inventory {
    private HashMap<ItemId, Integer> items;
    private int money;
    private int maxWeight;
    private List<InventoryObserver> inventoryObserver;
    private List<MoneyObserver> moneyObserver;
    private List<WeightObserver> weightObserver;



    public Inventory(int maxWeight) {
        this.items = new HashMap<ItemId, Integer>();
        this.money = 0;
        this.maxWeight = maxWeight;

        inventoryObserver = new ArrayList<InventoryObserver>();
        moneyObserver = new ArrayList<MoneyObserver>();
        weightObserver = new ArrayList<WeightObserver>();
    }

    public void addInvObserver(InventoryObserver invObserver) {
        this.inventoryObserver.add(invObserver);
    }

    public void addWeightObserver(InventoryView inventoryView) {
        this.weightObserver.add(inventoryView);
    }

    public HashMap<ItemId, Integer> getItems() {
        return items;
    }

    public int getMoney() {
        return money;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void updateItem(ItemId id, int quantity) {
        int currentQuantity = quantity;
        if (items.containsKey(id)) {
            //if item exists, add quantity
            currentQuantity += items.get(id); 
            items.put(id, currentQuantity);
            if(items.get(id) <= 0){
                //if quantity equals 0 or less, remove item
                items.remove(id);
            }
        } else {
            //if item doesn't exist, add item
            items.put(id, quantity);
        }
        notifyInvObservers(id, currentQuantity);
    }


    public void addMoney(int money) {
        this.money += money;
        
    }

    public void notifyInvObservers(ItemId item, Integer qtt) {
        for (InventoryObserver invObserver : this.inventoryObserver) {
            invObserver.updateInventory(item, qtt);
        }
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        notifyWeightObservers();
    }

    public void notifyWeightObservers() {
        for (WeightObserver weightObserver : this.weightObserver) {
            weightObserver.updateMaxWeight(this.maxWeight);
        }
    }


}
