package com.cognizant.service;

import org.springframework.stereotype.Service;

import com.cognizant.helper.MemberSubmitClaim;
import com.cognizant.helper.ProviderPolicy;
import com.cognizant.helper.ProviderPolicyBenefit;
import com.cognizant.model.ClaimStatus;

@Service
public interface ClaimService {
	ClaimStatus updateStatus(ClaimStatus claimStatus);
//	ProviderPolicy getProviderPolicy(String policyId);
//	ProviderPolicyBenefit getProviderPolicyBenefit(String benefit);
//	MemberSubmitClaim getMemberSubmitClaim(String policyId);
	

}
