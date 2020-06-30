package com.ct.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * The type Multi data source config.
 *
 * @author chen.cheng
 */
@Configuration
public class MultiDataSourceConfig {


    /**
     * Primary data source data source.
     *
     * @return the data source
     * @author chen.cheng
     */
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.first")
    public DataSource primaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Primary data source data source.
     *
     * @return the data source
     * @author chen.cheng
     */
    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.second")
    public DataSource secondDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Oracle data source data source.
     *
     * @return the data source
     * @author chen.cheng
     */
    @Bean(name = "oracleDataSource")
    @Qualifier("oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.oracle")
    public DataSource oracleDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * Impala data source data source.
     *
     * @return the data source
     * @author chen.cheng
     */
    @Bean(name = "impalaDataSource")
    @Qualifier("impalaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.impala")
    public DataSource impalaDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
