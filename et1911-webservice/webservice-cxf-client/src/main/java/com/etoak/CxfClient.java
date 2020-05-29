package com.etoak;

import com.etoak.service.HelloService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class CxfClient {
    public static void main(String[] args) {

        //JaxWsPro
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();

        factoryBean.setAddress("http://localhost:8000/hello?wsdl");


        //
        factoryBean.setServiceClass(HelloService.class);

        HelloService service = (HelloService) factoryBean.create();
        System.out.println(service);

        System.out.println(service.sayHello("CXF" ));

    }
}
