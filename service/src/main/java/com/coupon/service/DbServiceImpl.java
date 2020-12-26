package com.coupon.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class DbServiceImpl implements DbService {
    @Value("${database.url}")
    private String jdbcUrl;
    @Value("${database.username}")
    private String user;
    @Value("${database.password}")
    private String password;
    @Resource
    private Environment env;
     
    @Override
    public int alterStm(String query, String[] data) {
        try(Connection con =  DriverManager.getConnection(jdbcUrl, user, password);
            PreparedStatement pstm = con.prepareStatement(query)){
                for (int i = 1; i < data.length+1; i++) {
                    pstm.setString(i, data[i-1]);
                }
                int res = pstm.executeUpdate();
                return res;
        }catch(SQLException e){
            e.printStackTrace();
        }
        // //Error
        return -1;
    }
    @Override
    public JSONObject returnStm(String query, String[] data, String[] fields) {
        System.out.println(jdbcUrl);
        try(Connection con =  DriverManager.getConnection(jdbcUrl, user, password);
            PreparedStatement pstm = con.prepareStatement(query)){
                for (int i = 1; i < data.length+1; i++) {
                    pstm.setString(i, data[i-1]);
                }
                
                ResultSet req = pstm.executeQuery();
                ArrayList<JSONObject> collection = new ArrayList<>();
                while(req.next()){
                    JSONObject obj = new JSONObject();
                    for (int i = 0; i < fields.length; i++) {
                        obj.put(fields[i], req.getString(fields[i]));
                    }
                    collection.add(obj);
                }
                JSONObject res = new JSONObject();
                res.put("data", collection);
                return res;
        }catch(SQLException e){
            e.printStackTrace();
        }
        //Error
        return new JSONObject();
    }
}
