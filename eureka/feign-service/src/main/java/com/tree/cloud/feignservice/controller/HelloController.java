package com.tree.cloud.feignservice.controller;

import com.tree.cloud.feignservice.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Resource
    HelloService helloService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return helloService.hello();
    }

    @RequestMapping(value = "/var", method = RequestMethod.GET)
    public String var(){
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("tree")).append("\n");
        sb.append(helloService.hello("tree", 18)).append("\n");
        sb.append(helloService.hello(new HelloService.User("tree", 20))).append("\n");
        return sb.toString();
    }

}
