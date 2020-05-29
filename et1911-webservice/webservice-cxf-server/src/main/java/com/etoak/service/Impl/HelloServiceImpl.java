package com.etoak.service.Impl;

import com.etoak.service.HelloService;

import javax.jws.WebService;

@WebService(serviceName = "HelloServiceWS",
        portName = "HelloServiceWSPort")
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        System.out.println("");
        return "hello" + name;
    }
}
