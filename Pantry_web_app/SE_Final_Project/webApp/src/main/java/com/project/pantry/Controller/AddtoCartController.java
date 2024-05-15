/**
 * @author webpantry
 * "@RequestMapping("api/addtocart") This Rest API is POST request and which required three fields productID, email and qty as values to add products to cart.
 * "@RequestMapping("updateQtyForCart") This Rest API is being used to update the quantity of cart Items
 * 	@RequestMapping("removeProductFromCart") This Rest API is being used to delete item from the cart.
 * 	@RequestMapping("getCartsByUserId")// This API is being used to fetch all the cart items for the particular user which is fetched via userID which is email.
 */

package com.project.pantry.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pantry.Controller.pojo.ApiResponse;
import com.project.pantry.JWTToken.ShoppingCartCheck;
import com.project.pantry.model.Cart;
import com.project.pantry.service.AddtoCartService;
import com.project.pantry.service.impl.ProductServices;

@RestController
@RequestMapping("api/addtocart")
public class AddtoCartController {
	
	@Autowired
	AddtoCartService cartService;
	@RequestMapping("addProduct")
	@CrossOrigin
  	public ResponseEntity<?> addCartwithProduct(@RequestBody HashMap<String,String> addCartRequest) {
		try {
			String keys[] = {"productId","email","qty"};
			if(ShoppingCartCheck.validationWithHashMap(keys, addCartRequest)) {// Here JWT Authentication Check is being done 
				
			}
			long productId = Long.parseLong(addCartRequest.get("productId")); // Fetching values from JSON
			String email =  (addCartRequest.get("email")); 
			int qty =  Integer.parseInt(addCartRequest.get("qty"));
			List<Cart> obj = cartService.addCartbyUserIdAndProductId(productId,email,qty);// Calling the CartService to add product to the database
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));// Exception checks
		}
		
   }
	
	@RequestMapping("updateQtyForCart")
	@CrossOrigin
  	public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String,String> addCartRequest) {
		try {
			String keys[] = {"cartId","email","qty"};
			if(ShoppingCartCheck.validationWithHashMap(keys, addCartRequest)) {//JWT check
				
			}
			long cartId = Long.parseLong(addCartRequest.get("cartId")); //Fetching values from JSON
			String email =  (addCartRequest.get("email")); 
			int qty =  Integer.parseInt(addCartRequest.get("qty")); 
    		cartService.updateQtyByCartId(cartId, qty);
			 List<Cart> obj = cartService.getCartByUserId(email);// Calling Service class to consume the API and update it in database
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
		
   }
	
	
	@RequestMapping("removeProductFromCart")
	@CrossOrigin
  	public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String,String> removeCartRequest) {
		try {
			String keys[] = {"email","cartId"};// getting values from JSON
			if(ShoppingCartCheck.validationWithHashMap(keys, removeCartRequest)) {
				
			}
			List<Cart> obj = cartService.removeCartByUserId(Long.parseLong(removeCartRequest.get("cartId")), (removeCartRequest.get("email")));// Calling service to consume API and delete cart made in DB
			return ResponseEntity.ok(obj);
		}catch(Exception e) {
				return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}		
   }
	
	@RequestMapping("getCartsByUserId")
	@CrossOrigin
  	public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest) {
		try {
			String keys[] = {"email"};// Fetch Email
			if(ShoppingCartCheck.validationWithHashMap(keys, getCartRequest)) {
			}
			List<Cart> obj = cartService.getCartByUserId((getCartRequest.get("email"))); //Calling Service to fetch list of objects of Cart to be displayed in Checkout Page. 
			return ResponseEntity.ok(obj);
		}catch(Exception e) {
				return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));// Bad Response Exceptions are handled here
		}	
   }
}