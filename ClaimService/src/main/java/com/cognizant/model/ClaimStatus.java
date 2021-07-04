package com.cognizant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "claimstatus")
public class ClaimStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long claimstatusId;
	
	@Column(name = "claim_Id")
	private String claimId;
	
	@Column(name = "policy_Id")
	private String policyId;
	
	@Column(name = "member_Id")
	private String memberId;
	
	@Column(name = "claim_Status")
	private String claimStatus;
	
	@Column(name = "status_Des")
	private String statusDes;

}
