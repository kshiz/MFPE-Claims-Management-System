package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.ClaimStatus;
import com.cognizant.repo.ClaimStatusRepo;
import com.cognizant.service.ClaimService;
import com.cognizant.utility.ClaimStatusResult;

@RestController
public class ClaimController {

	@Autowired
	private ClaimService claimService;

	@Autowired
	private ClaimStatusRepo claimStatusRepo;

	// Submit Claim Status(file)
	@PostMapping("/submitClaimStatus")
	public ClaimStatus SubmitClaimStatus(@RequestBody ClaimStatus claimStatus) {
		return this.claimService.updateStatus(claimStatus);

	}

	// GET: /getClaimStatus (Input: Claim_ID, Policy_ID, Member_ID | Output: Claim
	// Status, Claim Status Description)(file)
	@GetMapping("/getClaimStatus/{claimId}/{policyId}/{memberId}")
	public ClaimStatusResult getClaimStatus(@PathVariable("claimId") long claimId,
			@PathVariable("policyId") long policyId, @PathVariable("memberId") long memberId) {
		ClaimStatus newclaimClaimStatus = claimStatusRepo.getClaimStatus(claimId, policyId, memberId); // select * ka
																										// content

		ClaimStatusResult claimStatusResult = new ClaimStatusResult();
		claimStatusResult.setClaimId(newclaimClaimStatus.getClaimId());
		claimStatusResult.setMemberId(newclaimClaimStatus.getMemberId());
		claimStatusResult.setClaimStatus(newclaimClaimStatus.getClaimStatus());
		claimStatusResult.setClaimDesc(newclaimClaimStatus.getStatusDes());
		return claimStatusResult;
	}

}
