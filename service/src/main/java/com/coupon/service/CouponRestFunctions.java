package com.coupon.service;
import java.util.ArrayList;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CouponRestFunctions {
    String fields[];
    @Autowired
    private DbService dbService;
    public CouponRestFunctions(){
        fields = new String[]{"name" , "id", "store_ref", "isActive"};
    }

	@PostMapping(value="/coupon/create",
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String create(@RequestBody Coupon request){
        String command = "INSERT INTO coupons(name,store_ref,isActive) VALUES (?,cast(? as int),true)";
        String data[] = {request.name, "" + request.store_ref};
        int result = dbService.alterStm(command, data);
		JSONObject res = new JSONObject();
        if(result == 1)
            res.put("success", true);
        else
        res.put("error", true);
        return res.toString();
    }
    @GetMapping(value="/coupon/get")
    public String getCouponById(@RequestParam(value="id", defaultValue = "-1") String id){
        
        System.out.println("looking for id: " + id);
        String command = "SELECT * FROM coupons WHERE id="+ id.trim();
        String data[] = {};
        JSONObject res = dbService.returnStm(command, data, fields);
        return res.toString();
    }

    @PutMapping(value="/coupon/toggle")
    public String changeState(@RequestParam(value="id", defaultValue = "-1") String id){
        

        String command = "UPDATE coupons SET isActive = NOT isActive WHERE id= ?"+ id.trim();
        String data[] = {};
        int result = dbService.alterStm(command, data);

        JSONObject res = new JSONObject();
        if(result == 1)
            res.put("success", true);
        else
        res.put("error", true);
        return res.toString();
    }
    @DeleteMapping(value="/coupon/delete")
    public String deleteById(@RequestParam(value="id", defaultValue = "-1") String id){
        
        String command = "DELETE FROM coupons WHERE id= ?"+ id.trim();
        String data[] = {};
        int result = dbService.alterStm(command, data);

        JSONObject res = new JSONObject();
        if(result == 1)
            res.put("success", true);
        else
        res.put("error", true);
        return res.toString();
    }

    @GetMapping(value="/getCoupons")
    public String getAllCoupons(){
        String command = "SELECT * FROM coupons";
        JSONObject res = dbService.returnStm(command, new String[]{}, fields);
        return res.toString();
    }

}
