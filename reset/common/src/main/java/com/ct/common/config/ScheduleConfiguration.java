package com.ct.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * The type Schedule configuration.
 *
 * @author chen.cheng
 */
@Configuration
public class ScheduleConfiguration {
    /**
     * Task scheduler task scheduler.
     *
     * @return the task scheduler
     * @author chen.cheng
     */
    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        return taskScheduler;
    }
}
