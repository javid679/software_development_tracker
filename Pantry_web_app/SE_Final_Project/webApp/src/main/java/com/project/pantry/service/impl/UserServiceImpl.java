package com.project.pantry.service.impl;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.pantry.Repository.userRepository;
import com.project.pantry.model.User;
import com.project.pantry.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	userRepository userRepo;
	
	@Autowired
	EmailService emailservice;

	@Override
	public User findByEmail(String email) throws Exception {// Find User by Email in DB
		return userRepo.findByEmail(email).orElseThrow(()->new Exception("User Not found.."));
	}

	@Override
	public User getUserDetailById(long userId) throws Exception {
		return userRepo.findById(userId).orElseThrow(()->new Exception("User Not found.."));// get user detils for JWT Authentication
	}

	@Override
	public User signUpUser(HashMap<String, String> signupRequest) throws Exception {// Add or register new user 
    
		try {
			if(userRepo.findByEmail(signupRequest.get("email")).isPresent()) {
				throw new Exception("User is already registered with Email ID.");// find by email
			}
			User user = new User();
			user.setUsername(signupRequest.get("name"));// Set Name, Email, password in DB
			user.setEmail(signupRequest.get("email"));
			user.setPassword(signupRequest.get("password"));
			String otp = generateOTP();
			emailservice.sendEmailOTP(user,otp);// Generate OTP to send to emailservice which will send email to the user for 2 Factor Authentication
			user.setOtp(otp);
			userRepo.save(user);
			return user;
		}catch(Exception e) {
			throw new Exception(e.getMessage());// Handling Excpetion
		}
	}

	private String generateOTP() {// Creating 8 digit OTP for the verification
		Random random = new Random();
		int otp = random.nextInt(90000000)+10000000;
		return String.format("%08d", otp);
	}


	@Override
	public User forgotuser(HashMap<String,String> forgotRequest) throws Exception {// forgot user 
		try
		{
			if(!(userRepo.findByEmail(forgotRequest.get("email")).isPresent())) {// validating email Exists or not
				throw new Exception("Email ID not Registered");
			}
			User user = findByEmail(forgotRequest.get("email"));
			String otp = generateOTP();// generate OTP
			user.setForgot_otp(otp); //and send it over email for verification
			emailservice.sendEmailOTP(user,otp);
			userRepo.save(user);
			return user;

		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void verifyuser(HashMap<String, String> verifyRequest) throws Exception {
		try
		{
		User user = findByEmail(verifyRequest.get("email"));
		if(!(user.getForgot_otp().equals(verifyRequest.get("forgot_otp"))))// validating OTP in request and checking with DB
		{
			throw new Exception("Invalid OTP Credentials");
		}
		user.setPassword(verifyRequest.get("password"));// If found ssetting new password for the user 
		userRepo.save(user);// Saving the password in DB
	
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public void verifysignup(HashMap<String, String> verifyRequest) throws Exception {
		try
		{
		User user = findByEmail(verifyRequest.get("email"));// Fetching email
		if(!(user.getOtp().equals(verifyRequest.get("otp"))))// Fetching OTP
		{
			throw new Exception("Invalid OTP Credentials");
		}
		user.setEmailVerified(1);// authenticating the user after signup and now he can login to order the products
		userRepo.save(user);// saving the email verified status to 1 in DB
	
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
    @Override
	public void logoutUser(String email) throws Exception {
		try {
			User user = findByEmail(email);
			user.setLoginToken(null);// Setting Login TOken to Null to Logout the customer
			userRepo.save(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}


}
