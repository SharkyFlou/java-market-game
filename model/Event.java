package model;

public class Event {
    private EventId id;
    private String name;
    private String description;
    private int startDay;
    private int duration;
    private ItemId[] affectedItems;
    private int probability;
    private int unlockDay;
    private int cooldown;
    private float multiplierBase;
    private float multiplierProgressive;

    public Event(EventId id, String name, String description, int duration, ItemId[] affectedItems, int probability, int unlockDay, int cooldown, float multiplierBase, float multiplierProgressive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDay = -1;
        this.duration = duration;
        this.affectedItems = affectedItems;
        this.probability = probability;
        this.unlockDay = unlockDay;
        this.cooldown = cooldown;
        this.multiplierBase = multiplierBase;
        this.multiplierProgressive = multiplierProgressive;
    }

    public EventId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getDuration() {
        return duration;
    }

    public ItemId[] getAffectedItems() {
        return affectedItems;
    }

    public int getProbability() {
        return probability;
    }

    public int getUnlockDay() {
        return unlockDay;
    }

    public int getCooldown() {
        return cooldown;
    }

    public float getMultiplierBase() {
        return multiplierBase;
    }

    public float getMultiplierProgressive() {
        return multiplierProgressive;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }
}
