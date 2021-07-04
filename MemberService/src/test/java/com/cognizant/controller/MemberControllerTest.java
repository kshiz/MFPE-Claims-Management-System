package com.cognizant.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.cognizant.controller.model.MemberPolicy;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private List<MemberPolicy> employees;
	@MockBean
	private MemberController mc;

	@Test
	public void testViewBill() throws Exception {
		System.out.println(mc);
		when(mc.viewBill("1001", "2002")).thenReturn(getTestData());
		mockMvc.perform(get("/viewbill/1001/2002")

				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].memberPolicyId", is(2))).andExpect(jsonPath("$[0].premiumAmtDue", is(25000)))
				.andDo(print());

	}

	private List<MemberPolicy> getTestData() {
		MemberPolicy mp1 = new MemberPolicy();
		mp1.setMemberPolicyId(2);
		mp1.setMemberid("1001");
		mp1.setPolicyId("2002");
		mp1.setPremiumAmtDue(25000);
		mp1.setLastPaymentInfo("Paid");

		List<MemberPolicy> mp = new ArrayList<MemberPolicy>();
		mp.add(mp1);
		return mp;
	}
}
