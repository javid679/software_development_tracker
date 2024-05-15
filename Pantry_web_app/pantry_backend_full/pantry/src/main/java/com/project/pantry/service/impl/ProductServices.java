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
	

		
	public List<Products>getAllProducts(){
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
	public List<Products>getProductsByCategory(long category_id){
		return productRepo.getByCategoryId(category_id);
	}
	
	public List<Category>getAllCategory(){
		return cateRepo.findAll();
	}
	
	public Products getProductsById(long productId) throws Exception {
		return productRepo.findById(productId).orElseThrow(() ->new Exception("Product is not found"));
	}
	public void addProduct(HashMap<String,String> request)
	{
		try
		{
		String product_name = request.get("product_name");
		long cat_id = Long.parseLong(request.get("cat_id"));
		int quantity = Integer.parseInt(request.get("qty"));
		Products product = new Products();
		product.setCategory_id(cat_id);;
		product.setName(product_name);
		product.setQty(quantity);
		Category category = getCategoryDetailById(cat_id);
		product.setImg(category.getImg());
		productRepo.save(product);
		
		

		}
		catch(Exception e)
		{
			e.getMessage();
		}
	
	}
	public Category getCategoryDetailById(long cat_id ) throws Exception {
		return cateRepo.findById(cat_id).orElseThrow(()->new Exception("Category Not found.."));
	}
}