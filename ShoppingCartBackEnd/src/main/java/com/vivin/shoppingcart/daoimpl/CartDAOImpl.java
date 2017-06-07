package com.vivin.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.domain.MyCart;

@Repository("cartDAO")
public class CartDAOImpl implements MyCartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public CartDAOImpl() {

	}

	public CartDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<MyCart> list(String userID) {

		String hql = "from MyCart where userID=" + "'" + userID + "'  and status = " + "'N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		return query.list();

	}

	@Transactional
	public boolean save(MyCart myCart) {
		try {
			sessionFactory.getCurrentSession().save(myCart);
		} catch (HibernateException e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public MyCart getCart(String userID, String productName) {
		String hql = "from MyCart where userID = ? and name = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, userID);
		query.setString(1, productName);
		return (MyCart) query.uniqueResult();

	}

	@Transactional
	public Integer getQuantity(String userID, String productName) {
		String hql = "select quantity from  MyCart where userID = ?  and  name =?";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, userID);
		query.setString(1, productName);

		return (Integer) query.uniqueResult();
	}

	@Transactional
	public Long getTotalAmount(String userID) {

		String hql = "select sum(price*quantity) from MyCart where userID=" + "'" + userID + "' " + "  and status = "
				+ "'N'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		return (Long) query.uniqueResult();

	}


	@Transactional
	public MyCart get(String id) {
		return (MyCart) sessionFactory.getCurrentSession().get(MyCart.class, id);
	}

	@Transactional
	public boolean delete(int id) {
		try {
			sessionFactory.getCurrentSession().delete(getCartById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Transactional
	public MyCart getCartById(int id) {

		return (MyCart) sessionFactory.getCurrentSession().createQuery("from MyCart where id = ?").setInteger(0, id)
				.uniqueResult();
	}

	@Transactional
	public MyCart getCartByUserId(String id) {

		return (MyCart) sessionFactory.getCurrentSession().createQuery("from MyCart where userid = ?").setString(0, id)
				.uniqueResult();
	}

	@Transactional
	public boolean update(MyCart myCart) {
		try {
			sessionFactory.getCurrentSession().update(myCart);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean deleteAllProductsInCart(String id) {
		try {
			sessionFactory.getCurrentSession().createQuery("delete from MyCart where userid = ?").setString(0, id)
					.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Transactional
	public Integer getCartSizeByStatus(String id, char status) {
		String hql = "select quantity from  MyCart where userID = ?  and  status =?";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		query.setCharacter(1, status);

		return (Integer) query.uniqueResult();
	}
}
