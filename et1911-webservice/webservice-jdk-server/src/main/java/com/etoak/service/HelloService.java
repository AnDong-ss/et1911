package com.etoak.service;

import javax.jws.WebService;
/*
* 表示这是一个web service接口
* 在接口和实现类上都必须加上@WebService注解
*
* */
@WebService
public interface HelloService {
    String sayHello(String name);
}
