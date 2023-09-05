package model;
import java.util.Dictionary;

public interface InventoryObserver {
    public abstract void updateInventory(ItemId id, int quantity);
}
