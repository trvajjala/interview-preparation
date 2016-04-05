package com.tvajjala.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tvajjala.boot.StartupRunner;

@Configuration
// @ComponentScan(basePackages = { "com.tvajjala" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class ApplicationContextConfig {

    @Bean
    public StartupRunner startupRunner() {
        return new StartupRunner();
    }
}
