package com.sbjenkins.controller.unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.sbjenkins.controller.InfoController;


public class BasicControllersTest {
	
	
	@Test
    public void testHelloEndpoint() {
       InfoController infoController = new InfoController();
        String result = infoController.sayHello();
        assertEquals(result, "hello there !");
    }
	
	
	@Value("${app.message}")
	private String envMessage;
	
	@Test
    public void testEnvEndpoint() {
		
       InfoController infoController = new InfoController();
        String result = infoController.getDdbEnv();
        assertEquals(result, envMessage);
       
    }

}
