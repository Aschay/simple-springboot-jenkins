package com.sbjenkins.controller.unitTest;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sbjenkins.config.InstanceInfoService;
import com.sbjenkins.controller.InfoController;
import com.sbjenkins.controller.ProductController;
import com.sbjenkins.model.Product;
import com.sbjenkins.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {InfoController.class, ProductController.class})
@AutoConfigureMockMvc
public class ControllersTest {
	
	@Autowired
	private MockMvc mvc;
	@MockBean
	private ProductService productService;
	@MockBean
	private InstanceInfoService infoService;
	

	@Test
    public void testHelloEndpoint() throws UnsupportedEncodingException, Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").accept(MediaType.ALL_VALUE);
		String result = mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
		String expected =  "hello there !";
		assertEquals(expected, result);
    }
	

		@Test
		public void getProductEndPoint() throws Exception {
			Product mockProduct = new Product(1001,"Linux","Fossa-tossa");
			List<Product> allProducts = new ArrayList<>();
			allProducts.add(mockProduct);
			Mockito.when(productService.getAllProducts()).thenReturn(allProducts);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			String expected =  "[{'id':1001,'brand':'Linux','serviceTag':'Fossa-tossa'}]";
			JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
		}

		@Test 
		public void testInfoEndpoint() throws Exception {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/application").accept(MediaType.ALL_VALUE);
			int result = mvc.perform(requestBuilder).andReturn().getResponse().getStatus();
			int expected =  200;
			assertEquals(expected, result);
			
		}
	



}
