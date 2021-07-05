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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.cognizant.repo.PolicyProviderRepo;
import com.cognizant.utility.EligibilityClaimResponse2;
import com.cognizant.utility.ProviderResult;

//@WebMvcTest(value = PolicyController.class)
//@WithMockUser
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PolicyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PolicyController ppr;

	@Test
	public void testGetData() throws Exception {

		System.out.println(ppr);		
		when(ppr.getData("2004")).thenReturn(getEcr());
		mockMvc.perform(get("/getdata/2004").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("policyName", is("Mental Insurance")))
				.andExpect(jsonPath("totalClaimedAmt", is(10000000))).andDo(print());

//		Mockito.when(ppr.getData(Mockito.anyString())).thenReturn(ecr);
//		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getdata/2004").accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		System.out.println(result.getResponse());
//		String expected = "{policyName: Mental Insurance, totalClaimedAmt: 10000000}";
//		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void getChainOfProviders() throws Exception {

		System.out.println(ppr);		
		when(ppr.getChainOfProviders("2004")).thenReturn(getPr());
		mockMvc.perform(get("/getChainOfProviders/2004").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("provider_Name", is("UnitedHealth")))
				.andExpect(jsonPath("location", is("New Jersey"))).andDo(print());
	}
	
	public EligibilityClaimResponse2 getEcr() {
		EligibilityClaimResponse2 ecr = new EligibilityClaimResponse2();
		ecr.setPolicyName("Mental Insurance");
		ecr.setTotalClaimedAmt(10000000);
		return ecr;
	}
	
	public ProviderResult getPr() {
		ProviderResult pr = new ProviderResult();
		pr.setProvider_Name("UnitedHealth");
		pr.setLocation("New Jersey");
		return pr;
	}

}
