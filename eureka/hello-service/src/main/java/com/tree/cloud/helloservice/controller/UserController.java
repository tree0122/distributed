package com.tree.cloud.helloservice.controller;

import com.tree.cloud.helloservice.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@RequestParam("name") String name){
        return "hello " + name;
    }

    @RequestMapping(value = "hello2", method = RequestMethod.GET)
    public User hello2(@RequestHeader String name, @RequestHeader Integer age){
        return new User(name, age);
    }

    @RequestMapping(value = "hello3", method = RequestMethod.POST)
    public String hello3(@RequestBody User user){
        return user.toString();
    }

}
