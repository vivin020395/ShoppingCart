package com.vivin.shoppingcart;

import static org.junit.Assert.*;
import java.sql.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.domain.MyCart;

public class CartTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static MyCartDAO cartDAO;

	@Autowired
	static MyCart cart;
						
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vivin");
		context.refresh();

		// get the userDAO from context
		cartDAO = (MyCartDAO) context.getBean("cartDAO");

		// get the user from context

		cart = (MyCart) context.getBean("mycart");
	}
	@Test
	public void createProductTest() {
		
		cart.setId(100);
		cart.setName("Samsung 850 EVO 2TB");
		cart.setPrice("41500");
		cart.setQuantity(2);
		cart.setUserId("abcde");
		cart.setStatus('N');	
		cart.setDate(new Date(System.currentTimeMillis()));		
		
		boolean flag = cartDAO.save(cart);
		assertEquals(true,flag);
	}
}

