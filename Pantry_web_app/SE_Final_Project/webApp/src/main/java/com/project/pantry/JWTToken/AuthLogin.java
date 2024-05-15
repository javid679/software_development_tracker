/**
 * @author webpantry
 * This is JWT Token API which is checking for security checks for the user
 */
package com.project.pantry.JWTToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import com.project.pantry.Controller.pojo.LoginPojo;
import com.project.pantry.model.User;
import com.project.pantry.service.UserService;

@Configuration
public class AuthLogin {
	@Autowired
	UserService userService;
	 private static final Logger logger = LoggerFactory.getLogger(AuthLogin.class);
	 public Authentication authenticate(UsernamePasswordAuthenticationToken authentication,LoginPojo loginRequest) throws AuthenticationException {
	    String email = authentication.getPrincipal() + "";
	    String password = authentication.getCredentials() + "";
	    User user;
		try {
			logger.info("user is going to validate(AuthManager) "+email);
			if(userService == null) {
				 logger.info("user found the error");
				 throw new BadCredentialsException("1001");// Handling Exception if user not found
			}
			user = userService.findByEmail(email);// Finding User if Exists
			if (user == null) {
		        throw new BadCredentialsException("User Not found!!");
		  }
			if(user.isEmailVerified()==0)// Checking if Email is not verified and not authenticating user to Login
			{
				throw new BadCredentialsException("Email Not verified");
			} 
			 logger.info("from authentication "+password+" from db "+user.getPassword());
			 if(!this.passwordMatch(password, user.getPassword())) {
				 logger.info("Password is wrong for user .."+user.getEmail()+"-- "+user.getEmail());
				 throw new BadCredentialsException("Email or password is wrong.");//checking for Password and Email Check
			 } 

		        return new UsernamePasswordAuthenticationToken(new UserPrincipal(user.getId(), email, password), password);
		} catch (Exception e) {
			logger.info("Error",e);
			 throw new BadCredentialsException(e.getMessage());
		}
	   
	} 
	 private Boolean passwordMatch(String rawPassword,String from_db_encoded) {//Password Check Function
		 return rawPassword.equals(from_db_encoded);
		// return BCrypt.checkpw(rawPassword.toString(),from_db_encoded);	 
	 }
	 
}