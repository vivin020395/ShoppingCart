package com.vivin.shoppingcart;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.dao.ShippingAddressDAO;
import com.vivin.shoppingcart.dao.UserDAO;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.ShippingAddress;
import com.vivin.shoppingcart.domain.User;

public class ShippingTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static ShippingAddressDAO shippingAddressDAO;
	@Autowired
	static ShippingAddress shippingAddress;
	@Autowired
	static UserDAO userDAO;
	@Autowired
	static User user;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vivin");
		context.refresh();

		// get the categoryDAO from context
		shippingAddressDAO = (ShippingAddressDAO) context.getBean("shippingAddressDAO");

		// get the category from context

		shippingAddress = (ShippingAddress) context.getBean("shippingAddress");
	}

	@Test
	public void createShippingTestCase() {

		shippingAddress.setUserid("qwerty");
		shippingAddress.setMobileno("9944981440");
		shippingAddress.setName("Vivin Kumar");
		shippingAddress.setAddressLine1("Niit");
		shippingAddress.setAddressLine2("Peelamedu");
		shippingAddress.setCity("Coimbatore");
		shippingAddress.setState("TamilNadu");
		shippingAddress.setZipCode("641608");
		shippingAddress.setCountry("India");

		boolean flag = shippingAddressDAO.save(shippingAddress);

		// error - if there is in runtime errors - Red mark
		// success - if expected and actual is same - green mark
		// fail - if expected and actual is different - blue mark
		assertEquals("createShippingTestCase", true, flag);

	}

}
