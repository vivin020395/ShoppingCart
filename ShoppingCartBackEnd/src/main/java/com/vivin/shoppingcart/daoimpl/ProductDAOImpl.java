package com.vivin.shoppingcart.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.Supplier;

@Transactional
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// write user defined constructor with one parameter i.e., sessionFactory

	public ProductDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Product> list() {

		return sessionFactory.getCurrentSession().createQuery("from Product").list();

	}

	public boolean delete(String id) {
		try {
			sessionFactory.getCurrentSession().delete(getProductById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Product getProductById(String id) {
		// get method get the date from user table based on primary key i.e., id
		// and set it to User class
		// like select * from user where id = ?
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	public Product getProductByName(String name) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where name = ?")
				.setString(0, name).uniqueResult();
	}

	public List<Product> getAllProductsByCategoryId(String categoryId) {

		String hql = "from Product where category_Id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, categoryId);
		return query.list();

	}

	public List<Product> getAllProductsBySupplierId(String supplierId) {
		return sessionFactory.getCurrentSession().createQuery("from Product where supplier_Id = ?")
				.setString(0, supplierId).list();
	}

	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(product);
		} catch (Exception e) {
			// if any excpetion comes during execute of try block, catch will
			// excute
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean save(Product product) {
		try {
			sessionFactory.getCurrentSession().save(product);
		} catch (Exception e) {
			// if any excpetion comes during execute of try block, catch will
			// excute
			e.printStackTrace();
			return false;
		}
		return true;
	}
}