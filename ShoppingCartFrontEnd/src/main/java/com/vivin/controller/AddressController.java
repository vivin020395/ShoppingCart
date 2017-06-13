package com.vivin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.dao.ShippingAddressDAO;
import com.vivin.shoppingcart.dao.UserDAO;
import com.vivin.shoppingcart.domain.ShippingAddress;
import com.vivin.shoppingcart.domain.User;

@Controller
public class AddressController {

	@Autowired
	HttpSession session;

	@Autowired
	ShippingAddressDAO shippingAddressDAO;
	@Autowired
	ShippingAddress shippingAddress;
	@Autowired
	MyCartDAO cartDAO;
	@Autowired
	User user;
	@Autowired
	UserDAO userDAO;

	@RequestMapping("/addressInfo")
	public String addressInfo(Model model) {
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		model.addAttribute("address", shippingAddressDAO.getByUserId(loggedInUserid));
		model.addAttribute("addressList", shippingAddressDAO.list(loggedInUserid));
		System.out.println(cartDAO.listCartByStatus(loggedInUserid, 'N'));
		if (cartDAO.listCartByStatus(loggedInUserid, 'N').isEmpty()) {
			model.addAttribute("isCartPage", "true");
			model.addAttribute("isCartEmpty", "true");
			model.addAttribute("emptyCart", "You're cart is empty.");
			model.addAttribute("isCartPage", "true");
		} else {
			model.addAttribute("isUserClickedAddress", "true");
			if (shippingAddressDAO.getByUserId(loggedInUserid) == null) {
				model.addAttribute("noAddressForUser", "true");
				model.addAttribute("showInfo", "false");
			} else {
				model.addAttribute("showInfo", "true");
			}
		}
		return "Home";

	}

	@RequestMapping("/addAddress")
	public String addAddress(Model model, @RequestParam("name") String name, @RequestParam("contact") String contact,
			@RequestParam("line1") String line1, @RequestParam("line2") String line2, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("zip") String zipCode) {
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		shippingAddress.setUserid(loggedInUserid);
		shippingAddress.setName(name);
		shippingAddress.setContact(contact);
		shippingAddress.setAddressLine1(line1);
		shippingAddress.setAddressLine2(line2);
		shippingAddress.setCity(city);
		shippingAddress.setState(state);
		shippingAddress.setCountry(country);
		shippingAddress.setZipCode(zipCode);
		boolean result = shippingAddressDAO.save(shippingAddress);
		if (result == true)
			model.addAttribute("message2", "New Address Added");
		else if (result == false)
			model.addAttribute("message2", "Failed to add address");
		else
			model.addAttribute("message2", "");
		model.addAttribute("address", shippingAddress);
		model.addAttribute("isUserClickedAccountInfo", "true");
		model.addAttribute("addressList", shippingAddressDAO.list(loggedInUserid));
		return "Home";
	}

	@RequestMapping("/deleteAddress/{id}")
	public String deleteAddress(Model model, @PathVariable("id") String id) {
		shippingAddressDAO.delete(id);
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		model.addAttribute("address", shippingAddress);
		model.addAttribute("addressList", shippingAddressDAO.list(loggedInUserid));
		return "redirect:/accountInfo";
	}

	@RequestMapping("/editAddress/{id}")
	public String editAddress(Model model, @PathVariable("id") String id, @RequestParam("name") String name,
			@RequestParam("contact") String contact, @RequestParam("line1") String line1,
			@RequestParam("line2") String line2, @RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("country") String country, @RequestParam("zip") String zipCode) {

		shippingAddress = shippingAddressDAO.getById(id);
		shippingAddress.setName(name);
		shippingAddress.setContact(contact);
		shippingAddress.setAddressLine1(line1);
		shippingAddress.setAddressLine2(line2);
		shippingAddress.setCity(city);
		shippingAddress.setState(state);
		shippingAddress.setCountry(country);
		shippingAddress.setZipCode(zipCode);
		if (shippingAddressDAO.update(shippingAddress) == true)
			model.addAttribute("message4", "Update Successful");
		return "redirect:/accountInfo";
	}
}
