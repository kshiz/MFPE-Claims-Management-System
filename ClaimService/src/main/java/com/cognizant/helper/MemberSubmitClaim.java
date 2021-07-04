package com.cognizant.helper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "member_submit_claim")
public class MemberSubmitClaim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long claimsId;
	
	@Column(name = "policy_Id")
	private String policyId;
	
	@Column(name = "member_Id")
	private String memberId;
	
	@Column(name = "hospital_Id")
	private String hospitalId;
	
	@Column(name = "total_Bill")
	private long totalBill;
	
	@Column(name = "total_claimed_Amt")
	private long totalClaimedAmt;

}
