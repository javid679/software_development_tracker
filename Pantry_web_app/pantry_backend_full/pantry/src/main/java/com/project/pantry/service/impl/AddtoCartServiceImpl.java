package com.project.pantry.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.pantry.Repository.AddtoCartRepository;
import com.project.pantry.Repository.CheckoutRepository;
import com.project.pantry.model.Cart;
import com.project.pantry.model.CheckoutCart;
import com.project.pantry.model.Products;
import com.project.pantry.service.AddtoCartService;
import com.project.pantry.service.UserService;

@Service
public class AddtoCartServiceImpl implements AddtoCartService {

	@Autowired
	AddtoCartRepository addCartRepo;
	@Autowired
	ProductServices proServices;
	@Autowired
	CheckoutRepository checkoutRepo;
	@Autowired
	EmailService emailService;
	private static final Logger logger = LoggerFactory.getLogger(AddtoCartServiceImpl.class);

	@Override
	public List<Cart> addCartbyUserIdAndProductId(long productId, String email,int qty) throws Exception {
		try {
			if(addCartRepo.getCartByProductIdAnduserId(email, productId).isPresent()){
				throw new Exception("Product is already exist.");
			}
			Cart obj = new Cart();
			obj.setQty(qty);
			obj.setEmail(email);
			Products pro = proServices.getProductsById(productId);
			obj.setProduct(pro); 
			//TODO price has to check with quantity
			addCartRepo.save(obj);		
			return this.getCartByUserId(email);	
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(""+e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public List<Cart> getCartByUserId(String email) {
		return addCartRepo.getCartByuserId(email);
	}

	@Override
	public List<Cart> removeCartByUserId(long cartId, String email) {
		addCartRepo.deleteCartByIdAndUserId(email, cartId);
		return this.getCartByUserId(email);
	}

	@Override
	public void updateQtyByCartId(long cartId, int qty) throws Exception {
		addCartRepo.updateQtyByCartId(cartId,qty);
	}
	
	@Override
	public List<Cart> removeAllCartByUserId(String email) {
		addCartRepo.deleteAllCartByUserId(email);
		return null;
	}

	@Override
	public List<CheckoutCart> getAllCheckoutByUserId(String email) {
		return checkoutRepo.getByuserId(email);
	}

	@Override
	public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp,String email) throws Exception {
		try {
			if(tmp.size() >0) {
				checkoutRepo.saveAll(tmp);
				emailService.sendOrderConfrimation(email,tmp.get(0).getOrder_id());
				this.removeAllCartByUserId(email);
				
				return this.getAllCheckoutByUserId(email);
			}	
			else {
				throw  new Exception("Should not be empty");
			}
		}catch(Exception e) {
			throw new Exception("Error while checkout "+e.getMessage());
		}
	}

}