/**
 * @author webpantry
 * @RequestMapping("api") This API is being used to logout the user in which JWT token has been set to null in database
 */
package com.project.pantry.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pantry.service.UserService;

@RestController
@RequestMapping("api")
 
public class LogoutController {
	@Autowired
	UserService userservice;
	@RequestMapping("Logout")
	@CrossOrigin
	public ResponseEntity<?> Logout(@RequestBody HashMap<String, String> logoutRequest) {
		try
		{
			userservice.logoutUser(logoutRequest.get("email"));// fetching Email from JSON and calling service to logout the user by removing JWT Token from DB
			return ResponseEntity.ok("Logout Successful");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());// Handling Excpetions of Badd Error Request.
		}
	}


}
