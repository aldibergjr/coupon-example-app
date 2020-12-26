package com.coupon.service;



import org.json.JSONObject;

public interface DbService {
    public int alterStm(String querry, String data[]);
    public JSONObject returnStm(String query, String data[], String fields[]);
}
