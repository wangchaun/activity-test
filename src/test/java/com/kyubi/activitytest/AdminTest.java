package com.kyubi.activitytest;

import com.alibaba.fastjson.JSON;
import com.mysteel.admin.Admin;
import com.mysteel.admin.AdminService;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description: AdminTest 类
 * @projectName: activity-test
 * @className: AdminTest
 * @author: wangsiming
 * @createTime: 2020/11/24 15:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivityTestApplication.class)
@ContextConfiguration("classpath:dubbo-consumer.xml")
public class AdminTest {

    @Resource
    private AdminService adminService;

    @Test
    public void test(){
        Admin admin = adminService.getAdminById(441370l);
//        Admin admin = adminService.getAdminByUsername("祁超");
        System.out.println(JSON.toJSONString(admin));
    }
}
