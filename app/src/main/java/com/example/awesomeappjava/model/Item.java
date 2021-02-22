package com.example.awesomeappjava.model;

public class Item {
    private int imgResId;
    private String title;
    private String description;

    public Item(int imgResId, String title, String description) {
        this.imgResId = imgResId;
        this.title = title;
        this.description = description;
    }

    public int getImgResId() {
        return imgResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
