package com.example.demo.serviceImpl;

import com.example.demo.bean.ResponseModelDto;
import com.example.demo.bean.UserBean;
import com.example.demo.bean.UserResponseBean;
import com.example.demo.entity.Users;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Order(1)
    @Test
    void create() {
        UserBean userBean = new UserBean();
        userBean.setEmail("test6@gmail.com");
        userBean.setPassword("##");
        Users users = userService.create(userBean);

        Assertions.assertNotNull(users.getUserId());
        Assertions.assertEquals(6,users.getUserId());

        boolean isMatched = userService.matchPassword(userBean.getPassword(), users.getPassword());
        Assertions.assertTrue(isMatched);
    }

    @Order(2)
    @Test
    void login() {

        UserBean userBean = new UserBean();
        userBean.setEmail("test1@gmail.com");
        userBean.setPassword("##");

        ResponseModelDto<UserResponseBean> users = userService.login(userBean);
        Assertions.assertNotNull(users);

    }

}