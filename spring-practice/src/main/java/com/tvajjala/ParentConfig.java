package com.tvajjala;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tvajjala.service.LifeCycleBean;

@Configuration
public class ParentConfig {

    @Bean
    public LifeCycleBean cacheBean() {
        return new LifeCycleBean();
    }

}
