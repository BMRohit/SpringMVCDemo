/**
 * 
 */
package com.example.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.init.WebAppConfig;
import com.example.model.Product;
import com.example.service.ProductService;

/**
 * @author Rohit
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class)
public class ProductDAOTest 
{
	
	private static final String PRODUCT_NAME = "Test Product1";
	private static final double PRODUCT_PRICE = 150001;
	private static final String PRODUCT_NAME_UPDATED = "Test Product Updated";
	private static final double PRODUCT_PRICE_UPDATED = 20000;
	private Product testProduct;
	
	@Autowired
	ProductService productService;
	
	@Before
	public void executeBeforeRunningTests()
	{
		testProduct = new Product();
		testProduct.setProductName(PRODUCT_NAME);
		testProduct.setPrice(PRODUCT_PRICE);
	}
	
	@Test
	public void testProductCRUD() throws Exception
	{
		//****** Test Create ***********
		//Insert Product to database
		productService.insertProduct(testProduct); 
		
		//****** Test Read ***********
		//Read the product to test whether product was inserted
		Product fetchedFromDB = productService.getProduct(testProduct.getProductID());
		Assert.assertNotNull(fetchedFromDB);
		//Test the values which are inserted to database
		Assert.assertEquals(fetchedFromDB.getProductID(),testProduct.getProductID());
		Assert.assertEquals(fetchedFromDB.getProductName(),PRODUCT_NAME);
		// For doubles/floats you should use delta Assert.assertEquals(expected, actual, delta)
		Assert.assertEquals(fetchedFromDB.getPrice(),PRODUCT_PRICE,0);
		
		//****** Test Update ***********
		//Update the product in the DB
		testProduct.setProductName(PRODUCT_NAME_UPDATED);
		testProduct.setPrice(PRODUCT_PRICE_UPDATED);
		productService.updateProduct(testProduct);
		
		//****** Test Read after update ***********
		//Read the product to test whether product was updated in DB
		fetchedFromDB = productService.getProduct(testProduct.getProductID());
		Assert.assertNotNull(fetchedFromDB);
		//Test the values which are inserted to database
		Assert.assertEquals(fetchedFromDB.getProductID(),testProduct.getProductID());
		Assert.assertEquals(fetchedFromDB.getProductName(),PRODUCT_NAME_UPDATED);
		// For doubles/floats you should use delta Assert.assertEquals(expected, actual, delta)
		Assert.assertEquals(fetchedFromDB.getPrice(),PRODUCT_PRICE_UPDATED,0);
		
		//****** Test Delete ***********
		//Delete the product from the database
		productService.deleteProduct(testProduct.getProductID());
		Product afterDeletingFromDB = productService.getProduct(testProduct.getProductID());
		//Test if the product is deleted
		Assert.assertNull(afterDeletingFromDB);
	}

}
