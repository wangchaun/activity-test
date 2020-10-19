//package com.kyubi.activitytest.config;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//
///**
// * @author Kyubi
// * @date 2020-10-19 10:07
// */
//@Slf4j
//public class SpringJdbcTemplate extends JdbcTemplate {
//
//    @Override
//    public DataSource getDataSource() {
//        DynamicDataSource router =  (DynamicDataSource) super.getDataSource();
//        if (router == null) {
//            return super.getDataSource();
//        }
//        DataSource actuallyDataSource = router.getActuallyDataSource();
//        log.info("getDataSource={}", JSON.toJSONString(actuallyDataSource));
//        return actuallyDataSource;
//
//    }
//}
