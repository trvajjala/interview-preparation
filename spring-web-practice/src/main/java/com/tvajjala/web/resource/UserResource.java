package com.tvajjala.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tvajjala.exception.ServiceException;
import com.tvajjala.persistence.vo.User;
import com.tvajjala.web.service.UserService;

/**
 * It is combination of two annotations @Controller and @ResponseBody
 *
 * @author ThirupathiReddy V
 *
 */
@RestController
@RequestMapping(value = "/user", produces = { "application/json" })
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {

        System.out.println("Reading user with username : " + username);

        final User user = userService.getUserByUsername(username);

        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", consumes = { "application/json" })
    public ResponseEntity<User> saveUser(User user) throws ServiceException {

        userService.saveUser(user);

        return new ResponseEntity<User>(HttpStatus.CREATED);

    }

}
