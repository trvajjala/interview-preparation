package com.tvajjala.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * If you want to completely suppress the spring boot auto configuration(for webMVC) you need to use @EnableWebMvc <br>
 * and write class that extends WebMvcConfigurer define your own beans so that it will pickup these beans.
 *
 * @author ThirupathiReddy V
 *
 */
@EnableWebMvc
@Configuration
// some of the components(CORS) still need @Configuration annotation
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // TODO Auto-generated method stub
        super.extendMessageConverters(converters);

        System.err.println("ExtendMessageConverters invoked");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        // https://en.wikipedia.org/wiki/Cross-origin_resource_sharing
        registry.addMapping("/*");// all the end-points now can be accisable from other domains
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/").addResourceLocations("classpath:/corsTest.html");
        registry.addResourceHandler("/cors/**").addResourceLocations("classpath:/");

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // TODO Auto-generated method stub
        super.configureDefaultServletHandling(configurer);
        configurer.enable("staticServlet");
    }

}
