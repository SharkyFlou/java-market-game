package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import view.InventoryView;

public class Inventory {
    private HashMap<ItemId, Integer> items;
    private int money;
    private int maxWeight;
    private int currentWeight;
    private List<InventoryObserver> inventoryObservers;
    private List<MoneyObserver> moneyObservers;
    private List<WeightObserver> weightObservers;

    public Inventory(int maxWeight) {
        this.items = new HashMap<ItemId, Integer>();
        this.money = 0;
        this.currentWeight = 0;
        this.maxWeight = maxWeight;

        inventoryObservers = new ArrayList<InventoryObserver>();
        moneyObservers = new ArrayList<MoneyObserver>();
        weightObservers = new ArrayList<WeightObserver>();
    }

    public void addInvObserver(InventoryObserver invObserver) {
        this.inventoryObservers.add(invObserver);
    }

    public void addWeightObserver(WeightObserver weightObserver) {
        this.weightObservers.add(weightObserver);
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

    public void updateItem(ItemId id, int quantity) {;
        if (items.containsKey(id)) {
            //if item exists, add quantity
            quantity += items.get(id); 
            items.put(id, quantity);
            if(items.get(id) <= 0){
                //if quantity equals 0 or less, remove item
                items.remove(id);
            }
        } else {
            //if item doesn't exist, add item
            items.put(id, quantity);
        }
        calculateCurrentWeight();
        notifyInvObservers(id, quantity);
    }

    private void calculateCurrentWeight() {
        currentWeight = 0;
        for (ItemId itemId : items.keySet()) {
            currentWeight += items.get(itemId) * ItemsInfo.getInstance().getItem(itemId).getWeight();
        }
        notifyWeightObservers();
    }


    public void addMoney(int money) {
        this.money += money;
        notifyMoneyObserver();
    }

    public void notifyInvObservers(ItemId item, Integer qtt) {
        for (InventoryObserver invObserver : this.inventoryObservers) {
            invObserver.updateInventory(item, qtt);
        }
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        notifyMaxWeightObservers();
    }

    public void notifyMaxWeightObservers() {
        for (WeightObserver weightObserver : this.weightObservers) {
            weightObserver.updateMaxWeight(this.maxWeight);
        }
    }

    public void notifyWeightObservers() {
        for (WeightObserver weightObserver : this.weightObservers) {
            weightObserver.updateWeight(this.currentWeight);
        }
    }

    public void notifyMoneyObserver() {
        for (MoneyObserver moneyObserver : this.moneyObservers) {
            moneyObserver.updateMoney(money);
        }
    }

    public void addMoneyObserver(MoneyObserver moneyObserver) {
        this.moneyObservers.add(moneyObserver);
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getItemQuantity(ItemId itemId) {
        if (items.containsKey(itemId)) {
            return items.get(itemId);
        }
        return 0;
    }
}
