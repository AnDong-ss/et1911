package com.etoak;

import com.etoak.service.impl.HelloServiceImpl;
import com.etoak.service.impl.HelloServiceImplService;

public class JdkClient {
    public static void main(String[] args) {
        HelloServiceImplService service = new HelloServiceImplService();
        HelloServiceImpl soap = service.getHelloServiceImplPort();
        String result = soap.sayHello("WebService");
        System.out.println(result);
    }
}
