package com.sbjenkins;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class SbJenkinsApplicationTests {

	@Test
	void contextLoads() {
		String result= "test";
		 assertEquals("test", result);
	}
	

}

