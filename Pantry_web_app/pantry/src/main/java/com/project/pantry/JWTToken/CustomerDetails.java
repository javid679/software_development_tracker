package com.project.pantry.JWTToken;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.pantry.model.User;
import com.project.pantry.service.UserService;


@Service
public class CustomerDetails implements UserDetailsService {

	@Autowired
	UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerDetails.class);
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
    	try {
            User user = userService.findByEmail(email);   
            return UserPrincipal.create(user);
    	}catch(Exception e) {
	   		throw new UsernameNotFoundException("User not found with Mobile : " + email);
	   	}
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
    	try {
   		 User user = userService.getUserDetailById(id);
   	        return UserPrincipal.create(user);
	   	}catch(Exception e) {
	   		throw new UsernameNotFoundException("User not found with id : " + id);
	   	}   
        //return UserPrincipal.create(user);
    }
   

}