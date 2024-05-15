package com.project.pantry.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pantry.model.AdminLogin;

public interface adminRepository extends JpaRepository<AdminLogin,Long> {// Admin Repository to find Admin Email if exists or not in DB
	Optional<AdminLogin> findByEmail(String email);


}
