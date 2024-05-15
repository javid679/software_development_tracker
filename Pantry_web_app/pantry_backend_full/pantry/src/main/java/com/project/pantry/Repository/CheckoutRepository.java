package com.project.pantry.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pantry.model.CheckoutCart;

@Repository
public interface CheckoutRepository  extends JpaRepository<CheckoutCart, Long> {
	@Query("Select checkCart  FROM CheckoutCart checkCart WHERE checkCart.email=:email")
	List<CheckoutCart> getByuserId(@Param("email")String email);
}