package io.swagger.api;

import io.swagger.api.impl.UserApiServiceImpl;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class YANISBinder extends AbstractBinder {

    //Singleton instance
    private static YANISBinder instance;

    private final UserApiServiceImpl yanisImpl;

    private YANISBinder() {
        this.yanisImpl = new UserApiServiceImpl();
    }

    @Override
    protected void configure() {
        bind(yanisImpl).to(UserApi.class);
    }

    public static YANISBinder getInstance() {
        if (instance == null) {
            synchronized (YANISBinder.class) {
                if (instance == null) {
                    instance = new YANISBinder();
                }
            }
        }
        return instance;
    }
}
