package com.vivin.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.dao.ShippingAddressDAO;
import com.vivin.shoppingcart.domain.MyCart;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.ShippingAddress;

@Controller
public class CartController {
	Logger log = LoggerFactory.getLogger(CartController.class);
	@Autowired
	private MyCartDAO cartDAO;

	@Autowired
	private MyCart myCart;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	ShippingAddress shippingAddress;
	@Autowired
	ShippingAddressDAO shippingAddressDAO;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/CartPage", method = RequestMethod.GET)
	public String myCart(HttpServletRequest request, Model model) {
		log.debug("Starting of the method myCart");
		model.addAttribute("myCart", new MyCart());

		// get the logged-in user id
		// if you added the loggedInUserID in session
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		if (loggedInUserid == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			loggedInUserid = auth.getName();
			Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities();
			authorities.contains("ROLE_USER");
		}
		if (loggedInUserid != null) {
			model.addAttribute("user", loggedInUserid);
			int cartSize = cartDAO.list(loggedInUserid).size();

			if (cartSize == 0) {
				model.addAttribute("errorMessage", "You do not have any products in your Cart");
			} else {
				model.addAttribute("totalAmount", cartDAO.getTotalAmount(loggedInUserid));
			}

		}
		session.setAttribute("loggedInUserid", loggedInUserid);
		model.addAttribute("cartList", cartDAO.listCartByStatus(loggedInUserid, 'N'));
		model.addAttribute("isUserLoggedIn", "true");
		model.addAttribute("isCartPage", "true");
		return "/Home";

	}

	// For add and update myCart both
	@RequestMapping("/myCart/add/{id}")
	public String addToCart(@PathVariable("id") String id) {
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		if (loggedInUserid == null) {
			return "redirect:/LoginPage";
		}
		Product product = productDAO.getProductById(id);
		log.debug("Starting of the method addToCart");
		// get the product based on product id
		myCart.setPrice(product.getPrice());
		myCart.setName(product.getName());
		myCart.setUserId(loggedInUserid);
		// It is not required if you given default value while creating the
		// table
		myCart.setStatus('N'); // Status is New. Once it is Purchased/order
								// changed to 'P' O
		myCart.setQuantity(1);
		myCart.setDate(new Date(System.currentTimeMillis()));
		myCart.setProductId(product.getId());
		// To get sequence number, you can do programmatically in DAOImpl
		myCart.setId(ThreadLocalRandom.current().nextLong(100, 1000000 + 1));

		// check same product is added earlier.
		if (cartDAO.getCart(loggedInUserid, myCart.getName()) != null) {
			// increase the quantity and update it.
			int presentQuantity = cartDAO.getQuantity(loggedInUserid, myCart.getName());
			myCart.setQuantity(presentQuantity + 1);
			if (cartDAO.update(myCart)) {
				int stock = product.getStock() - 1;
				product.setStock(stock);
				productDAO.update(product);
			}
		} else {
			if (cartDAO.save(myCart)) {
				int stock = product.getStock() - 1;
				product.setStock(stock);
				productDAO.update(product);
			}
		}

		// return "redirect:/views/home.jsp";

		ModelAndView mv = new ModelAndView("CartPage");
		mv.addObject("successMessage", " Successfuly add the product to myCart");

		log.debug("Ending of the method addToCart");
		return "redirect:/CartPage";

	}

	@RequestMapping("/myCart/addQuantity/{id}")
	public String updateQuantity(@PathVariable("id") int cart_id) {
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		myCart = cartDAO.getCartById(cart_id);
		int presentQuantity = cartDAO.getQuantity(loggedInUserid, myCart.getName());
		myCart.setQuantity(presentQuantity + 1);
		cartDAO.update(myCart);
		String id = myCart.getProductId();
		Product product = productDAO.getProductById(id);
		int stock = product.getStock() - 1;
		product.setStock(stock);
		productDAO.update(product);
		return "redirect:/CartPage";
	}

	@RequestMapping("/myCart/reduceQuantity/{id}")
	public String reduceQuantity(@PathVariable("id") int cart_id) {
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		myCart = cartDAO.getCartById(cart_id);
		int presentQuantity = cartDAO.getQuantity(loggedInUserid, myCart.getName());
		if (presentQuantity == 1) {
			session.setAttribute("isQuantityOne", "true");
			session.setAttribute("productMessage", "Remove product by clicking the delete button");
			return "redirect:/CartPage";
		}
		myCart.setQuantity(presentQuantity - 1);
		cartDAO.update(myCart);
		String id = myCart.getProductId();
		Product product = productDAO.getProductById(id);
		int stock = product.getStock() + 1;
		product.setStock(stock);
		productDAO.update(product);
		return "redirect:/CartPage";
	}

	@RequestMapping("/checkout/{id}")
	public String cartCheckout(Model model, @PathVariable("id") String id) {

		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		shippingAddress = shippingAddressDAO.getById(id);
		StringBuffer address = new StringBuffer();
		address.append(shippingAddress.getAddressLine1() + "\n");
		address.append(shippingAddress.getAddressLine2() + ",");
		address.append(shippingAddress.getCity() + ",");
		address.append(shippingAddress.getState() + ",");
		address.append(shippingAddress.getCountry() + "-");
		address.append(shippingAddress.getZipCode());
		int randomNum = ThreadLocalRandom.current().nextInt(2, 6 + 1);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, randomNum);
		Date deliveryDate = (Date) cal.getTime();
		for (MyCart myCart : cartDAO.list(loggedInUserid)) {
			myCart.setStatus('P');
			myCart.setDays(randomNum);
			myCart.setAddress(address.toString());
			myCart.setDatePurchased(date);
			myCart.setDeliveryDate(deliveryDate);
			cartDAO.update(myCart);
		}
		session.setAttribute("deliveryDays", randomNum + "");
		session.setAttribute("OrderDate", date);
		return "redirect:/CheckOutPage";
	}

	@RequestMapping("/CheckOutPage")
	public String checkOutPage(Model model) {
		model.addAttribute("isUserClickedCheckout", "true");
		return "Home";
	}

	@RequestMapping("/myCart/delete/{id}")
	public ModelAndView removeFromCart(@PathVariable("id") int id) {
		log.debug("Starting of removeFromCart in CartController");
		ModelAndView mv = new ModelAndView("redirect:/CartPage");
		// Check whether products are there for this category or not
		myCart = cartDAO.getCartById(id);
		int cartQuantity = myCart.getQuantity();
		Product product = productDAO.getProductByName(myCart.getName());
		int stock = product.getStock() + cartQuantity;
		product.setStock(stock);
		productDAO.update(product);
		if (cartDAO.delete(id)) {
			mv.addObject("cartMessage", "Successfullly deleted from cart");
		} else {
			mv.addObject("cartMessage", "Failed to delete from cart");
		}

		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		int cartSize = cartDAO.list(loggedInUserID).size();
		session.setAttribute("cartSize", cartSize);

		log.debug("Ending of removeFromCart in CartController");
		return mv;

	}

}
