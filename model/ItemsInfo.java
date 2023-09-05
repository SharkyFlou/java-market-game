package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public final class ItemsInfo {
    private Item[] items;
    private static HashMap<ItemId, int[]> allItemsPrices;

    private static ItemsInfo instance = null;

    private ItemsInfo(){
        //all items
        items = new Item[ItemId.values().length];
        items[ItemId.OAK_LOG.ordinal()] = new Item(ItemId.OAK_LOG, "Oak Log", "A log from an oak tree", 10, true, getImage("img/items/OAK_LOG.png"));
        items[ItemId.OAK_PLANK.ordinal()] = new Item(ItemId.OAK_PLANK, "Oak Plank", "A plank made from an oak log", 10, false, getImage("img/items/OAK_PLANK.png"));
        items[ItemId.OAK_STICK.ordinal()] = new Item(ItemId.OAK_STICK, "Oak Stick", "A stick made from an oak log", 10, false, getImage("img/items/OAK_STICK.png"));

        items[ItemId.BIRCH_LOG.ordinal()] = new Item(ItemId.BIRCH_LOG, "Birch Log", "A log from a birch tree", 10, true, getImage("img/items/BIRCH_LOG.png"));
        items[ItemId.BIRCH_PLANK.ordinal()] = new Item(ItemId.BIRCH_PLANK, "Birch Plank", "A plank made from a birch log", 10, false, getImage("img/items/BIRCH_PLANK.png"));
        items[ItemId.BIRCH_STICK.ordinal()] = new Item(ItemId.BIRCH_STICK, "Birch Stick", "A stick made from a birch log", 10, false, getImage("img/items/BIRCH_STICK.png"));

        items[ItemId.STEEL_INGOT.ordinal()] = new Item(ItemId.STEEL_INGOT, "Steel Ingot", "A steel ingot", 10, true, getImage("img/items/STEEL_INGOT.png"));
        items[ItemId.STEEL_ROD.ordinal()] = new Item(ItemId.STEEL_ROD, "Steel Bar", "A steel bar", 10, false, getImage("img/items/STEEL_ROD.png"));

        items[ItemId.STONE_BRICK.ordinal()] = new Item(ItemId.STONE_BRICK, "Stone Brick", "A stone brick", 10, true, getImage("img/items/STONE_BRICK.png"));
        
        items[ItemId.SAND.ordinal()] = new Item(ItemId.SAND, "Sand", "A pile of sand", 10, true, getImage("img/items/SAND.png"));
        items[ItemId.GLASS_PANE.ordinal()] = new Item(ItemId.GLASS_PANE, "Glass Pane", "A glass pane", 10, false, getImage("img/items/GLASS_PANE.png"));

        items[ItemId.OAK_DOOR.ordinal()] = new Item(ItemId.OAK_DOOR, "Oak Door", "An oak door", 10, false, getImage("img/items/OAK_DOOR.png"));
        items[ItemId.OAK_WINDOW.ordinal()] = new Item(ItemId.OAK_WINDOW, "Oak Window", "An oak window", 10, false, getImage("img/items/OAK_WINDOW.png"));
        items[ItemId.OAK_CHAIR.ordinal()] = new Item(ItemId.OAK_CHAIR, "Oak Chair", "An oak chair", 10, false, getImage("img/items/OAK_CHAIR.png"));
        items[ItemId.OAK_TABLE.ordinal()] = new Item(ItemId.OAK_TABLE, "Oak Table", "An oak table", 10, false, getImage("img/items/OAK_TABLE.png"));
        items[ItemId.OAK_SET.ordinal()] = new Item(ItemId.OAK_SET, "Oak Set", "A set of oak furniture", 10, false, getImage("img/items/OAK_SET.png"));

        items[ItemId.BIRCH_DOOR.ordinal()] = new Item(ItemId.BIRCH_DOOR, "Birch Door", "A birch door", 10, false, getImage("img/items/BIRCH_DOOR.png"));
        items[ItemId.BIRCH_WINDOW.ordinal()] = new Item(ItemId.BIRCH_WINDOW, "Birch Window", "A birch window", 10, false, getImage("img/items/BIRCH_WINDOW.png"));
        items[ItemId.BIRCH_CHAIR.ordinal()] = new Item(ItemId.BIRCH_CHAIR, "Birch Chair", "A birch chair", 10, false, getImage("img/items/BIRCH_CHAIR.png"));
        items[ItemId.BIRCH_TABLE.ordinal()] = new Item(ItemId.BIRCH_TABLE, "Birch Table", "A birch table", 10, false, getImage("img/items/BIRCH_TABLE.png"));
        items[ItemId.BIRCH_SET.ordinal()] = new Item(ItemId.BIRCH_SET, "Birch Set", "A set of birch furniture", 10, false, getImage("img/items/BIRCH_SET.png"));

        items[ItemId.STONE_DOOR.ordinal()] = new Item(ItemId.STONE_DOOR, "Stone Door", "A stone door", 10, false, getImage("img/items/STONE_DOOR.png"));
        items[ItemId.STONE_WINDOW.ordinal()] = new Item(ItemId.STONE_WINDOW, "Stone Window", "A stone window", 10, false, getImage("img/items/STONE_WINDOW.png"));
        items[ItemId.STONE_CHAIR.ordinal()] = new Item(ItemId.STONE_CHAIR, "Stone Chair", "A stone chair", 10, false, getImage("img/items/STONE_CHAIR.png"));
        items[ItemId.STONE_TABLE.ordinal()] = new Item(ItemId.STONE_TABLE, "Stone Table", "A stone table", 10, false, getImage("img/items/STONE_TABLE.png"));
        items[ItemId.STONE_SET.ordinal()] = new Item(ItemId.STONE_SET, "Stone Set", "A set of stone furniture", 10, false, getImage("img/items/STONE_SET.png"));
    
        //all items prices on 12 months

        allItemsPrices = new HashMap<ItemId, int[]>();
        allItemsPrices.put(ItemId.OAK_LOG, new int[]{350, 370, 390, 400, 380, 370, 390, 400, 400, 380, 360, 350}); //~ 1 m3
        allItemsPrices.put(ItemId.OAK_PLANK, new int[]{19, 19, 19, 19, 19, 20, 20, 21, 19, 19, 19, 19}); //20 per log
        allItemsPrices.put(ItemId.OAK_STICK, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11}); //40 per log

        allItemsPrices.put(ItemId.BIRCH_LOG, new int[]{170, 170, 170, 170, 170, 170, 170, 169, 166, 164, 166, 169}); //~ 1 m3
        allItemsPrices.put(ItemId.BIRCH_PLANK, new int[]{9, 9, 9, 9, 9, 10, 11, 11, 9, 9, 9, 9}); //20 per log
        allItemsPrices.put(ItemId.BIRCH_STICK, new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 7}); //40 per log

        allItemsPrices.put(ItemId.STEEL_INGOT, new int[]{1500, 1500, 1490, 1485, 1475, 1490, 1495, 1500, 1500, 1500, 1505, 1520}); //~ 1000kg
        allItemsPrices.put(ItemId.STEEL_ROD, new int[]{10, 10, 10, 10, 10, 10, 10, 11, 11, 10, 10, 10}); //150 per ingot

        allItemsPrices.put(ItemId.STONE_BRICK, new int[]{1300, 1300, 1300, 1300, 1290, 1280, 1295, 1300, 1300, 1300, 1300, 1300}); //~ 1 m3


        //made automatically, has to be checked !!

        allItemsPrices.put(ItemId.SAND, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 12}); //~ 1 m3
        allItemsPrices.put(ItemId.GLASS_PANE, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 12}); //10 per m3

        allItemsPrices.put(ItemId.OAK_DOOR, new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 110, 110, 120});
        allItemsPrices.put(ItemId.OAK_WINDOW, new int[]{80, 80, 80, 80, 80, 80, 80, 80, 80, 88, 88, 96});
        allItemsPrices.put(ItemId.OAK_CHAIR, new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 55, 55, 60});
        allItemsPrices.put(ItemId.OAK_TABLE, new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 110, 110, 120});
        allItemsPrices.put(ItemId.OAK_SET, new int[]{330, 330, 330, 330, 330, 330, 330, 330, 330, 363, 363, 396});

        allItemsPrices.put(ItemId.BIRCH_DOOR, new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 55, 55, 60});
        allItemsPrices.put(ItemId.BIRCH_WINDOW, new int[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 44, 44, 48});
        allItemsPrices.put(ItemId.BIRCH_CHAIR, new int[]{25, 25, 25, 25, 25, 25, 25, 25, 25, 27, 27, 30});
        allItemsPrices.put(ItemId.BIRCH_TABLE, new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 55, 55, 60});
        allItemsPrices.put(ItemId.BIRCH_SET, new int[]{165, 165, 165, 165, 165, 165, 165, 165, 165, 182, 182, 198});

        allItemsPrices.put(ItemId.STONE_DOOR, new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 110, 110, 120});
        allItemsPrices.put(ItemId.STONE_WINDOW, new int[]{80, 80, 80, 80, 80, 80, 80, 80, 80, 88, 88, 96});
        allItemsPrices.put(ItemId.STONE_CHAIR, new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 55, 55, 60});
        allItemsPrices.put(ItemId.STONE_TABLE, new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 110, 110, 120});
        allItemsPrices.put(ItemId.STONE_SET, new int[]{330, 330, 330, 330, 330, 330, 330, 330, 330, 363, 363, 396});

    }

    public static ItemsInfo getInstance(){
        if(instance == null){
            instance = new ItemsInfo();
        }
        return instance;
    }

    public Item getItem(ItemId id){
        return items[id.ordinal()];
    }

    private BufferedImage getImage(String srcString){
        BufferedImage image;
        try{
            image = ImageIO.read(new File(srcString));
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        

        return image;
    }

    public int[] getItemPrices(ItemId id){
        return allItemsPrices.get(id);
    }

    public int getItemPrice(ItemId id, int month){
        return allItemsPrices.get(id)[month%12];
    }
}
