package model;

public final class EventsInfo {
    private static EventsInfo instance = null;
    private Event[] events;

    private EventsInfo(){
        //the effects affect only the market : sale and purchase, not his inventory

        //bad events
        events = new Event[EventId.values().length];
        events[EventId.ROTTEN_WOOD.ordinal()] = new Event(EventId.ROTTEN_WOOD, "Rotten Wood", "A part of all your wood is rotten, it will take time to clean it all, its value goes down for a time", 1, new ItemId[]{ItemId.OAK_STICK, ItemId.OAK_CHAIR, ItemId.OAK_DOOR, ItemId.OAK_WINDOW, ItemId.OAK_TABLE, ItemId.OAK_SET, ItemId.BIRCH_STICK, ItemId.BIRCH_CHAIR, ItemId.BIRCH_DOOR, ItemId.BIRCH_WINDOW, ItemId.BIRCH_TABLE, ItemId.BIRCH_SET}, 100, 0, 24, 0.9f, 0.8f, false);
        
        events[EventId.OAK_FOREST_BURNED.ordinal()] = new Event(EventId.OAK_FOREST_BURNED, "Oak Forest Burned", "A part of the oak forest of your seller burned, it will take time to regrow, its value goes up for a time", 1, new ItemId[]{ItemId.OAK_LOG, ItemId.OAK_PLANK}, 100, 0, 24, 1.1f, 1.2f, false);
        events[EventId.BIRCH_FOREST_BURNED.ordinal()] = new Event(EventId.BIRCH_FOREST_BURNED, "Birch Forest Burned", "A part of the birch forest of your seller burned, it will take time to regrow, its value goes up for a time", 1, new ItemId[]{ItemId.BIRCH_LOG, ItemId.BIRCH_PLANK}, 100, 0, 24, 1.1f, 1.2f, false);
       
        events[EventId.OAK_WOODWORMS.ordinal()] = new Event(EventId.OAK_WOODWORMS, "Oak Woodworms", "A part of all your wood is infected by woodworms, it will take time to clean it all, its value goes down for a time", 1, new ItemId[]{ItemId.OAK_STICK, ItemId.OAK_CHAIR, ItemId.OAK_DOOR, ItemId.OAK_WINDOW, ItemId.OAK_TABLE, ItemId.OAK_SET}, 100, 0, 24, 0.8f, 0.7f, false);
        events[EventId.BIRCH_WOODWORMS.ordinal()] = new Event(EventId.BIRCH_WOODWORMS, "Birch Woodworms", "A part of all your wood is infected by woodworms, it will take time to clean it all, its value goes down for a time", 1, new ItemId[]{ItemId.BIRCH_STICK, ItemId.BIRCH_CHAIR, ItemId.BIRCH_DOOR, ItemId.BIRCH_WINDOW, ItemId.BIRCH_TABLE, ItemId.BIRCH_SET}, 100, 0, 24, 0.8f, 0.7f, false);
        
        events[EventId.STEEL_MINE_COLLAPSE.ordinal()] = new Event(EventId.STEEL_MINE_COLLAPSE, "Steel Mine Collapse", "A part of the steel mine of your seller collapsed, it will take time to rebuild, its value goes up for a time", 1, new ItemId[]{ItemId.STEEL_INGOT, ItemId.STEEL_ROD}, 100, 0, 24, 1.1f, 1.2f, false);
        events[EventId.STONE_MINE_COLLAPSE.ordinal()] = new Event(EventId.STONE_MINE_COLLAPSE, "Stone Mine Collapse", "A part of the stone mine of your seller collapsed, it will take time to rebuild, its value goes up for a time", 1, new ItemId[]{ItemId.STONE_BRICK}, 100, 0, 24, 1.1f, 1.2f, false);
    
        events[EventId.SAND_STORM.ordinal()] = new Event(EventId.SAND_STORM, "Sand Storm", "A sand storm destroyed a part of your seller's sand, it will take time to rebuild, its value goes up for a time", 1, new ItemId[]{ItemId.SAND}, 100, 0, 24, 1.1f, 1.2f, false);
        events[EventId.GLASS_PANE_BREAK.ordinal()] = new Event(EventId.GLASS_PANE_BREAK, "Glass Pane Break", "A part of your seller's glass pane broke, it will take time to remade, its value goes up for a time", 1, new ItemId[]{ItemId.GLASS_PANE}, 100, 0, 24, 1.2f, 1.1f, false);

        //good events
        events[EventId.OAK_FOREST_GROWTH.ordinal()] = new Event(EventId.OAK_FOREST_GROWTH, "Oak Forest Growth", "Your oak seller has grown more oaks, their price is lower for some time", 1, new ItemId[]{ItemId.OAK_LOG, ItemId.OAK_PLANK}, 100, 0, 24, 0.9f, 0.8f, true);
        events[EventId.BIRCH_FOREST_GROWTH.ordinal()] = new Event(EventId.BIRCH_FOREST_GROWTH, "Birch Forest Growth", "Your birch seller has grown more birches, their price is lower for some time", 1, new ItemId[]{ItemId.BIRCH_LOG, ItemId.BIRCH_PLANK}, 100, 0, 24, 0.9f, 0.8f, true);

        events[EventId.NEW_OAK_SELER.ordinal()] = new Event(EventId.NEW_OAK_SELER, "New Oak Seller", "A new oak seller has appeared, his price is lower for some time", 1, new ItemId[]{ItemId.OAK_LOG, ItemId.OAK_PLANK, ItemId.OAK_STICK, ItemId.OAK_CHAIR, ItemId.OAK_DOOR, ItemId.OAK_WINDOW, ItemId.OAK_TABLE, ItemId.OAK_SET}, 100, 0, 24, 0.9f, 0.8f, true);
        events[EventId.NEW_BIRCH_SELER.ordinal()] = new Event(EventId.NEW_BIRCH_SELER, "New Birch Seller", "A new birch seller has appeared, his price is lower for some time", 1, new ItemId[]{ItemId.BIRCH_LOG, ItemId.BIRCH_PLANK, ItemId.BIRCH_STICK, ItemId.BIRCH_CHAIR, ItemId.BIRCH_DOOR, ItemId.BIRCH_WINDOW, ItemId.BIRCH_TABLE, ItemId.BIRCH_SET}, 100, 0, 24, 0.9f, 0.8f, true);

        events[EventId.NEW_STEEL_MINE.ordinal()] = new Event(EventId.NEW_STEEL_MINE, "New Steel Mine", "A new steel mine has appeared, the price of the steel goes down a bit", 1, new ItemId[]{ItemId.STEEL_INGOT, ItemId.STEEL_ROD}, 100, 0, 24, 0.9f, 0.9f, true);

        events[EventId.LAB_GROW_OAK.ordinal()] = new Event(EventId.LAB_GROW_OAK, "Lab Grow Oak", "Your lab has found a way to grow oak, the price of the oak goes down a lot", 1, new ItemId[]{ItemId.OAK_LOG, ItemId.OAK_PLANK}, 100, 0, 24, 0.8f, 0.8f, true);
        events[EventId.LAB_GROW_BIRCH.ordinal()] = new Event(EventId.LAB_GROW_BIRCH, "Lab Grow Birch", "Your lab has found a way to grow birch, the price of the birch goes down a lot", 1, new ItemId[]{ItemId.BIRCH_LOG, ItemId.BIRCH_PLANK}, 100, 0, 24, 0.8f, 0.8f, true);

        events[EventId.LAB_GROW_STEEL.ordinal()] = new Event(EventId.LAB_GROW_STEEL, "Lab Grow Steel", "Your lab has found a way to grow steel, the price of the steel goes down a lot", 1, new ItemId[]{ItemId.STEEL_INGOT, ItemId.STEEL_ROD}, 100, 0, 24, 0.8f, 0.8f, true);

        events[EventId.LAB_GROW_STONE.ordinal()] = new Event(EventId.LAB_GROW_STONE, "Lab Grow Sand", "Your lab has found a way to grow stone, the price of the sand goes down a lot", 1, new ItemId[]{ItemId.STONE_BRICK}, 100, 0, 24, 0.8f, 0.8f, true);
        
        events[EventId.NEW_DESERT_ISLAND.ordinal()] = new Event(EventId.NEW_DESERT_ISLAND, "New Desert Island", "A new desert island has appeared, the price of the sand goes down a bit", 1, new ItemId[]{ItemId.SAND}, 100, 0, 24, 0.9f, 0.9f, true);
    
    }

    public static EventsInfo getInstance(){
        if(instance == null){
            instance = new EventsInfo();
        }
        return instance;
    }

    public Event getEvent(EventId id){
        return events[id.ordinal()];
    }

    public Event[] getEvents(){
        return events;
    }
}
