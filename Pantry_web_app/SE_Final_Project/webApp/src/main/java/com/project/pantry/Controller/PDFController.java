/**
 * @author webpantry
 * 	@GetMapping("/api/export/pdf") This API is being used by Admin User to fetch the Inventory of products
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

import com.project.pantry.Repository.ProductsRepository;
import com.project.pantry.model.Products;
import com.project.pantry.service.impl.PDFService;

@RestController
 
public class PDFController {
	@Autowired
	ProductsRepository productRepo;
	@GetMapping("/api/export/pdf")
	@CrossOrigin
	public ResponseEntity<?> Logout(HttpServletResponse response) {
		try
		{
			response.setContentType("application/pdf");// Setting response as PDF
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = currentDateTime + ".pdf";// Creating File name with current date Time
			response.setHeader(headerKey, headerValue);
            List<Products> products = productRepo.findAll(Sort.by("name").ascending());
            PDFService pdf = new PDFService(products);// Finding ALL the Prducts and displaying in PDF with Design
            pdf.export(response);
			return ResponseEntity.ok("PDF Created Successfully");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());//Handling Excpetions
		}
	}


}
