package com.vivin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vivin.shoppingcart.dao.CategoryDAO;
import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.dao.ShippingAddressDAO;
import com.vivin.shoppingcart.dao.SupplierDAO;
import com.vivin.shoppingcart.dao.UserDAO;
import com.vivin.shoppingcart.domain.Category;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.ShippingAddress;
import com.vivin.shoppingcart.domain.Supplier;
import com.vivin.shoppingcart.domain.User;

@Controller
public class UserController {

	// Logger Statements
	// SLF4J - Simple Logging Facade for Java
	private static Logger log = LoggerFactory.getLogger(UserController.class);

	// To connect to user in backend
	@Autowired
	UserDAO userDAO;
	@Autowired
	User user;

	@Autowired
	Category category;
	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;

	@Autowired
	SupplierDAO supplierDAO;
	@Autowired
	Supplier supplier;

	@Autowired
	ShippingAddressDAO shippingAddressDAO;
	@Autowired
	ShippingAddress shippingAddress;

	@Autowired
	HttpSession session;
	// Get the user id and password from login page
	// Send these values to userDao.validate
	// based on response, you can navigate to either login.jsp or home.jsp

	// Whenever we configure spring security - we can remove this method

	/*
	 * @RequestMapping("/validate") public ModelAndView
	 * login(@RequestParam("userId") String id, @RequestParam("pass") String
	 * password) {
	 * 
	 * ModelAndView mv = new ModelAndView("Home");
	 * log.debug("Starting of the method login");
	 * 
	 * log.info("You are login with id : " + id); if (userDAO.validate(id,
	 * password) == true) { log.debug("Valid credentials"); user =
	 * userDAO.get(id); mv.addObject("message", "Welcome " + user.getName() +
	 * "!");
	 * 
	 * mv.addObject("categoryList", categoryDAO.list());
	 * mv.addObject("category", categoryDAO);
	 * 
	 * mv.addObject("supplierList", supplierDAO.list());
	 * mv.addObject("supplier", supplierDAO);
	 * 
	 * // check whether user role is admin or user
	 * 
	 * if (user.getRole().equals("ROLE_ADMIN")) { mv = new
	 * ModelAndView("admin/AdminHome"); return mv; } else { mv = new
	 * ModelAndView("Home"); return mv; } } else { log.debug("Invalid user");
	 * mv.addObject("message", "invalid credentials"); }
	 * log.debug("Ending of the method login"); return mv;
	 * 
	 * }
	 */

	@RequestMapping("/newUser")
	public String register(Model model, @RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("contact") String phone,
			@RequestParam("userid") String id) {

		log.debug("Starting of the method register");
		log.debug("Assigning values");
		for (User user : userDAO.list()) {
			if (id.equals(user.getId())) {
				model.addAttribute("idAlreadyExists", "true");
				return "Register";
			}
		}
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		user.setContact(phone);
		user.setEmail(email);
		user.setRole("ROLE_USER");

		log.info("You are signing up with username : " + id);

		if (userDAO.save(user) == true) {
			log.debug("saving credentials");
			user = userDAO.get(id);
			model.addAttribute("message", "Account created.Please Login with id and password.");
			model.addAttribute("isUserClickedHomePage", "true");
			model.addAttribute("categoryList", categoryDAO.list());
			model.addAttribute("category", categoryDAO);
			model.addAttribute("supplierList", supplierDAO.list());
			model.addAttribute("supplier", supplierDAO);

		} else {
			log.debug("Error");
			model.addAttribute("message", "invalid credentials");
		}
		log.debug("Ending of the method login");
		return "Home";
	}

	@RequestMapping("/updateUser")
	public String updateUser(Model model, @RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("contact") String contact, @RequestParam("password") String password) {
		String loggedInUser = (String) session.getAttribute("loggedInUserID");
		user = userDAO.get(loggedInUser);
		user.setName(name);
		user.setEmail(email);
		user.setContact(contact);
		user.setPassword(password);
		boolean result = userDAO.update(user);
		if (result == true)
			model.addAttribute("message", "Account Updated");
		else if (result == false)
			model.addAttribute("message", "Update Failed");
		else
			model.addAttribute("message", "");
		model.addAttribute("user", loggedInUser);
		model.addAttribute("userName", user.getName());
		model.addAttribute("userContact", user.getContact());
		model.addAttribute("userEmail", user.getEmail());
		model.addAttribute("userPass", user.getPassword());
		model.addAttribute("isUserClickedAccountInfo", "true");
		shippingAddress = shippingAddressDAO.getByUserId(loggedInUser);
		model.addAttribute("address", shippingAddress);
		model.addAttribute("addressList", shippingAddressDAO.list(loggedInUser));
		return "Home";
	}

}