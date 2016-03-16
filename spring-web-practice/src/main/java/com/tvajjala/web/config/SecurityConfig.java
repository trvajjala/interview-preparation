package com.tvajjala.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.tvajjala.web.service.UserAuthenticationService;

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

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        // auth.userDetailsService(userAuthenticationService);
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("USER");

    }
}
