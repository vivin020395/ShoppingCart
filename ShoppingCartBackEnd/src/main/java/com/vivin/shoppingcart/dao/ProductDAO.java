package com.vivin.shoppingcart.dao;

import java.util.List;

import com.vivin.shoppingcart.domain.Product;

public interface ProductDAO {
	// delete category
	public boolean delete(String id);

	public List<Product> list();

	public Product getProductById(String id);
	
	//get all products pertaining to particular category
	public List<Product> getAllProductsByCategoryId(String categoryId);
	public List<Product> getAllProductsBySupplierId(String supplierId);
	
	public Product getProductByName(String name);

	public boolean update(Product product);

	public boolean save(Product product);
	
}
