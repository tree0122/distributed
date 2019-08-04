package com.tree.cloud.hystrixservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCollapser(
            batchMethod = "listByIds",
            collapserProperties = {
                @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
            }
    )
    public String getById(Long id){
        String ret = restTemplate.getForObject("http://hello-service/hello", String.class);
        return ret;
    }

//    @HystrixCommand(groupKey = "userService", commandKey = "listByIds", threadPoolKey = "user.list",fallbackMethod = "fallback")
    @HystrixCommand
    public List<String> listByIds(List<Long> ids){
        String ret = restTemplate.getForObject("http://hello-service/hello", String.class);
        List<String> list = ids.stream()
                .map(i -> String.valueOf(i))
                .collect(Collectors.toList());
        list.add(ret);
        return list;
    }

    private List<String> fallback(List<Long> ids){
        return new ArrayList<>();
    }

}
