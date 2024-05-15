package com.project.pantry.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.project.pantry.model.User;

@Service
public class EmailService {


   @Autowired
   JavaMailSender mailSender;

	public void sendEmailOTP(User user, String OTP) throws Exception {
		try {
		String toAddress = user.getEmail();
		String username = user.getUsername();
	    String fromAddress = "webapppantry@gmail.com";
	    String senderName = "THE PANTRY";
	    String subject = "Please verify your registration";
	    String content = "Dear "+username+",<br>"
	            + "Please enter the OTP to verify your email:<br>"
	            + "<h3>"+OTP+"</h3>"
	            + "Thank you,<br>"
	            + senderName+".";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);	     	     
	    helper.setText(content, true);
	    
	    mailSender.send(message); 
	
	}
	catch(Exception e)
	{
		throw new Exception(e.getMessage());
		
	}
}
	public void sendOrderConfrimation(String email, String orderID) throws Exception
	{
		try {
			String toAddress = email;
		    String fromAddress = "webapppantry@gmail.com";
		    String senderName = "THE PANTRY";
		    String subject = "Order Confirmed Successfully";
		    String content = "Dear User,<br>"
		            + "You Order Has been Confirmed:<br>"
		    		+ "Please share orderID with Pantry Team to get your order:<br>"
		            + "<h3>"+orderID+"</h3><br>"
		            +"Note: Pantry is open only on Monday-Tuesday in Housing and Thursday(Walb-Union)<br>"
		            + "Thank you,<br>"
		            + senderName+".";
		     
		    MimeMessage message = mailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(message);
		     
		    helper.setFrom(fromAddress, senderName);
		    helper.setTo(toAddress);
		    helper.setSubject(subject);	     	     
		    helper.setText(content, true);
		    
		    mailSender.send(message); 
		
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
}