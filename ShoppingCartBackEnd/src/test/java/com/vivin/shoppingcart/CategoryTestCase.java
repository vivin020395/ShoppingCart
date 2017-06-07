package com.vivin.shoppingcart;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vivin.shoppingcart.dao.CategoryDAO;

import com.vivin.shoppingcart.domain.Category;

public class CategoryTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static CategoryDAO categoryDAO;

	@Autowired
	static Category category;
						
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vivin");
		context.refresh();

		// get the userDAO from context
		categoryDAO = (CategoryDAO) context.getBean("CategoryDAO");

		// get the user from context

		category = (Category) context.getBean("category");
	}
	@Test
	public void createProductTest() {
		
		category.setDescription("Central Processing Unit");
		category.setName("CPU");
		
		boolean flag = categoryDAO.save(category);
		assertEquals(true,flag);
	}
}

