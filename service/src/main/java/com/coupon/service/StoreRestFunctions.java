package com.coupon.service;

import java.net.URI;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreRestFunctions {
    String fields[];

    @Autowired
    private DbService dbService;
    
    public StoreRestFunctions()
    {
        fields = new String[]{"name", "id", "category"};
    }

    @PostMapping(value="/store/create",
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String create(@RequestBody Store request){
        String command = "INSERT INTO stores(name,category) VALUES (?,?)";
        String data[] = {request.name, request.category};
        int result = dbService.alterStm(command, data);
		JSONObject res = new JSONObject();
        if(result == 1)
            res.put("success", true);
        else
        res.put("error", true);
        return res.toString();
    }
    @GetMapping(value="/getStores")
    public String getAllCoupons(){
        String command = "SELECT * FROM stores";
        JSONObject res = dbService.returnStm(command, new String[]{}, fields);
        return res.toString();
    }
    //Todo -> delete all coupons after delete store;

}
