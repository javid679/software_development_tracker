package com.project.pantry.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pantry.Controller.pojo.LoginPojo;
import com.project.pantry.model.AdminLogin;
import com.project.pantry.service.AdminService;
@RestController
@RequestMapping("rest-api")
public class AdminController {  
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	AdminService adminService;
	@CrossOrigin
	@RequestMapping("admin")//post and get
	public ResponseEntity<?> adminLogin(@RequestBody LoginPojo loginRequest) throws Exception {
		try
		{
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		if(adminService == null) {
			 logger.info("user found the error");
			 throw new BadCredentialsException("1001");
		}
		AdminLogin admin = adminService.findByEmail(email);
		if (admin == null) {
	        throw new BadCredentialsException("Admin User Not found!!");
	  }
		 logger.info("from authentication "+password+" from db "+admin.getPassword());
		 if(!this.passwordMatch(password, admin.getPassword())) {
			 logger.info("Password is wrong for user .."+admin.getEmail()+"-- "+admin.getEmail());
			 throw new BadCredentialsException("Email or password is wrong.");
		 } 

	    return new ResponseEntity<String>("Login Successfully",HttpStatus.OK);
		}
		catch (Exception e) {
			logger.info("Error",e);
			 throw new BadCredentialsException(e.getMessage());
		}

}
	private Boolean passwordMatch(String rawPassword,String from_db_encoded) {
		 return rawPassword.equals(from_db_encoded);
	 }
	

}
