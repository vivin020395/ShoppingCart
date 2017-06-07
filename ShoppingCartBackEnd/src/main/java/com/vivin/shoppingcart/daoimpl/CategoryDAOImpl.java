package com.vivin.shoppingcart.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vivin.shoppingcart.dao.CategoryDAO;
import com.vivin.shoppingcart.domain.Category;

@Transactional
@Repository("CategoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Category> list() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	}

	
	

	public boolean delete(String id) {
		Category categoryToDelete = new Category();
		categoryToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(categoryToDelete);
		return false;

	}

	public boolean validate(String id, String password) {
		Query query = sessionFactory.getCurrentSession().createQuery(" from User where id = ? and password = ?");
		query.setString(1, id);
		query.setString(2, password);
		if (query.uniqueResult() == null) {

			return false;
		} else {

			return true;
		}

	}

	
	public boolean save(Category category) {
		try
		{
		sessionFactory.getCurrentSession().save(category);
		}catch (Exception e) {
			//if any exception comes during execute of try block, catch will execute
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Category category) {
		try
		{
		sessionFactory.getCurrentSession().update(category);
		}catch (Exception e) {
			//if any exception comes during execute of try block, catch will execute
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public Category getCategoryById(String id) {
		
		//get method get the date from user table based on primary key i.e., id
		// and set it to Category class
		//like select * from category where id = ?
	  return 	(Category)  sessionFactory.getCurrentSession().get(Category.class, id);
		
	}
	
	
	public Category getCategoryByName(String name) {
		
		//Since name is not a primary key, we cannot use the procedure used in getCategoryById(id).
		//We write query as follow.
		return 	(Category)  sessionFactory.getCurrentSession().createQuery("from Category where name = ?").setString(0, name).uniqueResult();
		/*
		 * return 	(Category)  sessionFactory.getCurrentSession().createQuery("from Category where name = ?").setString(0, name).uniqueResult(); is same as
		 * 
		 * Query query = getCurrentSession().createQuery("from Category where name = ?");
		 * query.setString(0,name);
		 * query.uniqueResult();
		 */
		
		
	}

	

	
}

