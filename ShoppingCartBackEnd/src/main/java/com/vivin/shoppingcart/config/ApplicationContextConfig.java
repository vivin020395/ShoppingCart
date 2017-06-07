package com.vivin.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vivin.shoppingcart.dao.MyCartDAO;
import com.vivin.shoppingcart.dao.ProductDAO;
import com.vivin.shoppingcart.dao.ShippingAddressDAO;
import com.vivin.shoppingcart.dao.CategoryDAO;
import com.vivin.shoppingcart.dao.SupplierDAO;
import com.vivin.shoppingcart.dao.UserDAO;

import com.vivin.shoppingcart.daoimpl.CartDAOImpl;
import com.vivin.shoppingcart.daoimpl.CategoryDAOImpl;
import com.vivin.shoppingcart.daoimpl.ProductDAOImpl;
import com.vivin.shoppingcart.daoimpl.ShippingAddressDAOImpl;
import com.vivin.shoppingcart.daoimpl.SupplierDAOImpl;
import com.vivin.shoppingcart.daoimpl.UserDAOImpl;

import com.vivin.shoppingcart.domain.Category;
import com.vivin.shoppingcart.domain.MyCart;
import com.vivin.shoppingcart.domain.Product;
import com.vivin.shoppingcart.domain.ShippingAddress;
import com.vivin.shoppingcart.domain.Supplier;
import com.vivin.shoppingcart.domain.User;

@Configuration
@ComponentScan("com.vivin.*")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Autowired
	@Bean(name = "dataSource")
	public DataSource getH2DataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");

		dataSource.setUrl("jdbc:h2:tcp://localhost/~/MyDB");

		dataSource.setUsername("sa");
		dataSource.setPassword("");

		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(MyCart.class);
		sessionBuilder.addAnnotatedClass(Product.class);
		sessionBuilder.addAnnotatedClass(Supplier.class);
		sessionBuilder.addAnnotatedClass(Category.class);
		sessionBuilder.addAnnotatedClass(ShippingAddress.class);

		// sessionBuilder.scanPackages("com.niit");
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired(required = true)
	@Bean(name = "UserDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "CartDAO")
	public MyCartDAO getMyCartDAO(SessionFactory sessionFactory) {
		return new CartDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "SupplierDAO")
	public SupplierDAO getSupplierDAO(SessionFactory sessionFactory) {
		return new SupplierDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "CategoryDAO")
	public CategoryDAO getCategoryDAO(SessionFactory sessionFactory) {
		return new CategoryDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "productDAO")
	public ProductDAO getProductDAO(SessionFactory sessionFactory) {
		return new ProductDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "shippingAddressDAO")
	public ShippingAddressDAO getShippingAddressDAO(SessionFactory sessionFactory) {
		return new ShippingAddressDAOImpl(sessionFactory);
	}
}
