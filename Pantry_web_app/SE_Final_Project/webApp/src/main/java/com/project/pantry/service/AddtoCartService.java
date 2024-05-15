package com.project.pantry.service;
// Add to Cart Service Interface defines all the Addto Cart services used by the application
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.pantry.model.Cart;
import com.project.pantry.model.CheckoutCart;

@Service
public interface AddtoCartService {
	List<Cart> addCartbyUserIdAndProductId(long productId,String email,int qty) throws Exception;// Add Product to Cart method is defined here 
	void updateQtyByCartId(long cartId,int qty) throws Exception;
	List<Cart> getCartByUserId(String email);// Get Cart of the User is defined here 
	List<Cart> removeCartByUserId(long cartId,String email); //remove cart is deined here 
	List<Cart> removeAllCartByUserId(String email);	
	//CheckOutCart
	List<CheckoutCart> getAllCheckoutByUserId(String email);// Checkout cart to place ordr 
	List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp, String email)  throws Exception;// save products for checkout
}