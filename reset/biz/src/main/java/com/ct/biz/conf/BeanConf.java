package com.ct.biz.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.ct.common.utils.IdGenerate;

import javax.annotation.Resource;


/**
 * The type Ali engine api conf.
 *
 * @author chen.cheng
 */
@Configuration
public class BeanConf {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * Inter alarm type config ali engine api properties.
     *
     * @return the ali engine api properties
     * @author chen.cheng
     */
    @Bean(name = "idGenerate")
    public IdGenerate idGenerateBeanConf() {
        return new IdGenerate();
    }


    @Bean
    public RedisTemplate<Object,Object> redisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }

}
