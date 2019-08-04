package com.tree.cloud.hystrixservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String hello(){
        return restTemplate.getForObject("http://hello-service/hello", String.class);
    }

    private String fallback(){
        return "error";
    }

}
