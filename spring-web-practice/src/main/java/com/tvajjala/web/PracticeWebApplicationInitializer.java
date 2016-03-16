package com.tvajjala.web;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.tvajjala.servlet.CustomServlet;
import com.tvajjala.web.config.RootConfig;
import com.tvajjala.web.config.WebConfig;

public class PracticeWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        final Dynamic registration = servletContext.addServlet("customServlet", CustomServlet.class);
        registration.addMapping("/login");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class[] { RootConfig.class };// equals to ApplicationContextListener configuration
        // equals to applicationContext.xml
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };// dispatch-servlet.xml configuration
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setLoadOnStartup(1);
        // registration.setInitParameter("spring.profiles.active", "dev");
        //
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads"));
    }

    // @Override
    // protected Filter[] getServletFilters() {
    // return new Filter[] {};
    // }

}
