package com.tvajjala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tvajjala.bean.User;
import com.tvajjala.dao.UserDao;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private UserDao userDao;

    public void insert(User user) throws Exception {
        userDao.insertRecord(user);
    }

    public User getAllRecordsById(Integer id) {
        return userDao.getUserRecordsById(id);
    }

    public List<User> getUserRecords() {
        return userDao.getUserRecords();
    }
}