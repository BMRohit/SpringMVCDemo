/**
 * 
 */
package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rohit
 *
 */
@Entity
@Table(name="product")
public class Product 
{
	@Id
	@GeneratedValue
	private long productID;
	private String productName;
	private double price;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(long productID, String productName, double price) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.price = price;
	}
	
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName="
				+ productName + ", price=" + price + "]";
	}
	
}
