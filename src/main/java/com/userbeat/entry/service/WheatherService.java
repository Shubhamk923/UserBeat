package com.userbeat.entry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userbeat.entryapiresponse.WheaterResponse;

@Component
@Service
public class WheatherService {
	
//	@Value("${weather.api.key}")
//	private String apikey;
	private static final  String apikey = "6b2d7d47c87e7eb8975badd04d2d6f50" ; 
	
	private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
	
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	public WheaterResponse getWeather(String city) {
		String finalAPI = API.replace("CITY", city).replace("API_KEY", apikey);
	    ResponseEntity<WheaterResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WheaterResponse.class);
	    WheaterResponse body = response.getBody();	
        return body;
	}

}
