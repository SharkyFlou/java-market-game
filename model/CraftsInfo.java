package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public final class CraftsInfo {
    public static CraftsInfo instance = null;
    // list all possible craft, each first item of eachmap is the result, the others
    // are the ingredients
    private List<LinkedHashMap<ItemId, Integer>> craftsInfo = new ArrayList<LinkedHashMap<ItemId, Integer>>();

    private CraftsInfo() {
        // oak plank
        LinkedHashMap<ItemId, Integer> oakPlank = new LinkedHashMap<ItemId, Integer>();
        oakPlank.put(ItemId.OAK_PLANK, 20);
        oakPlank.put(ItemId.OAK_LOG, 1);
        craftsInfo.add(oakPlank);

        // oak stick
        LinkedHashMap<ItemId, Integer> oakStick = new LinkedHashMap<ItemId, Integer>();
        oakStick.put(ItemId.OAK_STICK, 40);
        oakStick.put(ItemId.OAK_LOG, 1);
        craftsInfo.add(oakStick);

        // birch plank
        LinkedHashMap<ItemId, Integer> birchPlank = new LinkedHashMap<ItemId, Integer>();
        birchPlank.put(ItemId.BIRCH_PLANK, 20);
        birchPlank.put(ItemId.BIRCH_LOG, 1);
        craftsInfo.add(birchPlank);

        // birch stick
        LinkedHashMap<ItemId, Integer> birchStick = new LinkedHashMap<ItemId, Integer>();
        birchStick.put(ItemId.BIRCH_STICK, 40);
        birchStick.put(ItemId.BIRCH_LOG, 1);
        craftsInfo.add(birchStick);

        // steel rod
        LinkedHashMap<ItemId, Integer> steelRod = new LinkedHashMap<ItemId, Integer>();
        steelRod.put(ItemId.STEEL_ROD, 100);
        steelRod.put(ItemId.STEEL_INGOT, 1);
        craftsInfo.add(steelRod);

        // glass pane
        LinkedHashMap<ItemId, Integer> glassPane = new LinkedHashMap<ItemId, Integer>();
        glassPane.put(ItemId.GLASS_PANE, 20);
        glassPane.put(ItemId.SAND, 1);
        craftsInfo.add(glassPane);

        // oak door
        LinkedHashMap<ItemId, Integer> oakDoor = new LinkedHashMap<ItemId, Integer>();
        oakDoor.put(ItemId.OAK_DOOR, 1);
        oakDoor.put(ItemId.OAK_PLANK, 8);
        oakDoor.put(ItemId.OAK_STICK, 4);
        craftsInfo.add(oakDoor);
        

        // oak window
        LinkedHashMap<ItemId, Integer> oakWindow = new LinkedHashMap<ItemId, Integer>();
        oakWindow.put(ItemId.OAK_WINDOW, 1);
        oakWindow.put(ItemId.OAK_PLANK, 2);
        oakWindow.put(ItemId.OAK_STICK, 4);
        glassPane.put(ItemId.GLASS_PANE, 4);
        craftsInfo.add(oakWindow);
        

        // oak chair
        LinkedHashMap<ItemId, Integer> oakChair = new LinkedHashMap<ItemId, Integer>();
        oakChair.put(ItemId.OAK_CHAIR, 1);
        oakChair.put(ItemId.OAK_PLANK, 2);
        oakChair.put(ItemId.OAK_STICK, 12);
        craftsInfo.add(oakChair);
        

        // oak table
        LinkedHashMap<ItemId, Integer> oakTable = new LinkedHashMap<ItemId, Integer>();
        oakTable.put(ItemId.OAK_TABLE, 1);
        oakTable.put(ItemId.OAK_PLANK, 12);
        oakTable.put(ItemId.OAK_STICK, 4);
        craftsInfo.add(oakTable);
        

        // oak set
        LinkedHashMap<ItemId, Integer> oakSet = new LinkedHashMap<ItemId, Integer>();
        oakSet.put(ItemId.OAK_SET, 1);
        oakSet.put(ItemId.OAK_DOOR, 1);
        oakSet.put(ItemId.OAK_WINDOW, 2);
        oakSet.put(ItemId.OAK_CHAIR, 4);
        oakSet.put(ItemId.OAK_TABLE, 1);
        craftsInfo.add(oakSet);
        

        // birch door
        LinkedHashMap<ItemId, Integer> birchDoor = new LinkedHashMap<ItemId, Integer>();
        birchDoor.put(ItemId.BIRCH_DOOR, 1);
        birchDoor.put(ItemId.BIRCH_PLANK, 8);
        birchDoor.put(ItemId.BIRCH_STICK, 4);
        craftsInfo.add(birchDoor);
        

        // birch window
        LinkedHashMap<ItemId, Integer> birchWindow = new LinkedHashMap<ItemId, Integer>();
        birchWindow.put(ItemId.BIRCH_WINDOW, 1);
        birchWindow.put(ItemId.BIRCH_PLANK, 2);
        birchWindow.put(ItemId.BIRCH_STICK, 4);
        glassPane.put(ItemId.GLASS_PANE, 4);
        craftsInfo.add(birchWindow);
        

        // birch chair
        LinkedHashMap<ItemId, Integer> birchChair = new LinkedHashMap<ItemId, Integer>();
        birchChair.put(ItemId.BIRCH_CHAIR, 1);
        birchChair.put(ItemId.BIRCH_PLANK, 2);
        birchChair.put(ItemId.BIRCH_STICK, 12);
        craftsInfo.add(birchChair);
        

        // birch table
        LinkedHashMap<ItemId, Integer> birchTable = new LinkedHashMap<ItemId, Integer>();
        birchTable.put(ItemId.BIRCH_TABLE, 1);
        birchTable.put(ItemId.BIRCH_PLANK, 12);
        birchTable.put(ItemId.BIRCH_STICK, 4);
        craftsInfo.add(birchTable);
        

        // birch set
        LinkedHashMap<ItemId, Integer> birchSet = new LinkedHashMap<ItemId, Integer>();
        birchSet.put(ItemId.BIRCH_SET, 1);
        birchSet.put(ItemId.BIRCH_DOOR, 1);
        birchSet.put(ItemId.BIRCH_WINDOW, 2);
        birchSet.put(ItemId.BIRCH_CHAIR, 4);
        birchSet.put(ItemId.BIRCH_TABLE, 1);
        craftsInfo.add(birchSet);

        // stone door
        LinkedHashMap<ItemId, Integer> stoneDoor = new LinkedHashMap<ItemId, Integer>();
        stoneDoor.put(ItemId.STONE_DOOR, 1);
        stoneDoor.put(ItemId.STONE_BRICK, 8);
        stoneDoor.put(ItemId.STEEL_ROD, 4);
        craftsInfo.add(stoneDoor);
        

        // stone window
        LinkedHashMap<ItemId, Integer> stoneWindow = new LinkedHashMap<ItemId, Integer>();
        stoneWindow.put(ItemId.STONE_WINDOW, 1);
        stoneWindow.put(ItemId.STONE_BRICK, 2);
        stoneWindow.put(ItemId.STEEL_ROD, 4);
        glassPane.put(ItemId.GLASS_PANE, 4);
        craftsInfo.add(stoneWindow);
        

        // stone chair
        LinkedHashMap<ItemId, Integer> stoneChair = new LinkedHashMap<ItemId, Integer>();
        stoneChair.put(ItemId.STONE_CHAIR, 1);
        stoneChair.put(ItemId.STONE_BRICK, 2);
        stoneChair.put(ItemId.STEEL_ROD, 12);
        craftsInfo.add(stoneChair);

        // stone table
        LinkedHashMap<ItemId, Integer> stoneTable = new LinkedHashMap<ItemId, Integer>();
        stoneTable.put(ItemId.STONE_TABLE, 1);
        stoneTable.put(ItemId.STONE_BRICK, 12);
        stoneTable.put(ItemId.STEEL_ROD, 4);
        craftsInfo.add(stoneTable);
        

        // stone set
        LinkedHashMap<ItemId, Integer> stoneSet = new LinkedHashMap<ItemId, Integer>();
        stoneSet.put(ItemId.STONE_SET, 1);
        stoneSet.put(ItemId.STONE_DOOR, 1);
        stoneSet.put(ItemId.STONE_WINDOW, 2);
        stoneSet.put(ItemId.STONE_CHAIR, 4);
        stoneSet.put(ItemId.STONE_TABLE, 1);
        craftsInfo.add(stoneSet);
        
    }

    public static CraftsInfo getInstance() {
        if (instance == null) {
            instance = new CraftsInfo();
        }
        return instance;
    }

    public List<LinkedHashMap<ItemId, Integer>> getAllCrafts() {
        return craftsInfo;
    }
}
