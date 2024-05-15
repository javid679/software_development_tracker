package com.project.pantry.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.project.pantry.model.User;

@Service
public interface UserService {
	User findByEmail(String email) throws Exception;
	User getUserDetailById(long userId) throws Exception;
	User signUpUser(HashMap<String,String> signupRequest) throws Exception;
	User forgotuser(HashMap<String,String> forgotRequest) throws Exception;
    void verifyuser(HashMap<String,String> forgotRequest) throws Exception;
	void verifysignup(HashMap<String, String> verifyRequest) throws Exception;
	void logoutUser(String email) throws Exception;

	
}
