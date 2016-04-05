package com.tvajjala.web.config;


/**
 * This is the entry point for the annotation based spring web application.<br>
 * Servlet 3.0 containers looks for WebApplicationInitializer and ServletContainerInitializer
 *
 * @author ThirupathiReddy V
 *
 */
public class WebXMLAnnotationConfiguration {
    // public class WebXMLAnnotationConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {
    //
    // /**
    // * registering the new servlet and filters as similar to web.xml <servlet> and <filter> tags
    // */
    // @Override
    // public void onStartup(ServletContext servletContext) throws ServletException {
    // super.onStartup(servletContext);
    //
    // // you can add more Servlets or filters here
    // final ServletRegistration.Dynamic registration = servletContext.addServlet("loginServlet", LoginServlet.class);
    // registration.setLoadOnStartup(1);
    // registration.addMapping("/login");
    //
    // System.err.println("  ############# SERVELET REGISTRATION SUCCESSFUL   #############   ");
    //
    // final FilterRegistration.Dynamic securityFilterChain = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy(
    // "springSecurityFilterChain"));
    //
    // securityFilterChain.addMappingForUrlPatterns(
    // EnumSet.of(DispatcherType.ERROR, DispatcherType.ASYNC, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.REQUEST), true,
    // new String[] { "/api/*" });// all /api/* end-points will be handled by this filter
    //
    // }
    //
    // @Override
    // public String getServletName() {
    // return "webTemplateServlet";
    // }
    //
    // /**
    // * This configuration is equals to the applicationContext.xml configuration
    // */
    // @Override
    // protected Class<?>[] getRootConfigClasses() {
    // return null;
    // // return new Class[] { ApplicationContextConfig.class, WebSecurityConfig.class, DatabaseConfig.class };// equals to ApplicationContextListener
    // // configuration
    // }
    //
    // /**
    // * this configuration is equals to the dispatch-servlet.xml
    // */
    // @Override
    // protected Class<?>[] getServletConfigClasses() {
    // return new Class[] { DispatchServletConfig.class };// dispatch-servlet.xml configuration
    // }
    //
    // /**
    // * dispatch-servlet mappings
    // */
    // @Override
    // protected String[] getServletMappings() {
    // return new String[] { "/api/*" };
    // }
    //
    // @Override
    // protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    // registration.setLoadOnStartup(1);
    // // registration.setInitParameter("spring.profiles.active", "dev");
    // registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads"));
    //
    // // final HttpConstraintElement forceHttpsConstraint = new HttpConstraintElement(ServletSecurity.TransportGuarantee.CONFIDENTIAL, new String[0]);
    // // final ServletSecurityElement securityElement = new ServletSecurityElement(forceHttpsConstraint);
    // // registration.setServletSecurity(securityElement);
    //
    // }
    //
    // // @Override
    // // protected Filter[] getServletFilters() {
    // // return new Filter[] {};
    // // }
    //
}
