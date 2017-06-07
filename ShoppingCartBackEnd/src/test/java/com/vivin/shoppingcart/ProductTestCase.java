package com.vivin.shoppingcart;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vivin.shoppingcart.dao.ProductDAO;

import com.vivin.shoppingcart.domain.Product;

public class ProductTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static ProductDAO productDAO;
	@Autowired
	static Product product;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vivin");
		context.refresh();

		// get the categoryDAO from context
		productDAO = (ProductDAO) context.getBean("productDAO");

		// get the category from context

		product = (Product) context.getBean("product");

	}

	@Test
	public void createProductTestCase() {

		product.setId("25");
		product.setName("Corsair Vengeance LPX 2x6 GB");
		product.setDescription("RAM");
		product.setPrice("9000");
		product.setCategory_id("3");
		product.setSupplier_id("8");

		boolean flag = productDAO.save(product);

		// error - if there is in runtime errors - Red mark
		// success - if expected and actual is same - green mark
		// fail - if expected and actual is different - blue mark
		assertEquals("createProductTestCase", true, flag);

	}

	/*@Test
	public void updateProductTestCase() {

		product.setId("Samsung S8 64gb");
		product.setName("Samsung S8 64gb");
		product.setDescription("Brand new Samsung S8 64gb blue");
		product.setPrice("71999");
		product.setCategory_id("Mobiles");
		product.setSupplier_id("Sangeetha");

		boolean flag = productDAO.update(product);

		// error - if there is in runtime errors - Red mark
		// success - if expected and actual is same - green mark
		// fail - if expected and actual is different - blue mark
		assertEquals("updateProductTestCase", true, flag);

	}

	@Test
	public void listAllProductTestCase() {
		int actualSize = productDAO.list().size();
		assertEquals(2, actualSize);
	}*/

}
