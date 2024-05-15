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
	public User findByEmail(String email) throws Exception {
		return userRepo.findByEmail(email).orElseThrow(()->new Exception("User Not found.."));
	}

	@Override
	public User getUserDetailById(long userId) throws Exception {
		return userRepo.findById(userId).orElseThrow(()->new Exception("User Not found.."));
	}

	@Override
	public User signUpUser(HashMap<String, String> signupRequest) throws Exception {
    
		try {
			if(userRepo.findByEmail(signupRequest.get("email")).isPresent()) {
				throw new Exception("User is already registered with Email ID.");
			}
			User user = new User();
			user.setUsername(signupRequest.get("name"));
			user.setEmail(signupRequest.get("email"));
			user.setPassword(signupRequest.get("password"));
			String otp = generateOTP();
			emailservice.sendEmailOTP(user,otp);
			user.setOtp(otp);
			userRepo.save(user);
			return user;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private String generateOTP() {
		Random random = new Random();
		int otp = random.nextInt(90000000)+10000000;
		return String.format("%08d", otp);
	}


	@Override
	public User forgotuser(HashMap<String,String> forgotRequest) throws Exception {
		try
		{
			if(!(userRepo.findByEmail(forgotRequest.get("email")).isPresent())) {
				throw new Exception("Email ID not Registered");
			}
			User user = findByEmail(forgotRequest.get("email"));
			String otp = generateOTP();
			user.setForgot_otp(otp);
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
		if(!(user.getForgot_otp().equals(verifyRequest.get("forgot_otp"))))
		{
			throw new Exception("Invalid OTP Credentials");
		}
		user.setPassword(verifyRequest.get("password"));
		userRepo.save(user);
	
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public void verifysignup(HashMap<String, String> verifyRequest) throws Exception {
		try
		{
		User user = findByEmail(verifyRequest.get("email"));
		if(!(user.getOtp().equals(verifyRequest.get("otp"))))
		{
			throw new Exception("Invalid OTP Credentials");
		}
		user.setEmailVerified(1);
		userRepo.save(user);
	
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
    @Override
	public void logoutUser(String email) throws Exception {
		try {
			User user = findByEmail(email);
			user.setLoginToken(null);
			userRepo.save(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}


}
