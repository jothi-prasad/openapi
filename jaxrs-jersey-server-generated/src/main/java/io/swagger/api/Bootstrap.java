package io.swagger.api;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
    info = @Info(
        title = "Swagger Server", 
        version = "1.0.0", 
        description = "No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)",
        termsOfService = "",
        contact = @Contact(email = ""),
        license = @License(
            name = "MIT",
            url = "http://unlicense.org"
        )
    )
)
public class Bootstrap {
}
