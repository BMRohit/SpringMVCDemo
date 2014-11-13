/**
 * 
 */
package com.example.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Product;

/**
 * @author Rohit
 *
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

	
	private static final Logger logger = Logger.getLogger(ProductDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		if(sessionFactory != null){
			return sessionFactory.getCurrentSession();
		}
		else
			throw new RuntimeException("Session Factory is null");
	}
	
	
	@Override
	public void insertProduct(Product product) {
		try{
			getCurrentSession().save(product);
			logger.info("Product inserted to database ");
		}
		catch(Exception e){
			logger.error(" error while saving product "+product,e);
		}
	}

	@Override
	public Product getProduct(long productID) {
		Product product = null;
		try{
			product =  (Product)getCurrentSession().get(Product.class, productID);
			logger.info("Product fetched from database ");
		}
		catch(Exception e){
			logger.error(" error while getting product of id "+productID,e);
		}
		return product;
	}

	@Override
	public void updateProduct(Product product) {
		try{
			Product productToUpdate = getProduct(product.getProductID());
			productToUpdate.setProductName(product.getProductName());
			productToUpdate.setPrice(product.getPrice());
			getCurrentSession().update(productToUpdate);
			logger.info("Product updated to database ");
		}
		catch(Exception e){
			logger.error(" error while updating product "+product,e);
		}
	}

	@Override
	public void deleteProduct(long productID) {
		try{
			getCurrentSession().delete(getProduct(productID));
			logger.info("Product deleted from from database ");
		}
		catch(Exception e){
			logger.error(" error while deleting product of id "+productID,e);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts() {
		List<Product> products = null;
		try{
			products = getCurrentSession().createQuery("from Product ").list();
			logger.info("all products fetched from database ");
		}
		catch(Exception e){
			logger.error(" error while getting list of products",e);
		}
		return products;
	}
	
	

}
