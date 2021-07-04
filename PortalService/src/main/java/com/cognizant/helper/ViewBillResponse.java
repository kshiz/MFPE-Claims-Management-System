package com.cognizant.helper;

import java.util.Date;

import lombok.Data;

@Data
public class ViewBillResponse {
	private long memberPolicyId;
	private String memberid;
	private String policyId;
	private Date lastPremiumDate;
	private long premiumAmtDue;
	private String lastPaymentInfo;
	private Date dueDate;

}
