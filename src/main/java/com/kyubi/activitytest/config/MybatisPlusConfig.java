package com.kyubi.activitytest.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.kyubi.activitytest.enums.DBTypeEnum;
import com.kyubi.activitytest.handlers.MyMetaObjectHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyubi
 */
@EnableTransactionManagement //开启事务
@Configuration  //spring中常用到注解，与xml配置相对立。是两种加载bean方式
public class MybatisPlusConfig {

    @Bean(name = "localDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.local")
    public DataSource localDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "onlineDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.online")
    public DataSource onlineDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    public PaginationInnerInterceptor paginationInterceptor() {
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        paginationInterceptor.setMaxLimit(100000L);
        return paginationInterceptor;
    }


    /**
     * 动态数据源配置
     *
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("localDataSource") DataSource db1,
                                         @Qualifier("onlineDataSource") DataSource db2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.LOCAL.getValue(), db1);
        targetDataSources.put(DBTypeEnum.ONLINE.getValue(), db2);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        // 程序默认数据源，这个要根据程序调用数据源频次，经常把常调用的数据源作为默认
        dynamicDataSource.setDefaultTargetDataSource(db2);

        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(localDataSource(), onlineDataSource()));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        //PerformanceInterceptor(),OptimisticLockerInterceptor()
        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }

    /**
     * @return
     * @Title:
     * @methodName: 定义事务
     * @param:
     * @Description: 定义事务
     * @author: 作者名 byu@xiaohongshu.com
     * @date: 19/2/22 下午3:33
     */
    @Bean
    public DataSourceTransactionManager businessTransactionManager(@Qualifier("onlineDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * @return
     * @Title:
     * @methodName: 定义事务
     * @param:
     * @Description: 定义事务
     * @author: 作者名 byu@xiaohongshu.com
     * @date: 19/2/22 下午3:33
     */
    @Bean
    public DataSourceTransactionManager activitiTransactionManager(@Qualifier("localDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public GlobalConfig globalConfiguration() {
        GlobalConfig conf = new GlobalConfig();
        conf.setDbConfig(new GlobalConfig.DbConfig());
        conf.getDbConfig().setLogicDeleteField("yn");
        conf.getDbConfig().setLogicDeleteValue("N");
        conf.getDbConfig().setLogicNotDeleteValue("Y");
        conf.setMetaObjectHandler(new MyMetaObjectHandler());
        return conf;
    }

    public OptimisticLockerInnerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }



//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }
//
//    /**
//     * 新的分页插件,一缓和二缓遵循mybatis的规则,
//     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
//     */
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(paginationInterceptor());
//        interceptor.addInnerInterceptor(optimisticLockerInterceptor());
//        return interceptor;
//    }
}