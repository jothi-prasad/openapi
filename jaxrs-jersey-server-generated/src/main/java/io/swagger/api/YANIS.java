package io.swagger.api;

import org.glassfish.jersey.server.ResourceConfig;

public class YANIS extends ResourceConfig {

        public YANIS() {
            registerClasses(io.swagger.api.UserApi.class);
            registerClasses(io.swagger.api.UsersApi.class);
            //register(YANISBinder.getInstance());
        }
    }

