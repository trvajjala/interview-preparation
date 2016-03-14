package com.tvajjala.test;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tvajjala.Application;
import com.tvajjala.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@ActiveProfiles("prod")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    DataSource dataSource;

    @Test
    public void userServiceTest() {

        Assert.assertNotNull(userService);
        Assert.assertEquals("userService", userService.getName());
        System.out.println(dataSource);
        Assert.assertNotNull(dataSource);

    }
}
