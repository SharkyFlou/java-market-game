package controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.management.MBeanRegistration;

import model.EventsInfo;
import model.Inventory;
import model.ItemId;
import model.ItemsInfo;
import model.Market;
import view.GameWindow;
import view.InventoryView;
import view.ItemView;
import view.MoneyView;

public class Game {
    public Game() {
        EventsInfo eventsInfo = EventsInfo.getInstance();
        ItemsInfo itemsInfo = ItemsInfo.getInstance();
        Market market = Market.getInstance();

        Inventory inventory = new Inventory(900);

        InventoryView inventoryView = new InventoryView(new LinkedHashMap<ItemId, ItemView>(), inventory.getMaxWeight(), 800/3, 600);

        MoneyView moneyView = new MoneyView();
        
        GameWindow gameWindow = new GameWindow(inventoryView, 800, 600, moneyView);

        inventory.addInvObserver(inventoryView);
        inventory.addWeightObserver(inventoryView);
        inventory.addMoneyObserver(moneyView);

        inventory.updateItem(ItemId.BIRCH_LOG, 10);
        inventory.updateItem(ItemId.OAK_LOG, 10);
        inventory.updateItem(ItemId.BIRCH_PLANK, 20);
        inventory.updateItem(ItemId.OAK_PLANK, 20);

        inventory.addMoney(30000);

    }

}
