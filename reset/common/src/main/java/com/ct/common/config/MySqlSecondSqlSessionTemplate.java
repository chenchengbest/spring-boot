package com.ct.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * The type My sql sql session template.
 *
 * @author chen.cheng
 */
@Configuration
@MapperScan(basePackages = "com.ztesoft.timer.dao.trafficIndex", sqlSessionTemplateRef  = "secondarySqlSessionTemplate")
public class MySqlSecondSqlSessionTemplate {
    /**
     * Primary sql session factory sql session factory.
     *
     * @param dataSource the data source
     * @return the sql session factory
     * @throws Exception the exception
     * @author chen.cheng
     */
    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/trafficIndexMysql/*.xml"));
        return bean.getObject();
    }

    /**
     * Primary transaction manager platform transaction manager.
     *
     * @param dataSource the data source
     * @return the platform transaction manager
     * @author chen.cheng
     */
    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Primary sql session template sql session template.
     *
     * @param sqlSessionFactory the sql session factory
     * @return the sql session template
     * @throws Exception the exception
     * @author chen.cheng
     */
    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
