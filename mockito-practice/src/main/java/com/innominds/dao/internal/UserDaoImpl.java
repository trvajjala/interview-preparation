package com.innominds.dao.internal;

import com.innominds.dao.UserDao;
import com.innominds.vo.User;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUserByUsername(String username) {

        return new User(1, username, 20);
    }

    @Override
    public int getAge(long id) {
        return 0;
    }

}
