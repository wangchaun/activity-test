package com.kyubi.activitytest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * @author Kyubi
 * @date 2020-09-29 14:53
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = DataSourceContextHolder.getDbType();
//        log.info("使用数据源 {}", datasource);
        return datasource;
    }


    public DataSource getActuallyDataSource() {
        Object lookupKey = determineCurrentLookupKey();
        if (null == lookupKey) {
            return this;
        }
        DataSource determineTargetDataSource = this.determineTargetDataSource();
        return determineTargetDataSource == null ? this : determineTargetDataSource;

    }

}
