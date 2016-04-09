package com.tvajjala;

import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

/**
 * If want to deploy boot application into external tomcat this must extend SpringBootServletInitializer. servlet 3.0 container looks for instance of this
 * class.
 *
 * @author ThirupathiReddy V
 *
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class })
public class BootApplication extends SpringBootServletInitializer {

    @Override
    public WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
        return super.createRootApplicationContext(servletContext);
    }

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootApplication.class);
    }

    public static void main(String[] args) {

        final SpringApplication application = new SpringApplication(BootApplication.class);
        final Properties properties = new Properties();
        properties.put("server.servletPath", "/api/*");// dispatch Servlet path can be set here
        application.setBannerMode(Mode.OFF);
        application.setDefaultProperties(properties);
        application.run();
    }
}
