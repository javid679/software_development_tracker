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
@RequestMapping("api/forgot")
public class ForgotController {
	@Autowired
	  UserService userservice;
	@RequestMapping("user")
	@CrossOrigin
	public ResponseEntity<?> forgotPassword(@RequestBody HashMap<String,String> forgotRequest) {
		try {
			User user = userservice.forgotuser(forgotRequest);
			return  ResponseEntity.ok(user);
		}catch(Exception e ) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
}
