package com.sbjenkins.controller.integrationTest;


import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.sbjenkins.SbJenkinsApplication;
import com.sbjenkins.model.Product;

@SpringBootTest(classes = SbJenkinsApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllersIT {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	

	
	@Test
	public void testGetProducts() throws URISyntaxException {
	    final String baseUrl = "http://localhost:" + port+ "/api/products";
	    URI uri = new URI(baseUrl);
	    ResponseEntity<Product[]> result = this.restTemplate.getForEntity(uri, Product[].class);
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    int res = result.getBody().length;
	    Assert.assertEquals(res,3);
	  
	}
	@Test
	public void testGetApplication() throws URISyntaxException{
		 final String baseUrl = "http://localhost:" + port+ "/application";
		    URI uri = new URI(baseUrl);
		    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
		    Assert.assertEquals(200, result.getStatusCodeValue());
	}
	@Test
	public void testGetHello() throws URISyntaxException{
		 final String baseUrl = "http://localhost:" + port+ "/";
		    URI uri = new URI(baseUrl);
		    ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
		    Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	
}