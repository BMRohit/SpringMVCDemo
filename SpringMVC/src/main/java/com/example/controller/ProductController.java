/**
 * 
 */
package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Product;
import com.example.service.ProductService;

/**
 * @author Rohit
 *
 */


@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	private static final Logger logger = Logger.getLogger(ProductController.class);
	
	/**
	 * The ModelAndView can be constructed with 3 parameters. (View name , Model
	 * name , model object) By default model name "command" is used because the
	 * spring framework expects an object with name "command" if you are using
	 * <form:form> tags in your JSP file. Model name can be changed to any other
	 * name and same name should be used in JSP with command = "samplename".
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/productpage", method=RequestMethod.GET)
	private ModelAndView getAddProductPage(){
		return new ModelAndView("add-product","product",new Product());
	}
	
	//Simple modelandview without redirection
	@RequestMapping(value="/addproduct", method=RequestMethod.POST)
	private ModelAndView addingTeam(@ModelAttribute Product product) {
		logger.info("Product adding to database...");
		ModelAndView modelAndView = new ModelAndView("home");
		productService.insertProduct(product);
		modelAndView.addObject( "message","Product was successfully added.");
		return modelAndView;
	}
	
	@RequestMapping(value="/listofproducts",method=RequestMethod.GET)
	private ModelAndView getListOfProducts()
	{
		logger.info("getting all the Product from database...");
		List<Product> products = new ArrayList<Product>();
		products = productService.getProducts();
		ModelAndView modelAndView = new ModelAndView("list-of-products");
		modelAndView.addObject("products", products);
		return modelAndView;
	}
	
	//Used the re-direction mechanism; After deleting the product it redirects to home page.
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	private ModelAndView deleteProduct(@PathVariable Integer id,final RedirectAttributes redirectAttributes)
	{
		logger.info("Deleting product from database..."+id);
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		productService.deleteProduct(id);
		redirectAttributes.addFlashAttribute( "message","Product was successfully deleted.");
		return modelAndView;
	}
	
	@RequestMapping(value = "/geteditpage/{id}",method=RequestMethod.GET)
	private ModelAndView getEditProductPage(@PathVariable Integer id)
	{
		ModelAndView modelAndView = new ModelAndView("edit-product-form");
		modelAndView.addObject("product", productService.getProduct(id));
		return modelAndView;
	}
	
	//Used the re-direction mechanism; After updating the product it redirects to home page.
	@RequestMapping(value = "/updateproduct",method=RequestMethod.POST)
	private ModelAndView updateProduct(@ModelAttribute Product product,final RedirectAttributes redirectAttributes)
	{
		logger.info("Updating product to database...");
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		productService.updateProduct(product);
		redirectAttributes.addFlashAttribute( "message","Product was successfully updated.");
		return modelAndView;
	}

}
