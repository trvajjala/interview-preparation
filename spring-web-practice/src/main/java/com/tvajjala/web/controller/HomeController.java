package com.tvajjala.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tvajjala.repository.UserRepository;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        System.out.println(" userRepository :" + userRepository);
        // userRepository.save(new UserEntity());

        final Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale);
        return "home";
    }
}
