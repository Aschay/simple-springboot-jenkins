package com.sbjenkins.controller.unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.sbjenkins.controller.InfoController;


public class BasicControllersTest {
	
	
	@Test
    public void testHelloEndpoint() {
       InfoController infoController = new InfoController();
        String result = infoController.sayHello();
        assertEquals("hello there !",result);
    }
	


}
