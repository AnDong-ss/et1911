package com.etoak.service.impl;

import com.etoak.service.HelloService;

import javax.jws.WebService;

@WebService
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        System.out.println("sayHello invoke~~");
        return "Hello "+name;
    }
}
