package com.innominds.service.internal;

import com.innominds.dao.UserDao;
import com.innominds.service.UserService;
import com.innominds.vo.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByUsername(String username) {
        System.out.println(userDao);
        return userDao.getUserByUsername(username);
    }

    @Override
    public int getAge(long id) {

        return userDao.getAge(id);
    }

}
