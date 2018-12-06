/**
 * Date:     2018/12/423:46
 * AUTHOR:   Administrator
 */
package com.zhou.springdtxdbdb.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 数据库配置类
 * 2018/12/4  23:46
 * created by zhoumb
 */
@Configuration
public class DBConfiguration {

    @Bean
    @Primary        //能够在spring容器中存在多个同类型的时候选择哪个
    @ConfigurationProperties(prefix = "spring.ds_user")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource userDataSource() {
        return userDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource userDataSource) {
        return new JdbcTemplate(userDataSource);
    }

    @Bean
//    @Primary
    @ConfigurationProperties(prefix = "spring.ds_order")
    public DataSourceProperties orderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
//    @Primary
    public DataSource orderDataSource() {
        return orderDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource orderDataSource) {
        return new JdbcTemplate(orderDataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager userTM = new DataSourceTransactionManager(userDataSource());
        DataSourceTransactionManager orderTM = new DataSourceTransactionManager(orderDataSource());
        ChainedTransactionManager chainedTransactionManager = new ChainedTransactionManager(userTM, orderTM);
        return chainedTransactionManager;
    }

}
