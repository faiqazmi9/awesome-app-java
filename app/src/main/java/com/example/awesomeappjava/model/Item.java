package com.example.awesomeappjava.model;

public class Item {
    private int imgResId;
    private String title;
    private int likes;
    private int comments;

    public Item(int imgResId, String title) {
        this.imgResId = imgResId;
        this.title = title;
    }

    public int getImgResId() {
        return imgResId;
    }

    public String getTitle() {
        return title;
    }
}
