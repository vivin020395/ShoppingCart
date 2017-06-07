package com.vivin.shoppingcart.dao;

import java.util.List;


import com.vivin.shoppingcart.domain.MyCart;

public interface MyCartDAO {


	public List<MyCart> list(String userID);

	public MyCart get(String id);
	
	public boolean  save(MyCart myCart);
	
	public boolean delete(int id);
	
	public boolean update(MyCart myCart);

	public MyCart getCartById(int id);
	
	public Long getTotalAmount(String id);
	
	public MyCart getCartByUserId(String userid);
	
	public MyCart getCart(String userID, String productName);
	
	public Integer getQuantity(String userID, String productName);

	public boolean deleteAllProductsInCart(String id);

	public Integer getCartSizeByStatus(String id,char status);
}
