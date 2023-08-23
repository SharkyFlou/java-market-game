package model;

public class EventsInfo {
    private static EventsInfo instance = null;
    private Event[] events;

    private EventsInfo(){
        //the effects affect only the market : sale and purchase, not his inventory
        events = new Event[EventId.values().length];
        events[EventId.ROTTEN_WOOD.ordinal()] = new Event(EventId.ROTTEN_WOOD, "Rotten Wood", "A part of all your wood is rotten, it will take time to clean it all, its value goes down for a time", 1, new ItemId[]{ItemId.OAK_STICK, ItemId.OAK_CHAIR, ItemId.OAK_DOOR, ItemId.OAK_WINDOW, ItemId.OAK_TABLE, ItemId.OAK_SET, ItemId.BIRCH_STICK, ItemId.BIRCH_CHAIR, ItemId.BIRCH_DOOR, ItemId.BIRCH_WINDOW, ItemId.BIRCH_TABLE, ItemId.BIRCH_SET}, 100, 0, 30, 0.8f, 0.7f);
        
    }

    public static EventsInfo getInstance(){
        if(instance == null){
            instance = new EventsInfo();
        }
        return instance;
    }
}
