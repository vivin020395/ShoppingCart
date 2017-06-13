package com.vivin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vivin.shoppingcart.dao.CategoryDAO;
import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.dao.ShippingAddressDAO;
import com.vivin.shoppingcart.dao.UserDAO;
import com.vivin.shoppingcart.domain.Category;
import com.vivin.shoppingcart.domain.MyCart;
import com.vivin.shoppingcart.domain.Product;
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
	MyCart myCart;
	@Autowired
	ShippingAddressDAO shippingAddressDAO;
	@Autowired
	ShippingAddress shippingAddress;
	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	Category cateogry;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	private HttpSession session;

	@RequestMapping("/")
	public String goToHome(Model model) {

		model.addAttribute("productList", productDAO.getAllProductsByCategoryId("3"));
		model.addAttribute("productList2", productDAO.getAllProductsByCategoryId("4"));
		model.addAttribute("productList3", productDAO.getAllProductsByCategoryId("5"));
		model.addAttribute("productList4", productDAO.getAllProductsByCategoryId("6"));
		model.addAttribute("productList5", productDAO.getAllProductsByCategoryId("7"));
		// category
		model.addAttribute("CPULIST", categoryDAO.getCategoryById("3"));
		model.addAttribute("MOTHERBOARDLIST", categoryDAO.getCategoryById("4"));
		model.addAttribute("MEMORYLIST", categoryDAO.getCategoryById("5"));
		model.addAttribute("VIDEOCARDLIST", categoryDAO.getCategoryById("6"));
		model.addAttribute("STORAGELIST", categoryDAO.getCategoryById("7"));
		model.addAttribute("isUserClickedHomePage", "true");
		return "Home";
	}

	@RequestMapping("/HomePage")
	public String goHome(Model model) {
		String userid = (String) session.getAttribute("loggedInUserID");
		if (userid != null) {
			for (MyCart myCart : cartDAO.listCartByStatus(userid, 'P')) {
				Date dispatchDate = new Date(myCart.getDeliveryDate().getTime());
				Date today = new Date();
				System.out.println(myCart.getDatePurchased());
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
				String date1 = DATE_FORMAT.format(today);
				System.out.println("Today in dd-MM-yyyy format : " + date1);
				if (myCart.getDatePurchased().equals(today))
					break;
				long diff = dispatchDate.getTime() - today.getTime();
				long date = (diff / (1000 * 60 * 60 * 24));
				myCart.setDays((int) date + 1);
				if (myCart.getDays() != 0)
					cartDAO.update(myCart);
			}
		}
		// product
		model.addAttribute("productList", productDAO.getAllProductsByCategoryId("3"));
		model.addAttribute("productList2", productDAO.getAllProductsByCategoryId("4"));
		model.addAttribute("productList3", productDAO.getAllProductsByCategoryId("5"));
		model.addAttribute("productList4", productDAO.getAllProductsByCategoryId("6"));
		model.addAttribute("productList5", productDAO.getAllProductsByCategoryId("7"));
		// category
		model.addAttribute("CPULIST", categoryDAO.getCategoryById("3"));
		model.addAttribute("MOTHERBOARDLIST", categoryDAO.getCategoryById("4"));
		model.addAttribute("MEMORYLIST", categoryDAO.getCategoryById("5"));
		model.addAttribute("VIDEOCARDLIST", categoryDAO.getCategoryById("6"));
		model.addAttribute("STORAGELIST", categoryDAO.getCategoryById("7"));
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
		model.addAttribute("productList", productDAO.getAllProductsByCategoryId("3"));
		model.addAttribute("productList2", productDAO.getAllProductsByCategoryId("4"));
		model.addAttribute("productList3", productDAO.getAllProductsByCategoryId("5"));
		model.addAttribute("productList4", productDAO.getAllProductsByCategoryId("6"));
		model.addAttribute("productList5", productDAO.getAllProductsByCategoryId("7"));
		// category
		model.addAttribute("CPULIST", categoryDAO.getCategoryById("3"));
		model.addAttribute("MOTHERBOARDLIST", categoryDAO.getCategoryById("4"));
		model.addAttribute("MEMORYLIST", categoryDAO.getCategoryById("5"));
		model.addAttribute("VIDEOCARDLIST", categoryDAO.getCategoryById("6"));
		model.addAttribute("STORAGELIST", categoryDAO.getCategoryById("7"));
		model.addAttribute("isUserClickedCPU", "true");

		return "Home";
	}

	@RequestMapping("/MotherboardPage")
	public String motherboardPage(Model model) {
		model.addAttribute("productList", productDAO.getAllProductsByCategoryId("3"));
		model.addAttribute("productList2", productDAO.getAllProductsByCategoryId("4"));
		model.addAttribute("productList3", productDAO.getAllProductsByCategoryId("5"));
		model.addAttribute("productList4", productDAO.getAllProductsByCategoryId("6"));
		model.addAttribute("productList5", productDAO.getAllProductsByCategoryId("7"));
		// category
		model.addAttribute("CPULIST", categoryDAO.getCategoryById("3"));
		model.addAttribute("MOTHERBOARDLIST", categoryDAO.getCategoryById("4"));
		model.addAttribute("MEMORYLIST", categoryDAO.getCategoryById("5"));
		model.addAttribute("VIDEOCARDLIST", categoryDAO.getCategoryById("6"));
		model.addAttribute("STORAGELIST", categoryDAO.getCategoryById("7"));
		model.addAttribute("isUserClickedMotherboard", "true");

		return "Home";
	}

	@RequestMapping("/MemoryPage")
	public String memoryPage(Model model) {
		model.addAttribute("productList", productDAO.getAllProductsByCategoryId("3"));
		model.addAttribute("productList2", productDAO.getAllProductsByCategoryId("4"));
		model.addAttribute("productList3", productDAO.getAllProductsByCategoryId("5"));
		model.addAttribute("productList4", productDAO.getAllProductsByCategoryId("6"));
		model.addAttribute("productList5", productDAO.getAllProductsByCategoryId("7"));
		// category
		model.addAttribute("CPULIST", categoryDAO.getCategoryById("3"));
		model.addAttribute("MOTHERBOARDLIST", categoryDAO.getCategoryById("4"));
		model.addAttribute("MEMORYLIST", categoryDAO.getCategoryById("5"));
		model.addAttribute("VIDEOCARDLIST", categoryDAO.getCategoryById("6"));
		model.addAttribute("STORAGELIST", categoryDAO.getCategoryById("7"));
		model.addAttribute("isUserClickedMemory", "true");

		return "Home";
	}

	@RequestMapping("/StoragePage")
	public String storagePage(Model model) {
		model.addAttribute("productList", productDAO.getAllProductsByCategoryId("3"));
		model.addAttribute("productList2", productDAO.getAllProductsByCategoryId("4"));
		model.addAttribute("productList3", productDAO.getAllProductsByCategoryId("5"));
		model.addAttribute("productList4", productDAO.getAllProductsByCategoryId("6"));
		model.addAttribute("productList5", productDAO.getAllProductsByCategoryId("7"));
		// category
		model.addAttribute("CPULIST", categoryDAO.getCategoryById("3"));
		model.addAttribute("MOTHERBOARDLIST", categoryDAO.getCategoryById("4"));
		model.addAttribute("MEMORYLIST", categoryDAO.getCategoryById("5"));
		model.addAttribute("VIDEOCARDLIST", categoryDAO.getCategoryById("6"));
		model.addAttribute("STORAGELIST", categoryDAO.getCategoryById("7"));
		model.addAttribute("isUserClickedStorage", "true");

		return "Home";
	}

	@RequestMapping("/VideoCardPage")
	public String videoCardPage(Model model) {
		model.addAttribute("productList", productDAO.getAllProductsByCategoryId("3"));
		model.addAttribute("productList2", productDAO.getAllProductsByCategoryId("4"));
		model.addAttribute("productList3", productDAO.getAllProductsByCategoryId("5"));
		model.addAttribute("productList4", productDAO.getAllProductsByCategoryId("6"));
		model.addAttribute("productList5", productDAO.getAllProductsByCategoryId("7"));
		// category
		model.addAttribute("CPULIST", categoryDAO.getCategoryById("3"));
		model.addAttribute("MOTHERBOARDLIST", categoryDAO.getCategoryById("4"));
		model.addAttribute("MEMORYLIST", categoryDAO.getCategoryById("5"));
		model.addAttribute("VIDEOCARDLIST", categoryDAO.getCategoryById("6"));
		model.addAttribute("STORAGELIST", categoryDAO.getCategoryById("7"));
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
		model.addAttribute("total", cartDAO.getTotalAmount(loggedInUserid));
		model.addAttribute("isUserClickedOrderHistory", "true");
		model.addAttribute("cartList", cartDAO.listCartByStatus(loggedInUserid, 'P'));
		model.addAttribute("OrderDate", myCart.getDatePurchased());

		return "Home";
	}

	@RequestMapping("/paymentInfo/{id}")
	public String paymentInfo(Model model, @PathVariable("id") String id) {
		session.setAttribute("selectedAddress", shippingAddressDAO.getById(id));
		System.out.println(shippingAddressDAO.getById(id));
		return "redirect:/PaymentInfoPage";
	}

	@RequestMapping("/PaymentInfoPage")
	public String paymentInfoPage(Model model) {
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