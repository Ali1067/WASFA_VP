package com.qmobileme.wasfa.Extra;

public class Item {
    private int id;
    private String title;
    private String url;
    private int count;

    public Item(String title, int count, int id) {
        this.title = title;
        this.count = count;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
