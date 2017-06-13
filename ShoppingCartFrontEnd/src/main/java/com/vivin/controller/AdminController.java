
package com.vivin.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.vivin.shoppingcart.dao.CategoryDAO;
import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.dao.SupplierDAO;
import com.vivin.shoppingcart.dao.UserDAO;
import com.vivin.shoppingcart.domain.Category;
import com.vivin.shoppingcart.domain.MyCart;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.Supplier;
import com.vivin.shoppingcart.domain.User;

import antlr.collections.List;

@Controller
public class AdminController {
	@Autowired
	HttpSession session;
	@Autowired
	Category category;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	Supplier supplier;
	@Autowired
	SupplierDAO supplierDAO;
	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	User user;
	@Autowired
	UserDAO userDAO;
	@Autowired
	MyCart cart;
	@Autowired
	MyCartDAO cartDAO;

	// Logger Statements
	// SLF4J - Simple Logging Facade for Java
	private static Logger log = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping("/manageCategories")
	public ModelAndView manageCategories() {
		log.debug("Starting of the method manageCategories");
		ModelAndView mv = new ModelAndView("Home");
		String userID = (String) session.getAttribute("loggedInUserID");
		if (userID == null) {
			mv = new ModelAndView("Login");
			return mv;
		}
		user = userDAO.get(userID);
		if (user.getRole().equals("ROLE_USER")) {
			mv = new ModelAndView("/HomePage");
			return mv;
		}
		mv.addObject("isAdminClickedCategories", "true");
		mv.addObject("isAdmin", "true");
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		log.debug("Ending of the method manageCategories");
		return mv;
	}

	@RequestMapping("/manageSuppliers")
	public ModelAndView manageSuppliers() {
		ModelAndView mv = new ModelAndView("Home");
		String userID = (String) session.getAttribute("loggedInUserID");
		if (userID == null) {
			mv = new ModelAndView("Login");
			return mv;
		}
		user = userDAO.get(userID);		
		if (user.getRole().equals("ROLE_USER")) {
			mv = new ModelAndView("/HomePage");
			System.out.println(user.getRole() + " inside if statment");
			return mv;
		}
		mv.addObject("isAdminClickedSuppliers", "true");
		mv.addObject("isAdmin", "true");
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("supplier", supplier);
		log.debug("Ending of the method manageSuppliers");
		return mv;
	}

	@RequestMapping("/manageProducts")
	public ModelAndView manageProducts() {
		ModelAndView mv = new ModelAndView("Home");
		String userID = (String) session.getAttribute("loggedInUserID");
		if (userID == null) {
			mv = new ModelAndView("Login");
			return mv;
		}
		user = userDAO.get(userID);
		System.out.println(user.getId());
		System.out.println(user.getRole());
		log.debug(user.getId());
		if (user.getRole().equals("ROLE_USER")) {
			mv.addObject("isUserClickedHomePage", "true");
			mv.addObject("product", product);
			mv.addObject("productList", productDAO.list());
			mv.addObject("supplier", supplier);
			mv.addObject("supplierList", supplierDAO.list());
			mv.addObject("category", category);
			mv.addObject("categoryList", categoryDAO.list());
			return mv;
		}
		log.debug("Starting of the method manageProducts");
		mv.addObject("isAdminClickedProducts", "true");
		mv.addObject("isAdmin", "true");
		mv.addObject("product", product);
		mv.addObject("productList", productDAO.list());
		mv.addObject("supplier", supplier);
		mv.addObject("supplierList", supplierDAO.list());
		mv.addObject("category", category);
		mv.addObject("categoryList", categoryDAO.list());
		session.setAttribute("isUserLoggedIn", "false");
		log.debug("Ending of the method manageProducts");
		return mv;
	}

	@RequestMapping("/manageUsers")
	public String paymentInfo(Model model) {
		model.addAttribute("userList", userDAO.list());
		model.addAttribute("isAdminClickedUsers", "true");
		return "Home";

	}

	/*
	 * @RequestMapping("/status") public String cartStatus(Model model) {
	 * log.debug("HELLO WORLD start of cart"); model.addAttribute("cartList",
	 * cartDAO.list()); char status; for (MyCart cart : cartDAO.list()) { status
	 * = cart.getStatus(); model.addAttribute("status", status); }
	 * model.addAttribute("P", 'P'); model.addAttribute("N", 'N');
	 * model.addAttribute("isAdminClickedCart", "true");
	 * model.addAttribute("message", "Displaying all Cart"); return "Home"; }
	 */

	// User Add/Update and delete
	@RequestMapping("/manage-user-delete/{id}")
	public ModelAndView manageUserDelete(@PathVariable("id") String id) {
		log.debug("Starting of method manageUserDelete");
		ModelAndView mv = new ModelAndView("redirect:/manageUsers");
		log.info("You are about to delete user");
		String loggedInUser = (String) session.getAttribute("loggedInUserID");
		log.debug("Logged in user id : " + loggedInUser);
		log.debug("Delete user id : " + id);
		int userCartSize = cartDAO.list(id).size();

		if (loggedInUser.equals(id)) {
			session.setAttribute("errorMessage", "Cannot delete loggedIn User. ");
			log.info("Tried to delete loggedin User Id!");
		} else if (userCartSize > 0) {
			session.setAttribute("errorMessage", "There are few products in selected User cart. Cannot delete! ");
			log.info("Tried to delete User Id with products in cart!");
		} else if (userDAO.delete(id)) {
			mv.addObject("successMessage", "Successfully deleted user with id: " + id + ". ");
		} else {
			mv.addObject("errorMessage", "Falied to delete user with id: " + id + ". ");
		}

		log.debug("Ending of method manageUserDelete");
		return mv;
	}

	@RequestMapping("/manage-user-edit/{id}")
	public ModelAndView manageUserEdit(@PathVariable("id") String id) {
		log.debug("Starting of method manageUserEdit");
		ModelAndView mv = new ModelAndView("redirect:/manageUsers");
		log.info("You are about to delete user");
		user = userDAO.get(id);

		session.setAttribute("selectedUser", user);
		session.setAttribute("isAdminClickedEditUser", "true");

		log.debug("Ending of method manageUserEdit");
		return mv;
	}

	@RequestMapping("/manage-user-update")
	public ModelAndView manageUserUpdate(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("contact") String contact,
			@RequestParam("role") String role, @RequestParam("email") String email) {
		log.debug("Starting of method manageUserUpdate");
		ModelAndView mv = new ModelAndView("redirect:/manageUsers");
		log.info("You are about to update user");

		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setContact(contact);
		user.setEmail(email);
		user.setRole(role);
		if (userDAO.update(user)) {
			log.info("Update user Successful");
			session.setAttribute("successMessage", "Update  user Successful. ");
			session.setAttribute("isAdminClickedEditUser", "false");
		} else {
			log.info("Update user falied");
			session.setAttribute("errorMessage", "Update  user failed. ");
		}

		log.debug("Ending of method manageUserUpdate");
		return mv;
	}

	@RequestMapping("/manage-user-add")
	public ModelAndView manageUserAdd(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("contact") String contact,
			@RequestParam("role") String role, @RequestParam("email") String email,
			@RequestParam("address") String address) {
		log.debug("Starting of method manageUserAdd");
		ModelAndView mv = new ModelAndView("redirect:/manageUsers");
		log.info("You are about to add user");

		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		user.setContact(contact);
		user.setRole(role);
		if (userDAO.save(user)) {
			log.info("Add user Successful");
			session.setAttribute("successMessage", "Add  user Successful. ");
		} else {
			log.info("Add user falied");
			session.setAttribute("errorMessage", "Add  user failed. ");
		}

		log.debug("Ending of method manageUserAdd");
		return mv;
	}

}
