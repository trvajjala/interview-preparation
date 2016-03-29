package com.tvajjala.web.resource;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tvajjala.repository.UserRepository;
import com.tvajjala.vo.User;

@RestController
public class LoginResource {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" })
    public String login(User user) {
        final Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale);
        return "some token";
    }

    @RequestMapping(value = "/user/one", method = RequestMethod.GET)
    public User getUser() {
        System.out.println("getUser");
        return new User();
    }

}
