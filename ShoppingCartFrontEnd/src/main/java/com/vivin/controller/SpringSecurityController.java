package com.vivin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vivin.shoppingcart.dao.CategoryDAO;
import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.dao.SupplierDAO;
import com.vivin.shoppingcart.domain.Category;
import com.vivin.shoppingcart.domain.MyCart;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.Supplier;

@Controller
public class SpringSecurityController {

	public static Logger log = LoggerFactory.getLogger(SpringSecurityController.class);

	@Autowired
	private HttpSession session;

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private Category category;

	@Autowired
	private SupplierDAO supplierDAO;
	@Autowired
	private Supplier supplier;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private Product product;

	@Autowired
	private MyCartDAO cartDAO;

	@Autowired
	private MyCart myCart;

	// authentication-failure-forward-url="/loginError"
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(Model model) {
		log.debug("Starting of the method loginError");
		session.setAttribute("isUserLoginError", "true");
		session.setAttribute("isLoginError", "Invalid Credentials.Please try again.");
		// model.addAttribute("invalidCredentials", "true");
		log.debug("Ending of the method loginError");
		return "redirect:/LoginPage";

	}

	// <security:access-denied-handler error-page="/accessDenied" />
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		log.debug("Starting of the method accessDenied");
		model.addAttribute("errorMessage", "You are not authorized to access this page");
		model.addAttribute("isUserClickedHomePage", "true");
		log.debug("Ending of the method accessDenied");
		return "Home";

	}

	// @RequestMapping(value = "validate", method = RequestMethod.GET)
	@RequestMapping(value = "/checkRole", method = RequestMethod.GET)
	public ModelAndView checkRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("starting of the method validate");
		ModelAndView mv = new ModelAndView("Home");
		// session = request.getSession(true);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		mv.addObject("categoryList", categoryDAO.list());
		mv.addObject("category", category);

		mv.addObject("supplierList", supplierDAO.list());
		mv.addObject("supplier", supplier);

		mv.addObject("productList", productDAO.list());
		mv.addObject("product", product);
		// mv.addObject("isUserAtHomePage", "true");

		String userID = auth.getName();
		System.out.println(userID);
		session.setAttribute("loggedInUserID", userID);

		if (request.isUserInRole("ROLE_ADMIN")) {
			log.debug("admin");
			mv.addObject("isAdmin", "true");
			session.setAttribute("isAdminLoggedIn", "true");
			session.setAttribute("role", "ROLE_ADMIN");
			session.setAttribute("isAdmin", true);
			session.setAttribute("user", userID);
			mv.addObject("isUserAtHomePage", "false");
			return mv;

		} else if (request.isUserInRole("ROLE_USER")) {

			log.debug("customer");
			session.setAttribute("isAdmin", false);
			session.setAttribute("isUserLoggedIn", "true");
			session.setAttribute("myCart", myCart);
			mv.addObject("isAdmin", "false");
			session.setAttribute("role", "ROLE_USER");
			session.setAttribute("loggedInUserID", userID);
			String loggedInUserID = (String) session.getAttribute("loggedInUserID");
			int cartSize = cartDAO.list(loggedInUserID).size();
			session.setAttribute("cartSize", cartSize);
			mv.addObject("isUserAtHomePage", "true");

			return mv;
		} else {
			mv = new ModelAndView("loginError");
			return mv;
		}
	}

	@RequestMapping("/secure_logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.getContext().setAuthentication(null);
		ModelAndView mv = new ModelAndView("redirect:/HomePage");
		return mv;
	}

}
