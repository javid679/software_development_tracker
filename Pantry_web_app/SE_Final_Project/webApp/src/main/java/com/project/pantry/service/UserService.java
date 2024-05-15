package com.project.pantry.service;
// UserService
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.project.pantry.model.User;

@Service
public interface UserService {
	User findByEmail(String email) throws Exception;// find user by email
	User getUserDetailById(long userId) throws Exception;// get user details
	User signUpUser(HashMap<String,String> signupRequest) throws Exception;// signup User service for signUp Controller
	User forgotuser(HashMap<String,String> forgotRequest) throws Exception;// forgot user for Forgot Controller
    void verifyuser(HashMap<String,String> forgotRequest) throws Exception;// verifyuser for verifying forgot password OTP
	void verifysignup(HashMap<String, String> verifyRequest) throws Exception;// verifySignup to register user and do 2Factor Authentication
	void logoutUser(String email) throws Exception;// Logout service to delete token from DB

	
}
