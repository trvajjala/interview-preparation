package com.tvajjala.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.tvajjala.servlet.CustomServlet;
import com.tvajjala.web.config.RootConfig;
import com.tvajjala.web.config.SecurityConfig;
import com.tvajjala.web.config.WebConfig;

public class PracticeWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * registering the new servlet and filters as similar to web.xml <servlet> and <filter> tags
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        // you can add more Servlets or filters here
        final ServletRegistration.Dynamic registration = servletContext.addServlet("customServlet", CustomServlet.class);
        registration.addMapping("/custom");

        final FilterRegistration.Dynamic securityFilterChain = servletContext
                .addFilter("securityFilterChain", new DelegatingFilterProxy("securityFilterChain"));

        securityFilterChain.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.ERROR, DispatcherType.ASYNC, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.REQUEST), true,
                new String[] { "/api/*" });// all /api/* end-points will be handled by this filter

    }

    /**
     * This configuration is equals to the applicationContext.xml configuration
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class, SecurityConfig.class };// equals to ApplicationContextListener configuration
    }

    /**
     * this configuration is equals to the dispatch-servlet.xml
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };// dispatch-servlet.xml configuration
    }

    /**
     * dispatch-servlet mappings
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/api/*", "/login" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setLoadOnStartup(1);
        // registration.setInitParameter("spring.profiles.active", "dev");
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads"));
    }

    // @Override
    // protected Filter[] getServletFilters() {
    // return new Filter[] {};
    // }

}
