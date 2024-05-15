/**
 * @author webapantry
 * Verify OTP API to set new password for the user if he clicks forgot password Button
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
@RequestMapping("api/verify")
public class VerifyOTPController {
	@Autowired
	  UserService userservice;
	@RequestMapping("user")
	@CrossOrigin
	public ResponseEntity<?> verifyOTP(@RequestBody HashMap<String,String> verifyRequest) throws Exception {
		
		userservice.verifyuser(verifyRequest);// Calling Service to set new password for the user and OTP
		return  ResponseEntity.ok("Password has been Changed Successfully");
		

}
}