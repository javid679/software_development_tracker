package com.project.pantry.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pantry.model.Category;


public interface CategoryRepository  extends JpaRepository<Category, Long> {

}