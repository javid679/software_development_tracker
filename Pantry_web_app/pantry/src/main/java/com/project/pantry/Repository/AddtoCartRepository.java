package com.project.pantry.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pantry.model.Cart;

@Repository
public interface AddtoCartRepository extends JpaRepository<Cart,Long> {

	
	//remove cart by userid and cartId,
	//getCartByuserId
	
	@Query("Select addCart  FROM Cart addCart WHERE addCart.email=:email")
	List<Cart> getCartByuserId(@Param("email")String email);
	@Query("Select addCart  FROM Cart addCart ")
	Optional<Cart> getCartByuserIdtest();
	@Query("Select addCart  FROM Cart addCart WHERE addCart.product.id= :product_id and addCart.email=:email")
	Optional<Cart> getCartByProductIdAnduserId(@Param("email")String email,@Param("product_id")Long product_id);
	@Modifying
    @Transactional
	@Query("DELETE  FROM Cart addCart WHERE addCart.id =:cart_id   and addCart.email=:email")
	void deleteCartByIdAndUserId(@Param("email")String email,@Param("cart_id")Long cart_id);
	@Modifying
    @Transactional
	@Query("DELETE  FROM Cart addCart WHERE   addCart.email=:email")
	void deleteAllCartByUserId(@Param("email")String email);
	
	@Modifying
    @Transactional
	@Query("DELETE  FROM Cart addCart WHERE addCart.email=:email")
	void deleteAllCartUserId(@Param("email")Long email);
	@Modifying
    @Transactional
	@Query("update Cart addCart set addCart.qty=:qty WHERE addCart.id=:cart_id")
	void updateQtyByCartId(@Param("cart_id")Long cart_id,@Param("qty")Integer qty);
	
}