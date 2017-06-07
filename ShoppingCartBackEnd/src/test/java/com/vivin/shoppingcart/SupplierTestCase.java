package com.vivin.shoppingcart;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.vivin.shoppingcart.dao.SupplierDAO;
import com.vivin.shoppingcart.domain.Supplier;

public class SupplierTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static SupplierDAO supplierDAO;

	@Autowired
	static Supplier supplier;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vivin");
		context.refresh();

		// get the userDAO from context
		supplierDAO = (SupplierDAO) context.getBean("SupplierDAO");

		// get the user from context

		supplier = (Supplier) context.getBean("supplier");
	}
	
	@Test
	public void createSupplierTest() {
		supplier.setName("Gigabyte");
		supplier.setAddress("USA");
		supplier.setPhone("5154997800");
		
		boolean flag = supplierDAO.save(supplier);
		assertEquals(true, flag);
	}

}
