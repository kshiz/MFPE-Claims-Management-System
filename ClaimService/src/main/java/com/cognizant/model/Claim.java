package com.cognizant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
//no use
@Data
@Entity
@Table(name = "claims")
public class Claim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long claimId;
	
	@Column(name = "claim_Status")
	private String claimStatus;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "policy_Details")
	private String policyDetails;
	
	@Column(name = "hospital_Detail")
	private String hospitalDetail;
	
	@Column(name = "benefits_Availed")
	private String benefitsAvailed;
	
	@Column(name = "amount_Claimed")
	private long amountClaimed;
	
	@Column(name = "settled_Amt")
	private long settledAmt;

}
