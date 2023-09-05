package model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public final class Market {
    private static Market instance;
    private HashMap<ItemId, List<Integer> > history = new HashMap<ItemId, List<Integer>>();

    private HashMap<EventId, Integer> activeEvents = new HashMap<EventId, Integer>();
    
    private HashMap<EventId, Integer> cooldownEvents = new HashMap<EventId, Integer>();

    private int actualMonth = 0;
    private int monthWithoutEvent = 0;
    
    private Market() {
        //go for 12 months to generate history without events
        for(int i = 0; i < 12; i++){
            passMonth();
        }
    };

    public static Market getInstance() {
        if (instance == null) {
            instance = new Market();
        }
        return instance;
    }

    public void passMonth(){
        actualMonth++;
        for(EventId eventId : activeEvents.keySet()){
            activeEvents.put(eventId, activeEvents.get(eventId) - 1);
            if(activeEvents.get(eventId) <= 0){
                activeEvents.remove(eventId);
            }
        }
        for(EventId eventId : cooldownEvents.keySet()){
            cooldownEvents.put(eventId, cooldownEvents.get(eventId) - 1);
            if(cooldownEvents.get(eventId) <= 0){
                cooldownEvents.remove(eventId);
            }
        }
        monthWithoutEvent++;
        if(generateEvent()){
            monthWithoutEvent = 0;
        }
        generateHistoryItem();
    }

    //return true if generated an new event, false if not
    private boolean generateEvent(){
        int random = (int) (Math.random() * 100);
        if(random < 15* monthWithoutEvent){
            //create list of all possible events without the ones in cooldown
            List<EventId> possibleEvents = new ArrayList<EventId>();
            EventsInfo eventsInfo = EventsInfo.getInstance();

            int totalProbabilities = 0;
            for(EventId eventId : EventId.values()){
                if(eventsInfo.getEvent(eventId).getCooldown() > actualMonth-11){
                    continue;
                }
                if(cooldownEvents.containsKey(eventId)){
                    continue;
                }
                possibleEvents.add(eventId);
                totalProbabilities += eventsInfo.getEvent(eventId).getProbability();
            }

            //choose one event
            int randomEvent = (int) (Math.random() * totalProbabilities);
            for(EventId eventId : possibleEvents){
                randomEvent -= eventsInfo.getEvent(eventId).getProbability();
                if(randomEvent <= 0){
                    //add event to active events
                    activeEvents.put(eventId, eventsInfo.getEvent(eventId).getDuration());
                    //add event to cooldown events
                    cooldownEvents.put(eventId, eventsInfo.getEvent(eventId).getCooldown());
                    return true;
                }
            }
        }
        return false;
    }

    private void generateHistoryItem(){
        EventsInfo eventsInfo = EventsInfo.getInstance();
        ItemsInfo itemsInfo = ItemsInfo.getInstance();

        //for each item
        for(ItemId itemId : ItemId.values()){
            Item item = itemsInfo.getItem(itemId);
            //generate number between 0.95 and 1.05
            float random = (float) (Math.random() * 0.1 + 0.95);
            float currentPrice = itemsInfo.getItemPrice(itemId, actualMonth) * random;
            //for each active event
            for(EventId event : activeEvents.keySet()){
                //for each affected items
                for(ItemId affectedItem : eventsInfo.getEvent(event).getAffectedItems()){
                    //if affected, modify price
                    if(affectedItem == itemId){
                        currentPrice = priceItemByEvent((int) currentPrice, event);
                    }
                }
            }
            //add price to history
            if(!history.containsKey(itemId)){
                history.put(itemId, new ArrayList<Integer>());
            }
            history.get(itemId).add((int) currentPrice);
        }
    }

    private int priceItemByEvent(int itemPrice, EventId eventId){
        EventsInfo eventsInfo = EventsInfo.getInstance();
        float midTime = (eventsInfo.getEvent(eventId).getDuration()+1)/2;
        float multBase = eventsInfo.getEvent(eventId).getMultiplierBase();
        float multProg = eventsInfo.getEvent(eventId).getMultiplierProgressive();
        int newValue = (int) ((itemPrice + (itemPrice * ((multProg - 1) * (midTime - Math.abs(actualMonth - midTime)) / midTime))) * multBase);

        return newValue;
    }

    public int getItemPrice(ItemId itemId){
        return getItemPrice(itemId, actualMonth);
    }

    public int getItemPrice(ItemId itemId, int month){
        if(!history.containsKey(itemId)){
            return -1;
        }
        return history.get(itemId).get(month);
    }

    public int getActualMonth(){
        return actualMonth;
    }

    public List<Integer> getHistory(ItemId itemId){
        return history.get(itemId);
    }
}
