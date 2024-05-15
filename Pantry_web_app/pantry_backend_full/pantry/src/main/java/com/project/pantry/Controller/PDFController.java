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
			response.setContentType("application/pdf");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);
            List<Products> products = productRepo.findAll(Sort.by("name").ascending());
            PDFService pdf = new PDFService(products);
            pdf.export(response);
			return ResponseEntity.ok("Logout Successful");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
