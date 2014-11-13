/**
 * 
 */
package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ProductDAO;
import com.example.model.Product;

/**
 * 
 * @Transactional can be applied to the Service layer. It's the one that knows
 * about units of work and use cases.The Spring team's recommendation is 
 * that you only annotate concrete classes with the @Transactional annotation, 
 * as opposed to annotating interfaces
 * 
 * @author Rohit
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	/**
	 * The @Autowired annotation is performing Dependency Injection.
	 * @Autowired is applied to :
	 * 1. field: then the dependency is stored in this field.
	 * 2. setter: then the setter in invoked, with the parameter that is 
	 * determined by the same algorithm like for the field dependency injection 
	 * 3. constructor: then the constructor in invoked with the parameters 
	 * determined by the same algorithm like for the field dependency injection
	 */
	@Autowired
	ProductDAO productDAO;
	
	@Override
	public void insertProduct(Product product) {
		productDAO.insertProduct(product);
	}

	@Override
	public Product getProduct(long productID) {
		return productDAO.getProduct(productID);
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}

	@Override
	public void deleteProduct(long productID) {
		productDAO.deleteProduct(productID);
	}

	@Override
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

}
