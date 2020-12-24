package com.coupon.service;

public class Coupon {
    boolean isActive;
    String name;
    int store_ref;
    int id;

    
    public Coupon(int id , String name, int store_ref){
        this.isActive = true;
        this.id = id;
        this.name = name;
        this.store_ref = store_ref;
    }

    public boolean isIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStore_ref() {
        return this.store_ref;
    }

    public void setStore_ref(int store_ref) {
        this.store_ref = store_ref;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
