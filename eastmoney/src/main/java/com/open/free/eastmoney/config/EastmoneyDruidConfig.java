package com.open.free.eastmoney.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 描述：数据源 druid 监控注册类
 * Created by lcssx on 6/23/2017.
 */
@Configuration
@MapperScan(basePackages = {"com.open.free.eastmoney.mapper"}, sqlSessionFactoryRef = "eastmoneySqlSessionFactory")
public class EastmoneyDruidConfig {

    private Logger logger = LoggerFactory.getLogger(EastmoneyDruidConfig.class);

    @Value("${spring.datasource.eastmoneyDatabase.url}")
    private String dbUrl;
    /*@Value("${spring.datasource.eastmoneyDatabase.mapperLocations}")
    private String mapperLocations;*/
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.filters}")
    private String filters;

    /**
     * 数据源 DataSource 配置
     */
    @Bean(name = "eastmoneyDataSource")
    @Primary
    public DataSource druidDataSource(){
        DruidDataSource datasource = new DruidDataSource();
        try {
            datasource.setUrl(dbUrl);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("eastmoneyDatabase druid configuration initialization", e);
        }
        return datasource;
    }

    /**
     * SqlSessionFactory 配置
     */
    @Bean(name = "eastmoneySqlSessionFactory")
    @Primary
    public SqlSessionFactory createSqlSessionFactoryBean(
            @Qualifier("eastmoneyDataSource") DataSource dataSource ) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));

        // Mybatis-Plus全局配置
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        //globalConfiguration.setDbColumnUnderline(true);
        sqlSessionFactoryBean.setGlobalConfig(globalConfiguration);

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事物管理器
     */
    @Bean(name = "eastmoneyTransactionManager")
    @Primary
    public DataSourceTransactionManager createDataSourceTransactionManagerBean(
            @Qualifier("eastmoneyDataSource") DataSource dataSource ){
        return DruidConfig.createDataSourceTransactionManagerBean(dataSource);
    }

}
