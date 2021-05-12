package com.smbms.mapper;

import com.smbms.pojo.Provider;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ProviderMappersTest {

    @Test
    public void getByProviderCode() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        ProviderMappers bean = context.getBean(ProviderMappers.class);
        Provider dfsddsd_122 = bean.getByProviderCode("BJ_GYS001");
        if (dfsddsd_122 == null) {
            System.out.println("可用");
        }else {
            System.out.println("不可用");
        }
    }
}