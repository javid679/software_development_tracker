package com.project.pantry.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pantry.Repository.CheckoutRepository;
import com.project.pantry.model.CheckoutCart;
import com.project.pantry.service.impl.OrderPDFService;

@RestController
 
public class OrderPDFController {
	@Autowired
	CheckoutRepository checkout;
	@GetMapping("/api/export/orderpdf")
	@CrossOrigin
	public ResponseEntity<?> Logout(HttpServletResponse response) {
		try
		{
			response.setContentType("application/pdf");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);
            List<CheckoutCart> orders = checkout.findAll(Sort.by("email").ascending());
            OrderPDFService pdf = new OrderPDFService(orders);
            pdf.export(response);
			return ResponseEntity.ok("Logout Successful");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
