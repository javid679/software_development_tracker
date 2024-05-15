/**
 * @author webpantry
 * LoginController user Login API which is checking for JWT Token if already present there and not expired otherwise create a new one for the user
 */
package com.project.pantry.Controller;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.pantry.Controller.pojo.ApiResponse;
import com.project.pantry.Controller.pojo.LoginPojo;
import com.project.pantry.JWTToken.AuthLogin;
import com.project.pantry.JWTToken.JWTTokenProvider;
import com.project.pantry.JWTToken.UserPrincipal;
import com.project.pantry.Repository.userRepository;
import com.project.pantry.model.User;
import com.project.pantry.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api")
public class LoginController {
	
	@Autowired
	  UserService userservice;
	@Autowired
	AuthLogin authLogin;
	@Autowired
	JWTTokenProvider tokenProvider;
	@Autowired
	userRepository userRepo;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping("login/user")//post and get
	@CrossOrigin
	public ResponseEntity<?> userLogin(@RequestBody LoginPojo loginRequest) {
		
		try {
			
        	Authentication authentication =  authLogin.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()) ,loginRequest);// Passing email and password to check for token expiration and token generation
        	SecurityContextHolder.getContext().setAuthentication(authentication); // Returning Login Token as field to Frontend to send it over in every request.
        	String token = tokenProvider.generateToken(authentication);// Tken Generation
    		JSONObject obj =  this.getUserResponse(token);
    		if(obj == null) {
    			throw new Exception("Error while generating Reponse");// If no user exists setting response
    		}
    		saveUserToken(token);
	        return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);// 200 OK if authenicated
	        
    	}catch(Exception e ) {
    		logger.info("Error in authenticateUser ",e);
    		return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
    		
    	}
		
	}
	private void saveUserToken(String token) throws Exception {// if TOken is generated for first time for the user saving it in DB to authenticate for the next time 
		try {
			User user = userservice.getUserDetailById(_getUserId());
			user.setLoginToken(token);
			userRepo.save(user);
		}
		catch (Exception e) {
			throw new Exception(e.getMessage()+" Error while Saving Token");
		}
    	
		
		
	}
	private JSONObject getUserResponse(String token) {// Getting User response from the service via Email and password and adding new parameter token to it to send as response
    	
    	try {
			User user = userservice.getUserDetailById(_getUserId());
			HashMap<String,String> response = new HashMap<String,String>();
			response.put("user_id", ""+_getUserId());
			response.put("email", user.getEmail());
			response.put("name", user.getUsername());	// Setting Reponse Data
			JSONObject obj = new JSONObject();// Creating JSONObject to put all values from DB to return it as JSON response
			
			obj.put("user_profile_details",response);
			obj.put("token", token);
			logger.info(obj.toString());
			return obj;
		} catch (Exception e) {
			logger.info("Error in getUserResponse ",e);
		}
    	
    	return null;
    }
 
 	private long _getUserId() {// Validating user for security API.
    	logger.info("user id vaildating. "+ SecurityContextHolder.getContext().getAuthentication());
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("(LoginController)user id is "+userPrincipal.getId());
		return userPrincipal.getId();
	}
}
