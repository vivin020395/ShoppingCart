package com.vivin.shoppingcart.dao;

import java.util.List;

import com.vivin.shoppingcart.domain.MyCart;

public interface MyCartDAO {

	public List<MyCart> list(String userID);

	public boolean save(MyCart myCart);

	public boolean delete(int id);

	public boolean update(MyCart myCart);

	public MyCart getCartById(int id);

	public Long getTotalAmount(String id);

	public MyCart getCartByUserId(String id);

	public MyCart getCart(String userID, String productName);

	public Integer getQuantity(String userID, String productName);

	public boolean deleteAllProductsInCart(String id);

	public List<MyCart> listCartByStatus(String userid, char status);

	public List<MyCart> list();
}
