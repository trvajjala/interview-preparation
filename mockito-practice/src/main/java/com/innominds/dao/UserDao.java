package com.innominds.dao;

import com.innominds.vo.User;

public interface UserDao {

    public User getUserByUsername(String username);

    public int getAge(long id);
}
