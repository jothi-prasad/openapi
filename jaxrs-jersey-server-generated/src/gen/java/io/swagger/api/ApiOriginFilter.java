package io.swagger.api;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-08-06T23:24:24.936Z[GMT]")public class ApiOriginFilter implements javax.servlet.Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type");
        System.out.print("xheader" +request.getAttribute("x-header"));
        System.out.print("content typ" +request.getAttribute("Content-Type"));
        System.out.print("ip" +request.getRemoteAddr());
        chain.doFilter(request, response);
    }

    public void destroy() {
        System.out.print("filter destroyed...");
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.print("filetr init.." + filterConfig.getFilterName());

    }
}