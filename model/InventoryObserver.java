package model;
import java.util.Dictionary;

public interface InventoryObserver {
    public abstract void updateInventory(Dictionary<ItemId, Integer> inventory);
}
