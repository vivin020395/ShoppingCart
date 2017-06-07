package com.vivin.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.dao.ShippingAddressDAO;
import com.vivin.shoppingcart.dao.UserDAO;
import com.vivin.shoppingcart.domain.ShippingAddress;
import com.vivin.shoppingcart.domain.User;

@Controller
public class HomeController {

	// http://localhost:8080/ShoppingCart/
	@Autowired
	UserDAO userDAO;
	@Autowired
	User user;
	@Autowired
	MyCartDAO cartDAO;
	@Autowired
	ShippingAddressDAO shippingAddressDAO;
	@Autowired
	ShippingAddress shippingAddress;
	@Autowired
	private HttpSession session;

	@RequestMapping("/")
	public String goToHome(Model model)

	{
		model.addAttribute("isUserClickedHomePage", "true");
		return "Home";
	}

	@RequestMapping("/HomePage")
	public String goHome(Model model)

	{
		model.addAttribute("isUserClickedHomePage", "true");
		return "Home";
	}

	@RequestMapping("/CartPage")
	public String cartPage(Model model) {
		model.addAttribute("isUserClickedCart", "true");
		return "Home";
	}

	@RequestMapping("/LoginPage")
	public String loginPage(Model model) {
		session.getAttribute("isUserLoginError");
		session.getAttribute("isLoginError");
		return "Login";
	}

	@RequestMapping("/RegistrationPage")
	public String registrationPage(Model model) {
		model.addAttribute("isUserClickedRegister", "true");

		return "Register";
	}

	@RequestMapping("/CPUPage")
	public String cpuPage(Model model) {
		model.addAttribute("isUserClickedCPU", "true");

		return "Home";
	}

	@RequestMapping("/MotherboardPage")
	public String motherboardPage(Model model) {
		model.addAttribute("isUserClickedMotherboard", "true");

		return "Home";
	}

	@RequestMapping("/MemoryPage")
	public String memoryPage(Model model) {
		model.addAttribute("isUserClickedMemory", "true");

		return "Home";
	}

	@RequestMapping("/StoragePage")
	public String storagePage(Model model) {
		model.addAttribute("isUserClickedStorage", "true");

		return "Home";
	}

	@RequestMapping("/VideoCardPage")
	public String videoCardPage(Model model) {
		model.addAttribute("isUserClickedVideoCard", "true");

		return "Home";
	}

	@RequestMapping("/OrderHistoryPage")
	public String OrderHistoryPage(Model model) {
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		user = userDAO.get(loggedInUserid);
		model.addAttribute("user", loggedInUserid);
		model.addAttribute("userName", user.getName());
		model.addAttribute("userContact", user.getContact());
		model.addAttribute("userEmail", user.getEmail());
		model.addAttribute("isUserClickedOrderHistory", "true");
		return "Home";
	}

	@RequestMapping("/paymentInfo")
	public String paymentInfo(Model model) {
		model.addAttribute("isUserClickedPayment", "true");
		return "Home";
	}

	@RequestMapping("/accountInfo")
	public String accountInfo(Model model) {
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		user = userDAO.get(loggedInUserid);
		model.addAttribute("user", loggedInUserid);
		model.addAttribute("userName", user.getName());
		model.addAttribute("userContact", user.getContact());
		model.addAttribute("userEmail", user.getEmail());
		model.addAttribute("userPass", user.getPassword());
		model.addAttribute("isUserClickedAccountInfo", "true");
		shippingAddress = shippingAddressDAO.getByUserId(loggedInUserid);
		model.addAttribute("address", shippingAddress);
		model.addAttribute("addressList", shippingAddressDAO.list(loggedInUserid));		
		return "Home";

	}

}