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
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 数据库配置类
 * 2018/12/4  23:46
 * created by zhoumb
 */
@Configuration
public class DBConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.ds_user")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource userDataSource() {
        return userDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource userDataSource) {
        return new JdbcTemplate(userDataSource);
    }
}
