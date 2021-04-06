package com.sbjenkins.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbjenkins.model.Product;
import com.sbjenkins.service.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
     ProductService service;
   
	@GetMapping("/products")
  public	ResponseEntity<List<Product>> getProducts(){
		List<Product> productList =  service.getAllProducts();
	return new ResponseEntity<>(productList, HttpStatus.OK);
	}
  
	

}
