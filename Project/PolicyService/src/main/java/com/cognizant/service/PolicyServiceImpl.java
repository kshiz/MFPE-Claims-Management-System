package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.ProviderPolicy;
import com.cognizant.repo.PolicyProviderRepo;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyProviderRepo policyProviderRepo;

	@Override
	public ProviderPolicy saveProviderPolicy(ProviderPolicy providerPolicy) {

		return policyProviderRepo.save(providerPolicy);
	}

}
