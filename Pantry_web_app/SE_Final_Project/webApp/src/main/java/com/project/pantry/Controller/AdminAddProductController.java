/**
 * @author webpantry
 * 	@RequestMapping("rest-api/admin") THis is Admin AddProduct API which adds the products to the database
 */
package com.project.pantry.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pantry.service.impl.ProductServices;

	@RestController
	@RequestMapping("rest-api/admin")
	public class AdminAddProductController {
		@Autowired
		ProductServices ProductServices;
		@RequestMapping("AddProduct")
		@CrossOrigin
		public ResponseEntity<?> getProductsByCategory(@RequestBody HashMap<String,String> request){
			try
			{
			ProductServices.addProduct(request);// Calling service to consume API and add product to the database
			return  ResponseEntity.ok("Product Added Successfully");
			}
			catch(Exception e)
			{
            return ResponseEntity.badRequest().body(e.getMessage());
			}

			
		}

		


}
