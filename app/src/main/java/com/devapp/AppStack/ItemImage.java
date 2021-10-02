package com.devapp.AppStack;

public class ItemImage {
    private int id;
    private int image;

    public ItemImage(int id, int image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ItemImage{" +
                "id=" + id +
                ", image=" + image +
                '}';
    }
}
