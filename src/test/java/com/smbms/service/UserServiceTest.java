package com.smbms.service;

import com.smbms.mapper.ProviderMappers;
import com.smbms.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void getUserCount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        UserService bean = context.getBean(UserService.class);
        List<User> getlist=bean.getuserlist(null,null,6,5);
        for (User user : getlist) {
            System.out.println(user.getUserRoleName());
        }
    }
}