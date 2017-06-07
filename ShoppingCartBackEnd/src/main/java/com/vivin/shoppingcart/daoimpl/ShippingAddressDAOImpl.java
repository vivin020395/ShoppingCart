package com.vivin.shoppingcart.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vivin.shoppingcart.dao.ShippingAddressDAO;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.ShippingAddress;

@Repository("ShippingAddressDAO")
public class ShippingAddressDAOImpl implements ShippingAddressDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ShippingAddressDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<ShippingAddress> list(String userid) {

		return sessionFactory.getCurrentSession().createQuery("from ShippingAddress where userid = ?")
				.setString(0, userid).list();
	}

	@Transactional
	public ShippingAddress getByUserId(String userid) {
		String hql = "from ShippingAddress where userid ='" + userid + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ShippingAddress> listShippingAddress = (List<ShippingAddress>) (query).list();

		if (listShippingAddress != null && !listShippingAddress.isEmpty()) {
			return listShippingAddress.get(0);
		}
		return null;
	}

	@Transactional
	public boolean save(ShippingAddress shipping) {
		try {
			sessionFactory.getCurrentSession().save(shipping);
		} catch (Exception e) {
			// if any excpetion comes during execute of try block, catch will
			// excute
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Transactional
	public boolean update(ShippingAddress shipping) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(shipping);
		} catch (Exception e) {
			// if any excpetion comes during execute of try block, catch will
			// excute
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Transactional
	public boolean delete(String id) {
		try {
			sessionFactory.getCurrentSession().delete(getById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Transactional
	public ShippingAddress getById(String id) {
		// TODO Auto-generated method stub
		return (ShippingAddress) sessionFactory.getCurrentSession().get(ShippingAddress.class, id);
	}
}
