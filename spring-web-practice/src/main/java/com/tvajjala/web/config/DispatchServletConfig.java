package com.tvajjala.web.config;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * this is equivalent java configuration for dispatch-servlet.xml
 *
 * @author ThirupathiReddy V
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.tvajjala")
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties(DispatchServletConfig.CustomProperties.class)
// -> if u define each bean in this class scanning is not requires
public class DispatchServletConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);// ?
        return resolver;
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() {

        return new ObjectMapper();
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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);// /hello/1-2 and /hello/1-2.1 are not same
        configurer.setUseTrailingSlashMatch(true); // /hello/ and /hello are same

    }

    @Bean
    public HandlerInterceptor performanceInterceptor() {
        final PerformanceInterceptor interceptor = new PerformanceInterceptor();
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(performanceInterceptor());
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        System.err.println("LocaleChange interceptor");
        return new LocaleChangeInterceptor();
    }

    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("fr"));
        return localeResolver;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        System.err.println("extendMessageConverters " + converters);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        // converters.add(new MappingJackson2HttpMessageConverter());
        // converters.add(new ByteArrayHttpMessageConverter());// by default these are all configured
        System.err.println("configureMessageConverters ");
    }

    /**
     * useful to render some static or large files like video etc
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        // registry.addResourceHandler("/static/**").addResourceLocations("classpath:/").setCachePeriod(10);
        // registry.addResourceHandler("**/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // registry.addResourceHandler("**/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    // http://www.programcreek.com/java-api-examples/index.php?api=org.springframework.boot.autoconfigure.condition.ConditionalOnBean
    // @Bean
    // public DelegatingFilterProxyRegistrationBean springSecurityFilterChain(SecurityProperties securityProperties) {
    // final DelegatingFilterProxyRegistrationBean registration = new DelegatingFilterProxyRegistrationBean("springSecurityFilterChain");
    // registration.setOrder(securityProperties.getFilterOrder());
    // registration.addUrlPatterns("/api/*");
    // return registration;
    // }

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {

                System.err.println("Customizing embeddedServlet container ");
                container.setSessionTimeout(1, TimeUnit.SECONDS);
                container.setPort(8084);// on which port embedded tomcat should run
                container.setContextPath("/spring-web-practice");// This is to make in sync with direct TOMCAT deployment and embedded server deployment
            }
        };
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        final TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        // tomcatFactory.setAddress(InetAddress.getLocalHost());// you can restrict localhost access
        tomcatFactory.setPort(8080);
        // ServletContainerInitializer

        final Connector connector = new Connector();
        connector.setPort(8443);
        connector.setSecure(true);
        connector.setScheme("https");
        connector.setProperty("SSLEnabled", "true");
        connector.setProperty("keystorePass", "spring");
        try {
            final ClassPathResource classPathResource = new ClassPathResource("keystore");
            System.err.println(classPathResource.getFile().getAbsolutePath());
            connector.setProperty("keystoreFile", classPathResource.getFile().getAbsolutePath());
        } catch (final Exception e) {
            System.err.println("Error while loading classpath resource " + e.getMessage());
        }

        tomcatFactory.addAdditionalTomcatConnectors(connector);
        return tomcatFactory;
    }

    @ConfigurationProperties(prefix = "tomcat.ssl")
    // all the properties prefixed with tomcat.ssl are binded to this class
    static class CustomProperties {

        private int port;

        public void setPort(int port) {
            this.port = port;
        }

        public int getPort() {
            return port;
        }
    }

    /**
     * sample usage of Interceptors in annotation based configuration
     *
     * @author ThirupathiReddy V
     *
     */
    class PerformanceInterceptor extends HandlerInterceptorAdapter {

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            super.afterCompletion(request, response, handler, ex);
        }

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            return super.preHandle(request, response, handler);
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            super.postHandle(request, response, handler, modelAndView);
        }
    }

}
