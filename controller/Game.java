package controller;

import java.awt.Event;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.management.MBeanRegistration;

import model.EventId;
import model.EventsInfo;
import model.Inventory;
import model.ItemId;
import model.ItemsInfo;
import model.Market;
import view.CraftsView;
import view.EventsView;
import view.GameWindow;
import view.InventoryView;
import view.ItemView;
import view.MoneyView;
import view.SalesView;
import view.ShopView;

public class Game {
    public Game() {
        EventsInfo eventsInfo = EventsInfo.getInstance();
        ItemsInfo itemsInfo = ItemsInfo.getInstance();
        Market market = Market.getInstance();

        Inventory inventory = new Inventory(15000);

        InventoryView inventoryView = new InventoryView(new LinkedHashMap<ItemId, ItemView>(), inventory.getMaxWeight(), 800/3, 600);
        EventsView eventsView = new EventsView();
        market.setEventsView(eventsView);

        MoneyView moneyView = new MoneyView();

        ShopView shopView = new ShopView(inventory);
        market.setShopView(shopView);

        CraftsView craftsView = new CraftsView(inventory);
        inventory.addInvObserver(craftsView);

        SalesView salesView = new SalesView(inventory);
        inventory.addInvObserver(salesView);

        
        GameWindow.getInstance(inventoryView, 800, 600, moneyView, eventsView, shopView,craftsView, salesView);

        inventory.addWeightObserver(shopView);



        inventory.addInvObserver(inventoryView);
        inventory.addWeightObserver(inventoryView);
        inventory.addMoneyObserver(moneyView);

        inventory.updateItem(ItemId.BIRCH_LOG, 2);
        inventory.updateItem(ItemId.BIRCH_PLANK, 10);
        inventory.updateItem(ItemId.BIRCH_STICK, 20);

        inventory.addMoney(30000);
    }

}
