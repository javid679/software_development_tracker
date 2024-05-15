/**
 * @author webpantry
 * 	@RequestMapping("getAll") This Rest API is being called to get ALl the products from the DB to display at Products Page.
 * 	@RequestMapping("getProductsByCategory") This Rest API is being used to get All products with Specific Category sent in the JSON request.
 * 	@RequestMapping("getAllCategory") THis API is being called to get All Category products
 */
package com.project.pantry.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pantry.model.Category;
import com.project.pantry.model.Products;
import com.project.pantry.service.impl.ProductServices;
@RestController
@RequestMapping("api/product")

public class ProductController {
	@Autowired
	ProductServices ProductServices;
	
	@RequestMapping("getAll")
	@CrossOrigin
	public List<Products> getAllPRoducts(){
		return ProductServices.getAllProducts();// Fetch All Products List
	}
	@RequestMapping("getAllCategory")
	@CrossOrigin
	public List<Category> getAllCategory(){
		return ProductServices.getAllCategory();
	}
	@RequestMapping("getProductsByCategory")
	@CrossOrigin
	public List<Products> getProductsByCategory(@RequestBody HashMap<String,String> request){
		long category_id = Long.parseLong(request.get("cat_id"));		
		return ProductServices.getProductsByCategory(category_id);// Fetch All Products by categoryID
	}
}
	