package com.coupon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@AutoConfigureMockMvc
@SpringBootTest
class CouponServiceApplicationTests {
	
	
	@MockBean
	Connection con;
	@MockBean
	PreparedStatement pstm;
	@MockBean
	ResultSet rs;

	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		//Query return
		when(pstm.executeQuery())
		.thenReturn(rs);
		//Update return
		when(pstm.executeUpdate())
		.thenReturn(1);

		//Result set tests
		when(rs.next())
		//one data
		.thenReturn(true).thenReturn(false)
		//multiple
		.thenReturn(true).thenReturn(true).thenReturn(false);

		when(rs.getString(any(String.class))).thenReturn("fields_change");

		
		when(con.prepareStatement(anyString())).thenReturn(pstm);
	}


	@Test
	void contextLoads() throws Exception {
		Coupon coupon = new Coupon(1, "20% off", 1);
		Store store = new Store(1, "Amazon", "Marketplace");
		CouponRestFunctions cr = new CouponRestFunctions(con);
		StoreRestFunctions sr = new StoreRestFunctions(con);
		//coupon functions
		String result = cr.create(coupon);
		assertEquals("{\"success\":true}", result);
		result = cr.getCouponById("1");
		assertEquals("{\"data\":[{\"name\":\"fields_change\",\"id\":\"fields_change\",\"store_ref\":\"fields_change\",\"isActive\":\"fields_change\"}]}", result);

		//StoreFunction
		result = sr.create(store);
		assertEquals("{\"success\":true}", result);
		result = sr.getAllStores();
		assertEquals("{\"data\":[{\"name\":\"fields_change\",\"id\":\"fields_change\",\"category\":\"fields_change\"},{\"name\":\"fields_change\",\"id\":\"fields_change\",\"category\":\"fields_change\"}]}", result);

	}

}
