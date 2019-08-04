package com.tree.cloud.feignservice.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "hello-service", fallback = HelloService.HelloFallBack.class)
public interface HelloService {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();

    @RequestMapping(value = "/user/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/user/hello2", method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/user/hello3", method = RequestMethod.POST)
    String hello(@RequestBody User user);

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class User {
        private String name;
        private Integer age;
    }

    @Component
    class HelloFallBack implements HelloService{

        @Override
        public String hello() {
            return "error";
        }

        @Override
        public String hello(String name) {
            return name + " error";
        }

        @Override
        public User hello(String name, Integer age) {
            return new User("sth", 0);
        }

        @Override
        public String hello(User user) {
            return user.toString() +  " error";
        }
    }
}
