package com.project.pantry.JWTToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class JavaSecurity {
	 	@Autowired
	    CustomerDetails customUserDetailsService;
//	 	@Autowired
//		BCryptPasswordEncoder eEncrypt;
	    @Autowired
	    private JWTEntryPoint unauthorizedHandler;
	    
	    @Bean
	    public JWTFilter jwtAuthenticationFilter() {
	        return new JWTFilter();
	    }

	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	         
	        authProvider.setUserDetailsService(customUserDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder());
	     
	        return authProvider;
	    }
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
	      return authConfiguration.getAuthenticationManager();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

        @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	                .cors()
	                    .and()
	                .csrf()
	                    .disable()
	                .exceptionHandling()
	                    .authenticationEntryPoint(unauthorizedHandler)
	                    .and()
	                .sessionManagement()
	                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                    .and()
	                .authorizeRequests()
	                    .antMatchers("/",
	                        "/favicon.ico",
	                        "/**/*.png",
	                        "/**/*.gif",
	                        "/**/*.svg",
	                        "/**/*.jpg",
	                        "/**/*.html",
	                        "/**/*.css",
	                        "/**/*.js")
	                        .permitAll()
	                    .antMatchers("/api/status/**","/api/login/**","/api/signup/**","/api/forgot/**","/api/verify/**","/api/addtocart/**","/api/product/**","/api/verifySignup/**","/rest-api/admin/**","/api/AddProduct/**","/api/Logout/**","/api/export/**","/api/survey/**","/api/order/**")
	                        .permitAll()
	                        .antMatchers(HttpMethod.GET, "/api/status/**")
	                        .permitAll()
	                    .anyRequest()
	                        .authenticated();

	        // Add our custom JWT security filter
	        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	        return http.build();

	    }
}