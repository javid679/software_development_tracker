package com.project.pantry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.pantry.Repository.adminRepository;
import com.project.pantry.model.AdminLogin;
import com.project.pantry.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	adminRepository admin;
	@Override
	public AdminLogin findByEmail(String email) throws Exception {
		return admin.findByEmail(email).orElseThrow(()->new Exception("Admin user Not found.."));
	}

}
