package com.project.pantry.service;

import org.springframework.stereotype.Service;

import com.project.pantry.model.AdminLogin;
@Service
public interface AdminService {
	AdminLogin findByEmail(String email) throws Exception;// Admin Service findbyEmail is defined here

}
