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
 * The type Oracle sql session template.
 *
 * @author chen.cheng
 */
@Configuration
@MapperScan(basePackages = "com.ct.biz.dao.oracle", sqlSessionTemplateRef  = "oracleSqlSessionTemplate")
public class DriverLicenseSqlSessionTemplate {

    /**
     * Secondary sql session factory sql session factory.
     *
     * @param dataSource the data source
     * @return the sql session factory
     * @throws Exception the exception
     * @author chen.cheng
     */
    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/oracle/*.xml"));
        return bean.getObject();
    }

    /**
     * Secondary transaction manager platform transaction manager.
     *
     * @param dataSource the data source
     * @return the platform transaction manager
     * @author chen.cheng
     */
    @Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager oracleTransactionManager(
            @Qualifier("oracleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Secondary sql session template sql session template.
     *
     * @param sqlSessionFactory the sql session factory
     * @return the sql session template
     * @throws Exception the exception
     * @author chen.cheng
     */
    @Bean(name = "oracleSqlSessionTemplate")
    public SqlSessionTemplate oracleSqlSessionTemplate(
            @Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
