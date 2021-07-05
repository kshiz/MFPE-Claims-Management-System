package com.cognizant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.controller.model.Members;

@Repository
public interface MemberRepo extends JpaRepository<Members, Long>{

}
