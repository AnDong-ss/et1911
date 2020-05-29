package com.etoak.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.18
 * 2020-05-29T16:52:23.129+08:00
 * Generated source version: 3.1.18
 * 
 */
@WebServiceClient(name = "HelloServiceWS", 
                  wsdlLocation = "http://localhost:8000/hello?wsdl",
                  targetNamespace = "http://service.etoak.com/") 
public class HelloServiceWS extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.etoak.com/", "HelloServiceWS");
    public final static QName HelloServiceWSPort = new QName("http://service.etoak.com/", "HelloServiceWSPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8000/hello?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(HelloServiceWS.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8000/hello?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public HelloServiceWS(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public HelloServiceWS(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelloServiceWS() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public HelloServiceWS(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public HelloServiceWS(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public HelloServiceWS(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns HelloService
     */
    @WebEndpoint(name = "HelloServiceWSPort")
    public HelloService getHelloServiceWSPort() {
        return super.getPort(HelloServiceWSPort, HelloService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelloService
     */
    @WebEndpoint(name = "HelloServiceWSPort")
    public HelloService getHelloServiceWSPort(WebServiceFeature... features) {
        return super.getPort(HelloServiceWSPort, HelloService.class, features);
    }

}
