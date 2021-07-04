package com.cognizant.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {

	String validateToken(String Token);
}
