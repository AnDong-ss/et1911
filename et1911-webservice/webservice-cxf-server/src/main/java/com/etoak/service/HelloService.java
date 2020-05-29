package com.etoak.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "HelloServiceWS",
portName = "HelloServiceWSPort")
public interface HelloService {
    @WebMethod
    String sayHello(String name);
}
