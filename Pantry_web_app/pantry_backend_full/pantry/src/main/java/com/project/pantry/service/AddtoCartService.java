package com.project.pantry.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.pantry.model.Cart;
import com.project.pantry.model.CheckoutCart;

@Service
public interface AddtoCartService {
	List<Cart> addCartbyUserIdAndProductId(long productId,String email,int qty) throws Exception;
	void updateQtyByCartId(long cartId,int qty) throws Exception;
	List<Cart> getCartByUserId(String email);
	List<Cart> removeCartByUserId(long cartId,String email);
	List<Cart> removeAllCartByUserId(String email);	
	//CheckOutCart
	List<CheckoutCart> getAllCheckoutByUserId(String email);
	List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp, String email)  throws Exception;
}