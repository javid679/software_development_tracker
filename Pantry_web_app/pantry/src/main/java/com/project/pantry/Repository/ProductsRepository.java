package com.project.pantry.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pantry.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
	@Query("Select pro FROM Products pro WHERE pro.cat_id=:cat_id")
	List<Products> getByCategoryId(@Param("cat_id")long cat_id);
}