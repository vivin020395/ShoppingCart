package com.vivin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vivin.shoppingcart.dao.CategoryDAO;
import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.dao.SupplierDAO;
import com.vivin.shoppingcart.domain.Category;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.Supplier;
import com.vivin.util.FileUtil;
import com.vivin.util.Util;

@Controller
public class ProductController {

	private static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductDAO productDAO;

	@Autowired
	Product product;

	@Autowired
	SupplierDAO supplierDAO;

	@Autowired
	Supplier supplier;

	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	Category category;

	@Autowired
	HttpSession session;

	// Setting Path to store images
	private static String PATH = "D://Program Files/workspace/ShoppingCartFrontEnd/src/main/webapp/WEB-INF/resources/images/featured/";

	@RequestMapping(method = RequestMethod.POST, value = "/manage-product-add")
	public String addPorduct(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("price") String price, @RequestParam("category") String Category_id,
			@RequestParam("supplier") String Supplier_id, @RequestParam("image") MultipartFile image,
			@RequestParam("stock") int stock) {
		log.debug("Starting of method addProduct");
		ModelAndView mv = new ModelAndView("Home");
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setCategory_id(Category_id);
		product.setSupplier_id(Supplier_id);
		product.setStock(stock);
		productDAO.save(product);
		if (image.isEmpty()) {
			mv.addObject("message", "Please select a file to upload");
		}
		FileUtil.upload(PATH, image, product.getId() + ".jpg");

		mv.addObject("isAdminClickedProducts", "true");
		mv.addObject("isAdmin", "true");

		log.debug("Ending of the method add Product");

		return "redirect:/manageProducts";
	}

	@RequestMapping("/viewProduct")
	public String viewProductHome(Model model) {
		model.addAttribute("isUserClickedListAllProducts", "true");
		model.addAttribute("product", product);
		model.addAttribute("productList", productDAO.list());		
		return "Home";

	}

	@RequestMapping("/viewProduct/{id}")
	public String viewProduct(@PathVariable("id") String id, Model model) {
		session.setAttribute("selectedProduct", productDAO.getProductById(id));
		product = productDAO.getProductById(id);
		if (product.getStock() == 0) {
			session.setAttribute("isNoStock", "true");
			session.setAttribute("isStock", "false");
			session.setAttribute("productNotAvailable", "OUT OF STOCK");
		} else {
			session.setAttribute("isNoStock", "false");
			session.setAttribute("isStock", "true");
		}
		String supplierId = product.getSupplier_id();
		Supplier supplier = supplierDAO.getSupplierById(supplierId);
		String supplierName = supplier.getName();
		session.setAttribute("supplierName", supplierName);
		return "redirect:/ProductDescriptionPage";
	}

	@RequestMapping("/ProductDescriptionPage")
	public String productDescriptionPage(Model model) {
		model.addAttribute("isUserDescriptionPage", "true");
		return "Home";
	}

	// Delete Product
	@RequestMapping("/manage_product_delete/{id}")
	public String deleteProduct(@PathVariable("id") String id, Model model) {
		log.debug("Starting of the method deleteProduct");
		productDAO.getProductById(id);
		String name = product.getId();
		try {
			File file = new File(PATH + name + ".jpg");

			if (file.delete() && productDAO.delete(id))
				model.addAttribute("message", "Successfully deleted");
			else
				model.addAttribute("message", "UnSuccessfull");

		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		log.debug("ending of method deleteProduct");
		return "redirect:/manageProducts";

	}

	// Edit Products
	@RequestMapping("/manage_product_edit/{id}")
	public ModelAndView editProduct(@PathVariable("id") String id) {
		log.debug("Starting of the method editProduct");
		product = productDAO.getProductById(id);
		ModelAndView mv = new ModelAndView("redirect:/manageProducts");
		session.setAttribute("selectedProduct", product);
		return mv;
	}

	@PostMapping("/manage_product_update")
	public ModelAndView updateProduct(@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") String price,
			@RequestParam("stock") int stock, @RequestParam("category") String categoryid,
			@RequestParam("supplier") String supplierid) {
		log.debug("Starting of updateProduct");
		ModelAndView mv = new ModelAndView("redirect:/manageProducts");

		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setStock(stock);
		product.setCategory_id(categoryid);
		product.setSupplier_id(supplierid);
		mv.addObject("isAdminClickedProducts", "true");
		mv.addObject("isAdmin", "true");
		if (productDAO.update(product))
			mv.addObject("Edited Successfully");
		else
			mv.addObject("Editing Failed");

		// Before calling save method, check whether supplier_id already exists
		// in db
		// if it does not exist, then only call save method.
		log.debug("Ending of updateProduct");
		return mv;
	}

	// Get select product details
	@RequestMapping("/manage-product-get/{id}")
	public ModelAndView getSelectedProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		log.debug("Starting of the method getSelectedProduct");
		ModelAndView mv = new ModelAndView("redirect:/");
		redirectAttributes.addFlashAttribute("selectedProduct", productDAO.getProductById(id));
		log.debug("ending of method getSelectedProduct");
		return mv;
	}

	/*
	 * @RequestMapping("/showProductByCategory/{category_id}") public String
	 * showProductByCategory(@PathVariable("category_id") String category_id,
	 * Model model) { session.setAttribute("selectedCategoryProducts",
	 * productDAO.getAllProductsByCategoryId(category_id));
	 * session.setAttribute("product", product);
	 * model.addAttribute("isUserClickedProductByCategory", "true"); return
	 * "redirect:/productByCategory"; }
	 * 
	 * @RequestMapping("/productByCategory") public String
	 * productByCategory(Model model) {
	 * model.addAttribute("isUserClickedProductByCategory", "true"); return
	 * "Home";
	 * 
	 * }
	 */

}
