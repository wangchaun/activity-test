package com.kyubi.activitytest;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kyubi
 */
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@MapperScan("com.kyubi.activitytest.modules.business.*.mapper")
public class ActivityTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityTestApplication.class, args);
    }

}
