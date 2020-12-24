package com.coupon.service;

import java.net.URI;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreRestFunctions {
    ArrayList<Store> storeColletion;
    
    @PostMapping(value="/store/create",
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> create(@RequestBody Store request){
        storeColletion.add(request);
		return ResponseEntity.created(URI
		.create("success")).body("{\"success\":true}");
    }
    @GetMapping(value="/getStores")
    public String getAllCoupons(){
        JSONObject res = new JSONObject();
        res.put("coupons", storeColletion);
        return res.toString();
    }
    //Todo -> delete all coupons after delete store;

}
