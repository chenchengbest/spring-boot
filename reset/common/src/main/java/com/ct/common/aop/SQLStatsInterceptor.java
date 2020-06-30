package com.ct.common.aop;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

import com.ct.common.utils.SpringContextUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * The type Sql stats interceptor.
 *
 * @author chen.cheng
 */
@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {
        Connection.class, Integer.class
    })
})
@Component
public class SQLStatsInterceptor implements Interceptor {
    /**
     * The Log.
     *
     * @author chen.cheng
     */
    private final Logger log = LoggerFactory.getLogger(SQLStatsInterceptor.class);

    /**
     * Intercept object.
     *
     * @param invocation the invocation
     * @return the object
     * @throws Throwable the throwable
     * @author chen.cheng
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String prepareSql = this.sqlFilter(boundSql.getSql());
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, prepareSql);
        return invocation.proceed();
    }

    /**
     * Plugin object.
     *
     * @param target the target
     * @return the object
     * @author chen.cheng
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        else {
            return target;
        }
    }

    /**
     * Sets properties.
     *
     * @param properties the properties
     * @author chen.cheng
     */
    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * Sql filter string.
     *
     * @param prepareSql the prepare sql
     * @return the string
     * @author chen.cheng
     */
    private String sqlFilter(String prepareSql) {
        DBSchemeConfig dbSchemeConfig = (DBSchemeConfig) SpringContextUtil.getBean(DBSchemeConfig.class);

        return prepareSql.replaceAll("#dwd#", dbSchemeConfig.getDwd()).replaceAll("#rdnet#", dbSchemeConfig.getRdnet())
            .replaceAll("#signal#", dbSchemeConfig.getSignal()).replaceAll("#screen#", dbSchemeConfig.getScreen())
            .replaceAll("#model#", dbSchemeConfig.getModel())
            .replaceAll("#eventHandle#", dbSchemeConfig.getEventHandle())
            .replaceAll("#dwsM#", dbSchemeConfig.getDwsM());
    }
}
