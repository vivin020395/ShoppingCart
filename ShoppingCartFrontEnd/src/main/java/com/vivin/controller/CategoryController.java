
package com.vivin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vivin.shoppingcart.dao.CategoryDAO;
import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.domain.Category;
import com.vivin.shoppingcart.domain.Product;

@Controller
public class CategoryController {

	// Logger Statements
	// SLF4J - Simple Logging Facade for Java
	private static Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	HttpSession session;

	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	Category category;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	Product product;

	// crud category
	@RequestMapping("/manage_category_add")
	public ModelAndView createCategory(@RequestParam("name") String name,
			@RequestParam("description") String description) {
		log.debug("Starting of create category");
		ModelAndView mv = new ModelAndView("Home");

		category.setName(name);
		category.setDescription(description);
		categoryDAO.save(category);
		mv.addObject("isAdminClickedCategories", "true");
		mv.addObject("isAdmin", "true");
		mv.addObject("isUserClickedAdd", "true");

		// get all categories
		List<Category> categoryList = categoryDAO.list();
		// attach to session
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("category", category);

		// Before calling save method, check whether category_id already exists
		// in db
		// if it does not exist, then only call save method.
		log.debug("Ending of create category");
		return mv;

	}

	// attach data to url we use @PathVariable
	@RequestMapping("/manage_category_delete/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") String id) {

		log.debug("Starting of delete Category");
		log.info("You are about to delete a category with id : " + id);

		ModelAndView mv = new ModelAndView("redirect:/manageCategories");
		// Check whether products are there for this category or not
		int noOfProducts = productDAO.getAllProductsByCategoryId(id).size();
		if (noOfProducts != 0) {
			log.debug("Few products are there under this category, you cannot delete!");
			session.setAttribute("categoryMessage",
					"Cannot Delete Category.There are " + noOfProducts + " products under  category " + id);
			return mv;
		}

		if (categoryDAO.delete(id)) {
			mv.addObject("categoryMessage", "Successfullly deleted");
		} else {
			mv.addObject("categoryMessage", "Failed to delete");
		}
		log.debug("Ending of delete Category");

		return mv;
	}

	// Edit category
	@RequestMapping("/manage_category_edit/{id}")
	public ModelAndView editCategory(@PathVariable("id") String id) {
		log.debug("Starting of editCategory");
		log.info("You are about to edit a category with id : " + id);

		category = categoryDAO.getCategoryById(id);

		// Selected category details we have to store in another instance
		// i.e., ModelAndView instance
		ModelAndView mv = new ModelAndView("redirect:/manageCategories");
		session.setAttribute("selectedCategory", category);

		log.debug("Ending of editCategory");

		return mv;
	}

	@PostMapping("/manage_category_update")
	public ModelAndView updateCategory(@RequestParam("name") String name,
			@RequestParam("description") String description) {
		log.debug("Starting of updateCategory");
		ModelAndView mv = new ModelAndView("redirect:/manageCategories");

		category.setName(name);
		category.setDescription(description);

		mv.addObject("isAdminClickedCategories", "true");
		mv.addObject("isAdmin", "true");
		if (categoryDAO.update(category))
			mv.addObject("Edited Successfully");
		else
			mv.addObject("Editing Failed");

		// Before calling save method, check whether category_id already exists
		// in db
		// if it does not exist, then only call save method.
		log.debug("Ending of updateCategory");
		return mv;
	}

}
