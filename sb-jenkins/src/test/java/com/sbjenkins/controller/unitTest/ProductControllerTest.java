package com.sbjenkins.controller.unitTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sbjenkins.controller.ProductController;
import com.sbjenkins.model.Product;
import com.sbjenkins.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	
	
	@Test
	public void getProductEndPoint() throws Exception {
		Product mockProduct = new Product(1001,"Linux","Fossa-tossa");
		List<Product> allProducts = new ArrayList<>();
		allProducts.add(mockProduct);
		Mockito.when(productService.getAllProducts()).thenReturn(allProducts);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		String expected =  "[{'id':1001,'brand':'Linux','serviceTag':'Fossa-tossa'}]";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}



}