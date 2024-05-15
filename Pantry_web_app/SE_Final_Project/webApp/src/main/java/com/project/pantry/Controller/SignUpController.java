/**
 * @author webpantry
 * @RequestMapping("api/signup") This API is being used to create newuser with all the details and send email with OTP for 2 Factor Authentication.
 * 
 */
package com.project.pantry.Controller;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pantry.Controller.pojo.ApiResponse;
import com.project.pantry.model.User;
import com.project.pantry.service.UserService;

@RestController
@RequestMapping("api/signup")
public class SignUpController {
	@Autowired
	  UserService userservice;
	@CrossOrigin
	@RequestMapping("user")
	public ResponseEntity<?> userLogin(@RequestBody HashMap<String,String> signupRequest) {
		try {
			//TODO validation has to add for client request
			User user = userservice.signUpUser(signupRequest);//Calling Service to consume request and add new user in DB and send email to authenticate
			return  ResponseEntity.ok(user);
		}catch(Exception e ) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
}


