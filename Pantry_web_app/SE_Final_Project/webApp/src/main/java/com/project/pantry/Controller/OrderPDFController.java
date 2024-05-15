/**
 * @author webpantry
 * 	@GetMapping("/api/export/orderpdf") This is a GET API to fetch all the Orders from the DB and show as PDF.
 */
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
			response.setContentType("application/pdf");// setting response as PDF
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = currentDateTime + ".pdf";// creating pdf file name with current date
			response.setHeader(headerKey, headerValue);
            List<CheckoutCart> orders = checkout.findAll(Sort.by("email").ascending());
            OrderPDFService pdf = new OrderPDFService(orders);// Calling Service to consume API and create PDF with Design
            pdf.export(response);
			return ResponseEntity.ok("PDF Created Successfully");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());//Handling Excpetions
		}
	}


}
