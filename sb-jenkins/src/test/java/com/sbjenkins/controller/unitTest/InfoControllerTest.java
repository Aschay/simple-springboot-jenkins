package com.sbjenkins.controller.unitTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sbjenkins.config.InstanceInfoService;
import com.sbjenkins.controller.InfoController;
@RunWith(SpringRunner.class)
@WebMvcTest(InfoController.class)
public class InfoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private InstanceInfoService infoService;
	
	
	@org.junit.Test
	public void getInfo() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("hello there !")));
	}

}
