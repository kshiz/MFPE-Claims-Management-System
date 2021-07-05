package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.helper.MemberSubmitClaim;
import com.cognizant.helper.ProviderPolicy;
import com.cognizant.helper.ProviderPolicyBenefit;
import com.cognizant.model.ClaimStatus;
import com.cognizant.repo.ClaimStatusRepo;

@Service
public class ClaimStatusImpl implements ClaimService {

	@Autowired
	private ClaimStatusRepo claimStatusRepo;

	@Override
	public ClaimStatus updateStatus(ClaimStatus claimStatus) {
		return this.claimStatusRepo.save(claimStatus);
	}

}
