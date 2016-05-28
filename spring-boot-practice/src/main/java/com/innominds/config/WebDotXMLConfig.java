package com.innominds.config;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class executes only if you deploy on external TOMCAT container. <br>
 * If you are running spring boot application property (server.servletPath=/api/*) must set as a property on the class argument.
 *
 * @author ThirupathiReddy V
 *
 */
public class WebDotXMLConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String getServletName() {
        return "customizedDispatchServlet";
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/api/*" };
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setLoadOnStartup(1);

    }

}
