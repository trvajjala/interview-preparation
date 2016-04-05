package com.tvajjala.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.tvajjala.Application;
import com.tvajjala.exception.ServiceException;
import com.tvajjala.persistence.vo.User;
import com.tvajjala.web.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
// This annotation creates webContext for the testCase
public class RestApplicationTest {

    @Autowired
    UserService userService;

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    RestTemplate restTemplate = new TestRestTemplate();

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    public void getUserByUsername() throws ServiceException {
        restTemplate.getForObject("http://localhost:" + 8084 + "/spring-web-practice/api/user/username/admin", User.class);
    }

    public void saveUser() {

    }
}
