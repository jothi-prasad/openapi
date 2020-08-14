package org.openapitools.api.impl;

import javax.servlet.ServletRequest;

public class Authorize implements BaseAuthorize {

    public boolean authorize(ServletRequest request) {
        System.out.println("httprequest headers.." + request.getRemoteAddr() + request.getContentType() + request.getRemoteHost());
        if(request.getAttribute("test").toString().equalsIgnoreCase("x-header")){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean evaluateRequest(String headerParam) {
        System.out.println(" headers.." + headerParam);
        if(headerParam.equalsIgnoreCase("testingheadertoken")){
            return true;
        }
        else {
            return false;
        }


    }

}
