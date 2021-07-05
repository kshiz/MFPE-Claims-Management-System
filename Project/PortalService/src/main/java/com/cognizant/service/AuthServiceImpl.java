package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException.BadRequest;


@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private RestTemplate restTemp;
	
	@Override
	public String validateToken(String token){
		try{
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", token);
			
			HttpEntity<Boolean> requestEntity = new HttpEntity<>(null, headers);
			
			ResponseEntity<String> result = restTemp.exchange("http://authorizationservice/api/auth/validate", HttpMethod.GET, requestEntity, String.class); 
			System.out.println(result.getBody());
			if(result.getBody().equals("True")) {
				return "True";
			}
			else {
				return "Invalid";
			}

		}
		catch(BadRequest e) {
			return "Invalid";
		}
	}
}
