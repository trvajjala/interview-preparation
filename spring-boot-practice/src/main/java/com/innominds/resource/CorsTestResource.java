package com.innominds.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innominds.persistence.vo.User;

@RestController
public class CorsTestResource {

    @RequestMapping("/user")
    public ResponseEntity<User> user() {

        final User user = new User();
        user.setUsername("Thiru");
        user.setPassword("1234");

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping("/cors")
    @Secured("ADMIN")
    public ResponseEntity<User> cors() {

        final User user = new User();
        user.setUsername("Thiru");
        user.setPassword("1234");

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}