package com.ct.config;

import com.ct.common.aop.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Interceptor config.
 *
 * @author chen.cheng
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * Add interceptors.
     *
     * @param registry the registry
     * @author chen.cheng
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");
    }

    /**
     * Authentication interceptor authentication interceptor.
     *
     * @return the authentication interceptor
     * @author chen.cheng
     */
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}

