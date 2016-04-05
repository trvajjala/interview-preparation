package com.tvajjala.boot;

import java.util.Collection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

@Configuration
public class DBCountAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean()
    public DBCountRunner dbCountRunner(Collection<CrudRepository> repositories) {
        return new DBCountRunner(repositories);
    }
}
