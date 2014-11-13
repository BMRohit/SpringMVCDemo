/**
 * 
 */
package com.example.dao;

import java.util.List;

import com.example.model.Product;

/**
 * @author Rohit
 *
 */
public interface ProductDAO {
	
	void insertProduct(Product product);
	Product getProduct(long productID);
	void updateProduct(Product product);
	void deleteProduct(long productID);
	List<Product> getProducts();

}
