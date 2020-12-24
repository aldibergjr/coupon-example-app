package com.coupon.service;
import java.net.URI;
import java.util.ArrayList;
import org.json.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CouponRestFunctions {

    public static ArrayList<Coupon> couponCollection = new ArrayList<>();
	@PostMapping(value="/coupon/create",
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> create(@RequestBody Coupon request){
        couponCollection.add(request);
		return ResponseEntity.created(URI
		.create("success")).body("{\"success\":true}");
    }
    @GetMapping(value="/coupon/get")
    public String getCouponById(@RequestParam(value="id", defaultValue = "-1") String id){
        Coupon aux = null;
        System.out.println("looking for id: " + id);
        //todo CHANGE REPOSITORY TYPE
        for (Coupon coupon : couponCollection) {
            if(coupon.id == Integer.parseInt(id))
                aux = coupon;
        }
        JSONObject res = ((new JSONObject(aux)));
        return res.toString();
    }

    @PutMapping(value="/coupon/toggle")
    public String changeState(@RequestParam(value="id", defaultValue = "-1") String id){
        
        //todo CHANGE REPOSITORY TYPE
        for (Coupon coupon : couponCollection) {
            if(coupon.id == Integer.parseInt(id))
                coupon.isActive = !coupon.isActive;
        }

        JSONObject res = new JSONObject();
        res.put("success", true);
        return res.toString();
    }
    @DeleteMapping(value="/coupon/delete")
    public String deleteById(@RequestParam(value="id", defaultValue = "-1") String id){
        
        //todo CHANGE REPOSITORY TYPE
        for (Coupon coupon : couponCollection) {
            if(coupon.id == Integer.parseInt(id))
                couponCollection.remove(coupon);
        }

        JSONObject res = new JSONObject();
        res.put("success", true);
        return res.toString();
    }
    @GetMapping(value="/getCoupons")
    public String getAllCoupons(){
        JSONObject res = new JSONObject();
        res.put("coupons", couponCollection);
        return res.toString();
    }

}
