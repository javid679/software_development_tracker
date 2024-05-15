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
			if(ShoppingCartCheck.validationWithHashMap(keys, addCartRequest)) {
				
			}
			long productId = Long.parseLong(addCartRequest.get("productId")); 
			String email =  (addCartRequest.get("email")); 
			int qty =  Integer.parseInt(addCartRequest.get("qty"));
			List<Cart> obj = cartService.addCartbyUserIdAndProductId(productId,email,qty);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
		
   }
	
	@RequestMapping("updateQtyForCart")
	@CrossOrigin
  	public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String,String> addCartRequest) {
		try {
			String keys[] = {"cartId","email","qty"};
			if(ShoppingCartCheck.validationWithHashMap(keys, addCartRequest)) {
				
			}
			long cartId = Long.parseLong(addCartRequest.get("cartId")); 
			String email =  (addCartRequest.get("email")); 
			int qty =  Integer.parseInt(addCartRequest.get("qty")); 
    		cartService.updateQtyByCartId(cartId, qty);
			 List<Cart> obj = cartService.getCartByUserId(email);
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
			String keys[] = {"email","cartId"};
			if(ShoppingCartCheck.validationWithHashMap(keys, removeCartRequest)) {
				
			}
			List<Cart> obj = cartService.removeCartByUserId(Long.parseLong(removeCartRequest.get("cartId")), (removeCartRequest.get("email")));
			return ResponseEntity.ok(obj);
		}catch(Exception e) {
				return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}		
   }
	
	@RequestMapping("getCartsByUserId")
	@CrossOrigin
  	public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest) {
		try {
			String keys[] = {"email"};
			if(ShoppingCartCheck.validationWithHashMap(keys, getCartRequest)) {
			}
			List<Cart> obj = cartService.getCartByUserId((getCartRequest.get("email")));
			return ResponseEntity.ok(obj);
		}catch(Exception e) {
				return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}	
   }
}