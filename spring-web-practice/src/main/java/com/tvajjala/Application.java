package com.tvajjala;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * don't use boot specific classes to run the application.<br>
 * make everything customized until you familiarize with annotation based configuration
 *
 * @author ThirupathiReddy V
 *
 */
// @Configuration
// @ComponentScan("com.tvajjala")
// @EnableAutoConfiguration
@PropertySource(ignoreResourceNotFound = true, value = { "classpath:application.properties", "file:application.properties" })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
