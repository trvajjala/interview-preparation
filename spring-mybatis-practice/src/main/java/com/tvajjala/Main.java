package com.tvajjala;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tvajjala.bean.User;
import com.tvajjala.service.UserService;

public class Main {

    public static void main(String[] args) throws Exception {

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        final UserService userService = ctx.getBean(UserService.class);
        userService.insert(new User("ThirupathiReddy", "FLAT-103, SREE NILAYAM ,SAI RAM"));
        System.out.println(userService.getAllRecordsById(1));
        System.err.println(userService.getUserRecords());
        ctx.close();
    }
}
