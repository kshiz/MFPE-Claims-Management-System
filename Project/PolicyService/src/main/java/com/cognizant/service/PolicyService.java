package com.cognizant.service;

import org.springframework.stereotype.Service;

import com.cognizant.model.ProviderPolicy;

@Service
public interface PolicyService {
	
	ProviderPolicy saveProviderPolicy(ProviderPolicy providerPolicy);

}
