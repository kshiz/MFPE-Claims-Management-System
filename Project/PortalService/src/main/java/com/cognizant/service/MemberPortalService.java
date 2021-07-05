package com.cognizant.service;

import org.springframework.stereotype.Service;

import com.cognizant.models.MemberSubmitClaim;
@Service
public interface MemberPortalService {
	void memberSubmitClaim(MemberSubmitClaim memberSubmitClaim);

}
