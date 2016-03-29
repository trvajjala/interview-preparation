package com.tvajjala.test;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tvajjala.bean.User;
import com.tvajjala.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config.xml" })
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertRecordTest() throws Exception {
        userService.insert(new User("ThirupathiReddy" + new Random().nextInt(), "FLAT-103, SREE NILAYAM " + new Random().nextInt()));
    }

    @Test
    public void getRecordById() {
        final User user = userService.getAllRecordsById(1);
        System.out.println("Received : " + user);
        Assert.assertNotNull(user);
    }

}
