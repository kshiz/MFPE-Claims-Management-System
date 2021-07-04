package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import com.cognizant.controller.model.ClaimStatusResult;
import com.cognizant.controller.model.MemberPolicy;
import com.cognizant.controller.model.MemberSubmitClaim;
import com.cognizant.controller.model.Members;
import com.cognizant.exception.TokenValidationFailedException;
import com.cognizant.repo.MembersPolicyRepo;
import com.cognizant.service.MembersService;

@RestController
public class MemberController {

	@Autowired
	private MembersService membersService;

	@Autowired
	private MembersPolicyRepo membersPolicyRepo;

	@Autowired
	private RestTemplate restTemp;

	@PostMapping("/savememb")
	public Members getMemberdetails(@RequestBody Members members) {
		return this.membersService.savaMembers(members);

	}

	@GetMapping("/viewbill/{membId}/{policyId}")
	public List<MemberPolicy> viewBill(@PathVariable("membId") String membid,
			@PathVariable("policyId") String policyId) {
		return this.membersPolicyRepo.viewbill(membid, policyId);

	}

	@PostMapping("/submitclaim")
	public MemberSubmitClaim submitClaim(@RequestBody MemberSubmitClaim memberSubmitClaim) {
		return this.membersService.saveClaim(memberSubmitClaim);

	}


	@GetMapping("/getClaimStatus/{claimId}/{policyId}/{memberId}")
	public ClaimStatusResult getClaimStatus(@PathVariable("claimId") long claimId,
			@PathVariable("policyId") long policyId, @PathVariable("memberId") long memberId) {
		ClaimStatusResult result = restTemp.getForObject(
				"http://claimservice/getClaimStatus/" + claimId + "/" + policyId + "/" + memberId,
				ClaimStatusResult.class);
		return result;

	}

}
