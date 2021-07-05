package com.cognizant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.models.MemberSubmitClaim;

@Repository
public interface PortalRepo extends JpaRepository<MemberSubmitClaim, Long>{

}
