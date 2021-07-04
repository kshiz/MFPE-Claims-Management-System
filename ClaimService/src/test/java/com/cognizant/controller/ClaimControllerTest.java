package com.cognizant.controller;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.utility.ClaimStatusResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClaimControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClaimController cr;

	
	
	@Test
	public void getClaimStatus() throws Exception {

		System.out.println(cr);		
		when(cr.getClaimStatus(3001, 2001, 1001)).thenReturn(getCsr());
		mockMvc.perform(get("/getClaimStatus/3001/2001/1001").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("claimStatus", is("Accepted")))
				.andExpect(jsonPath("memberId", is("1001")))
				.andExpect(jsonPath("claimId", is("3001")))
				.andExpect(jsonPath("claimDesc", is("Amount will be transfered soon"))).andDo(print());
	}
	
	
	public ClaimStatusResult getCsr() {
		ClaimStatusResult csr = new ClaimStatusResult();
//		pr.setProvider_Name("UnitedHealth");
//		pr.setLocation("New Jersey");
		csr.setClaimStatus("Accepted");
		csr.setClaimDesc("Amount will be transfered soon");
		csr.setMemberId("1001");
		csr.setClaimId("3001");
		return csr;
	}

}
