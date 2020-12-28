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
    
    private Connection con;

    //Test Constructor
    public DbServiceImpl(){
        
    }
    public DbServiceImpl(Object con){
        this.con = (Connection)con;
    }


    @Override
    public int alterStm(String query, String[] data) {
        PreparedStatement pstm = null;
        try{
            if(this.con == null)
                this.con =  DriverManager.getConnection(jdbcUrl, user, password);
                pstm = con.prepareStatement(query);
            for (int i = 1; i < data.length+1; i++) {
                pstm.setString(i, data[i-1]);
            }
            int res = pstm.executeUpdate();
            return res;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                if(con != null)
                    con.close();
                if(pstm != null)
                    pstm.close();

                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // //Error
        return -1;
    }
    @Override
    public JSONObject returnStm(String query, String[] data, String[] fields) {
        ResultSet req = null;
        PreparedStatement pstm = null;
        try{
            if(this.con == null)
                this.con =  DriverManager.getConnection(jdbcUrl, user, password);
            pstm = con.prepareStatement(query);
            for (int i = 1; i < data.length+1; i++) {
                pstm.setString(i, data[i-1]);
            }
            req = pstm.executeQuery();
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
        }finally{
            try {
                if(con != null)
                    con.close();
                if(pstm != null)
                    pstm.close();
                if(req != null)
                    req.close();

                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Error
        return new JSONObject();
    }
}
