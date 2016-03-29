package com.tvajjala.web.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tvajjala.rest.security.entry.AuthorizationFailHandler;
import com.tvajjala.rest.security.entry.RestAuthenticationEntryPoint;
import com.tvajjala.rest.security.repo.SecurityTokenRepository;
import com.tvajjala.security.auth.provider.HibernateAuthenticationProvider;
import com.tvajjala.security.managers.GenericAccessDecisionManager;
import com.tvajjala.security.resource.SecureResouceMetadataSource;
import com.tvajjala.web.service.AuthenticationService;

/**
 * the @EnableWebMvcSecurity annotation configures a Spring MVC argument resolver <br>
 * so that handler methods can receive the authenticated user’s principal (or username)<br>
 * via @AuthenticationPrincipal-annotated parameters.<br>
 * It also configures a bean that automatically adds a hidden cross-site request forgery (CSRF) <br>
 * token field on forms using Spring’s form-binding tag library.
 *
 * @author ThirupathiReddy V
 *
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    AuthenticationService authenticationService;

    @Bean(name = "securityFilterChain")
    public FilterChainProxy securityFilterChain() throws ServletException, Exception {

        final List<SecurityFilterChain> listOfFilterChains = new ArrayList<SecurityFilterChain>();
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/user/save*")));// no filters
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/**"), securityContextPersistenceFilterWithASCFalse(),
                exceptionTranslationFilter(), filterSecurityInterceptor()));

        return new FilterChainProxy(listOfFilterChains);
    }

    @Bean
    public SecurityContextPersistenceFilter securityContextPersistenceFilterWithASCFalse() {
        logger.info("securityContextPersistenceFilterWithASCFalse");
        // return new SecurityContextPersistenceFilter(new HttpSessionSecurityContextRepository());// this stores token in httpSession
        return new SecurityContextPersistenceFilter(securityTokenRepository());
    }

    @Bean
    public SecurityTokenRepository securityTokenRepository() {
        logger.info("securityTokenRepository");
        return new SecurityTokenRepository();
    }

    @Bean
    public ExceptionTranslationFilter exceptionTranslationFilter() {
        final ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(restAuthenticationEntryPoint());
        exceptionTranslationFilter.setAccessDeniedHandler(authorizationFailHandler());
        return exceptionTranslationFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public AuthorizationFailHandler authorizationFailHandler() {
        return new AuthorizationFailHandler();
    }

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        final FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAuthenticationManager(getAuthenticationManager());
        filterSecurityInterceptor.setAccessDecisionManager(genericAccessDecisionManager());
        filterSecurityInterceptor.setSecurityMetadataSource(secureResouceMetadataSource());
        return filterSecurityInterceptor;
    }

    @Bean
    public GenericAccessDecisionManager genericAccessDecisionManager() {
        return new GenericAccessDecisionManager();
    }

    @Bean
    public SecureResouceMetadataSource secureResouceMetadataSource() {
        return new SecureResouceMetadataSource();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        final AuthenticationManager authenticationManager = authenticationManager();
        return authenticationManager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        System.out.println("http Security configuration");// suppress default configuration
    }

    @Bean
    public HibernateAuthenticationProvider authenticationProvider() {
        return new HibernateAuthenticationProvider();
    }

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder();
    }

    @Bean
    public ReflectionSaltSource saltSource() {
        final ReflectionSaltSource reflectionSaltSource = new ReflectionSaltSource();
        reflectionSaltSource.setUserPropertyToUse("username");
        return reflectionSaltSource;
    }

}
