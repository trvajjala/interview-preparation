package com.innominds.service;

import com.innominds.vo.User;

public interface UserService {

    public User getUserByUsername(String username);

    public int getAge(long id);

}
