package com.etoak;

import com.etoak.service.HelloService;
import com.etoak.service.Impl.HelloServiceImpl;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class CxfSerer {
    public static void main(String[] args) {
        //创建jaxwsservice
        JaxWsServerFactoryBean factory= new
                JaxWsServerFactoryBean();
        //设置wsdl的地址
        factory.setAddress("http://localhost:8000/hello");
        //发布服务接口
        factory.setServiceClass(HelloService.class);
        //设置服务实现
        factory.setServiceBean(new HelloServiceImpl());
        //调用start启动
        Server server = factory.create();
        server.start();
        System.out.println("service start........");

    }

}
