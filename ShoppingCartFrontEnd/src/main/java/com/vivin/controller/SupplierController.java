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

import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.dao.SupplierDAO;
import com.vivin.shoppingcart.domain.Category;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.Supplier;

@Controller
public class SupplierController {

	private static Logger log = LoggerFactory.getLogger(SupplierController.class);

	@Autowired
	HttpSession session;
	@Autowired
	SupplierDAO supplierDAO;
	@Autowired
	Supplier supplier;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	Product product;

	// crud supplier
	@RequestMapping("/manage_supplier_add")
	public ModelAndView createSupplier(@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("phone") String phone) {
		ModelAndView mv = new ModelAndView("Home");

		supplier.setName(name);
		supplier.setPhone(phone);
		supplier.setAddress(address);
		supplierDAO.save(supplier);
		mv.addObject("isAdminClickedSuppliers", "true");
		mv.addObject("isAdmin", "true");

		// Before calling save method, check whether supplier_id already exists
		// in db
		// if it does not exist, then only call save method.

		// get all categories
		List<Supplier> supplierList = supplierDAO.list();
		// attach to session
		session.setAttribute("supplierList", supplierList);
		session.setAttribute("supplier", supplier);

		// Before calling save method, check whether supplier_id already exists
		// in db
		// if it does not exist, then only call save method.
		log.debug("Ending of create supplier");
		return mv;

	}

	// attach data to url we use @PathVariable
	@RequestMapping("/manage-supplier-delete/{id}")
	public ModelAndView deleteSupplier(@PathVariable("id") String id) {

		log.debug("Starting of delete Supplier");
		log.info("You are about to delete a supplier with id : " + id);

		ModelAndView mv = new ModelAndView("redirect:/manageSuppliers");

		// Check whether products are there for this category or not
		int noOfProducts = productDAO.getAllProductsBySupplierId(id).size();
		if (noOfProducts != 0) {
			log.debug("Few products are there by this seller, you cannot delete!");
			session.setAttribute("supplierMessage",
					"Cannot delete Supplier.There are " + noOfProducts + " products under Supplier " + id);
			return mv;
		}
		if (supplierDAO.delete(id) == true) {
			mv.addObject("message", "Successfullly deleted");
		} else {
			mv.addObject("message", "Failed to delete");
		}
		log.debug("Ending of delete Supplier");

		return mv;
	}

	@RequestMapping("/manage-supplier-edit/{id}")
	public String editSupplier(@PathVariable("id") String id) {
		log.debug("Starting of editSupplier");
		log.info("You are about to edit a supplier with id : " + id);

		supplier = supplierDAO.getSupplierById(id);

		// Selected category details we have to store in another instance
		// i.e., ModelAndView instance

		session.setAttribute("selectedSupplier", supplier);

		log.debug("Ending of editSupplier");

		return "redirect:/manageSuppliers";
	}

	@PostMapping("/manage_supplier_update")
	public ModelAndView updateSupplier(@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("phone") String phone) {
		log.debug("Starting of updateSupplier");
		ModelAndView mv = new ModelAndView("redirect:/manageSuppliers");

		supplier.setName(name);
		supplier.setPhone(phone);
		supplier.setAddress(address);

		mv.addObject("isAdminClickedSuppliers", "true");
		mv.addObject("isAdmin", "true");
		if (supplierDAO.update(supplier) == true)
			mv.addObject("Edited Successfully");
		else
			mv.addObject("Editing Failed");

		// Before calling save method, check whether supplier_id already exists
		// in db
		// if it does not exist, then only call save method.
		log.debug("Ending of updateSupplier");
		return mv;
	}

}
