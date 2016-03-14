package com.tvajjala.service;

import org.springframework.beans.factory.annotation.Value;

public class UserServiceImpl implements UserService {

    @Value("#{T(System).currentTimeMillis()}")
    private String user;

    @Override
    public String getName() {

        return "userService";
    }

    @Override
    public String getSystemProperty() {
        return user;
    }
}
