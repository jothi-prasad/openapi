package org.openapitools.api;

import com.fasterxml.jackson.databind.ser.Serializers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

public class BaseHttpServlet {
    @Context
    public static HttpServletRequest httpServletRequest;
    @Context
    public static HttpServletResponse httpServletResponse;
//    public static HttpServletRequest request = null;
//    public static HttpServletResponse response = null;

//    public BaseHttpServlet(HttpServletRequest request, HttpServletResponse response){
////        this.request = request;
////        this.response = response;
//        this.setServletRequest(request);
//    }

    public BaseHttpServlet() {
        System.out.println("const.. invoked");
        System.out.println("request inside basehttpservlet class.." + httpServletRequest);
        this.setServletRequest();
    }

    public static HttpServletRequest getServletRequest(){
        System.out.println("request inside basehttpservlet class.." + httpServletRequest);
        return httpServletRequest;
    }
    public static HttpServletRequest setServletRequest(){
        System.out.println("request inside basehttpservlet class.." + httpServletRequest);
        return httpServletRequest;
    }
    public static HttpServletResponse getServletResponse(){
        return httpServletResponse;
    }
}
