package com.coupon.service;

public class Store {
    int id;
    String name;
    String category;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Store(int id, String name, String category){
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
