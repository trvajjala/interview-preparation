package com.innominds.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.innominds.dao.UserDao;
import com.innominds.service.UserService;
import com.innominds.service.internal.UserServiceImpl;
import com.innominds.vo.User;

@RunWith(MockitoJUnitRunner.class)
public class FirstMockitoTest {

    @Test
    public void taxTest() {

        final UserDao userDao = mock(UserDao.class);

        System.out.println(userDao);
        final UserService userService = new UserServiceImpl(userDao);
        when(userDao.getUserByUsername(anyString())).thenReturn(new User(1l, "ss", 1));

        Assert.assertThat("Thiru", CoreMatchers.is(userService.getUserByUsername("Thiru")));

    }
}
