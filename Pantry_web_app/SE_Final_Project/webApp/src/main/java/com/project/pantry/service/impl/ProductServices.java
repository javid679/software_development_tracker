package com.project.pantry.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.pantry.Repository.CategoryRepository;
import com.project.pantry.Repository.ProductsRepository;
import com.project.pantry.model.Category;
import com.project.pantry.model.Products;
import com.project.pantry.model.User;


@Service
public class ProductServices {

	@Autowired
	ProductsRepository productRepo;
	
	@Autowired
	CategoryRepository cateRepo;
	

		
	public List<Products>getAllProducts(){// Get All the products and return as List of Products
		List<Products> list = productRepo.findAll();
		
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getQty()==0)
			{
				list.remove(list.get(i));
			}
		}
		return list;
	}
	public List<Products>getProductsByCategory(long category_id){// Get All products by Category ID and return as List of Products
		return productRepo.getByCategoryId(category_id);
	}
	
	public List<Category>getAllCategory(){
		return cateRepo.findAll();// method to get All Category products
	}
	
	public Products getProductsById(long productId) throws Exception {
		return productRepo.findById(productId).orElseThrow(() ->new Exception("Product is not found"));// method to get a Product by ID
	}
	public void addProduct(HashMap<String,String> request)// THis Logic method is defined for adding the product to products Table by Admin
	{
		try
		{
		String product_name = request.get("product_name");//get values enetered by Admin
		long cat_id = Long.parseLong(request.get("cat_id")); //Fetch Category ID
		int quantity = Integer.parseInt(request.get("qty"));
		Products product = new Products();
		product.setCategory_id(cat_id);;
		product.setName(product_name);
		product.setQty(quantity);
		Category category = getCategoryDetailById(cat_id);// Set All Values and IMG from category Instance and save it in Products Table
		product.setImg(category.getImg());
		productRepo.save(product);
		
		

		}
		catch(Exception e)
		{
			e.getMessage();// Handling Excpetions if occurred
		}
	
	}
	public Category getCategoryDetailById(long cat_id ) throws Exception {
		return cateRepo.findById(cat_id).orElseThrow(()->new Exception("Category Not found.."));// TO fetch  Category Details by ID
	}
}