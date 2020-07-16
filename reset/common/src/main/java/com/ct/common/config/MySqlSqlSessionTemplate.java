package com.ct.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.ct.common.aop.SQLStatsInterceptor;

/**
 * The type My sql sql session template.
 *
 * @author chen.cheng
 */
@Configuration
@MapperScan(basePackages = "com.ct.biz.dao.mysql", sqlSessionTemplateRef  = "primarySqlSessionTemplate")
public class MySqlSqlSessionTemplate {

    @Autowired
    private SQLStatsInterceptor sqlStatsInterceptor;
    /**
     * Primary sql session factory sql session factory.
     *
     * @param dataSource the data source
     * @return the sql session factory
     * @throws Exception the exception
     * @author chen.cheng
     */
    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mysql/*.xml"));
        bean.setPlugins(new Interceptor[] {sqlStatsInterceptor});
        return bean.getObject();
    }

    /**
     * Primary transaction manager platform transaction manager.
     *
     * @param dataSource the data source
     * @return the platform transaction manager
     * @author chen.cheng
     */
    @Bean(name = "primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
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
    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
