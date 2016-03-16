package com.tvajjala.web.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.tvajjala.web.interceptor.PerformanceInterceptor;

/**
 *
 * @author ThirupathiReddy V
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.tvajjala")
@Import(DatabaseConfig.class)
// -> if u define each bean in this class scanning is not requires
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);// ?
        return resolver;
    }

    /**
     * this new WebConfig class extends WebMvcConfigurerAdapter and overrides its<br>
     * configureDefaultServletHandling() method. By calling enable() on the given<br>
     * DefaultServletHandlerConfigurer, you’re asking DispatcherServlet to <br>
     * forward requests for static resources to the servlet container’s default servlet<br>
     * and not to try to handle them itself.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();// enable static content handling
    }

    @Bean
    public HandlerInterceptor performanceInterceptor() {
        PerformanceInterceptor interceptor;
        interceptor = new PerformanceInterceptor();
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(performanceInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("fr"));
        return localeResolver;
    }
}
