package model;

import java.awt.image.BufferedImage;

public class Item {
    private ItemId id;
    private String name;
    private String description;
    private int weight;
    private boolean isRawMaterial;
    private BufferedImage image;
   

    public Item(ItemId id, String name, String description, int weight, boolean isRawMaterial, BufferedImage image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.isRawMaterial = isRawMaterial;
        this.image = image;
    }

    public ItemId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isRawMaterial() {
        return isRawMaterial;
    }

    public BufferedImage getImage() {
        return image;
    }
}
