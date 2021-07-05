package com.cognizant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.controller.model.MemberPolicy;
import com.cognizant.controller.model.MemberSubmitClaim;
import com.cognizant.controller.model.Members;

@Service
public interface MembersService {
	Members savaMembers(Members members);
	Members getMemberByid(long id) throws Exception;
	MemberPolicy saveMemberPolicy(MemberPolicy memberPolicy);
	List<MemberPolicy> ViewBill(long membId,long policyId);
	MemberSubmitClaim saveClaim(MemberSubmitClaim memberSubmitClaim);

}
