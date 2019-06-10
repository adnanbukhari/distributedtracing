package com.ups.mock.target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class TargetRESFfullAPI {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/process")
    public String process(String message) {
    	System.out.println("Message Received: " + message);
        return "Your request has processed sucessfully!  " + message;
    }
}
