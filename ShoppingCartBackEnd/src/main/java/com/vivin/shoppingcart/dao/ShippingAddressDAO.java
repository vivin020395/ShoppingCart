package com.vivin.shoppingcart.dao;

import java.util.List;

import com.vivin.shoppingcart.domain.ShippingAddress;

public interface ShippingAddressDAO {

	public List<ShippingAddress> list(String userid);

	public ShippingAddress getByUserId(String userid);

	public ShippingAddress getById(String id);

	public boolean save(ShippingAddress shipping);

	public boolean update(ShippingAddress shipping);

	public boolean delete(String id);
}
