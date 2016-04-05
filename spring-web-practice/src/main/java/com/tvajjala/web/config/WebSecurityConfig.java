package com.tvajjala.web.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import com.hazelcast.core.HazelcastInstance;
import com.tvajjala.web.auth.provider.HibernateAuthenticationProvider;
import com.tvajjala.web.service.AccessControlService;
import com.tvajjala.web.service.AuthenticationService;

/**
 * the @EnableWebMvcSecurity annotation configures a Spring MVC argument resolver <br>
 * so that handler methods can receive the authenticated user’s principal (or username)<br>
 * via @AuthenticationPrincipal-annotated parameters.<br>
 * It also configures a bean that automatically adds a hidden cross-site request forgery (CSRF) <br>
 * token field on forms using Spring’s form-binding tag library.
 *
 * https://www.safaribooksonline.com/blog/2013/10/08/secure-rest-services-with-spring-security/
 *
 * @author ThirupathiReddy V
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /** The logger */
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Bean(name = "springSecurityFilterChain")
    public FilterChainProxy springSecurityFilterChain() throws ServletException, Exception {

        final List<SecurityFilterChain> listOfFilterChains = new ArrayList<SecurityFilterChain>();
        // listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/login"), new NoSecurityFilter()));

        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/validatorUrl")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/swagger-resources")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/configuration/ui")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/configuration/security")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/v2/api-docs")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/swagger-ui.html")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/webjars/**")));
        // no filters
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/webjars/**")));// no filters
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/**"), securityContextPersistenceFilterWithASCFalse(),
                exceptionTranslationFilter(), filterSecurityInterceptor()));

        final FilterChainProxy filterChainProxy = new FilterChainProxy(listOfFilterChains);

        return filterChainProxy;
    }

    // don't add @Bean annotation for the below three definitions. spring boot create new mapping for that
    // which violates the security
    public SecurityContextPersistenceFilter securityContextPersistenceFilterWithASCFalse() {
        logger.info("securityContextPersistenceFilterWithASCFalse");
        // return new SecurityContextPersistenceFilter(new HttpSessionSecurityContextRepository());// this stores token in httpSession
        return new SecurityContextPersistenceFilter(statelessSecurityContextRepository());
    }

    @Bean
    public StatelessSecurityContextRepository statelessSecurityContextRepository() {
        return new StatelessSecurityContextRepository();
    }

    public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        final FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
        filterSecurityInterceptor.setAccessDecisionManager(genericAccessDecisionManager());
        filterSecurityInterceptor.setSecurityMetadataSource(secureResouceMetadataSource());
        return filterSecurityInterceptor;
    }

    @Bean
    public GenericAccessDecisionManager genericAccessDecisionManager() {
        return new GenericAccessDecisionManager();
    }

    /**
     * since this bean auto-wired with other beans we need to create spring bean
     *
     * @return SecureResouceMetadataSource
     */
    @Bean
    public SecureResouceMetadataSource secureResouceMetadataSource() {
        return new SecureResouceMetadataSource();
    }

    @Override
    @Bean
    // this be need to be there to avoid spring boot to configure its own default configuration
    public AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();
    }

    public ExceptionTranslationFilter exceptionTranslationFilter() {
        final ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(new RestAuthenticationEntryPoint());
        exceptionTranslationFilter.setAccessDeniedHandler(new AuthorizationFailHandler());
        return exceptionTranslationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public HibernateAuthenticationProvider authenticationProvider() {
        return new HibernateAuthenticationProvider();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        System.out.println("http Security configuration");// suppress default configuration
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

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new LoginServlet(), "/login");
    }

}

class NoSecurityFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.err.println("  no security applied  ");
    }

}

class AuthorizationFailHandler implements AccessDeniedHandler {

    /**
     * Handle.
     *
     * @param request
     *            the request
     * @param response
     *            the response
     * @param accessDeniedException
     *            the access denied exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the servletException
     * @see org.springframework.security.web.access.AccessDeniedHandler#handle
     */
    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setContentType("application/json");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("{\"status\":\"403\",\"msg\":\"" + accessDeniedException.getMessage() + "\"}");
        }
    }

}

class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    /** The messageList. */
    static List<String> msgList = Arrays.asList("An Authentication object was not found in the SecurityContext",
            "Full authentication is required to access this resource");

    /**
     * @param request
     *            the request
     * @param response
     *            the response
     * @param exception
     *            the exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the ServletException
     */
    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException,
    ServletException {

        logger.warn("Authentication fails");

        final String msg = msgList.contains(exception.getMessage()) ? "invalid token entered or token expire " : exception.getMessage();
        response.setContentType("application/json");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("{\"status\":\"200\",\"msg\":\"" + msg + "\"}");
        }

    }

}

class SecureResouceMetadataSource implements FilterInvocationSecurityMetadataSource {

    /** The logger. */
    private static Logger logger = LoggerFactory.getLogger(SecureResouceMetadataSource.class);

    /** The access control service. */
    @Autowired
    private AccessControlService accessControlService;

    @Override
    public Collection<ConfigAttribute> getAttributes(final Object object) throws IllegalArgumentException {
        final FilterInvocation filterInvocation = (FilterInvocation) object;
        final String uri = filterInvocation.getRequestUrl();
        logger.info("Requested URI    :   " + uri);
        logger.info("accessControlService " + accessControlService);
        return accessControlService.getAllRolesByURI(uri);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return accessControlService.getAllRoles();
    }

    /**
     * true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(final Class<?> clazz) {
        return true;
    }

}

class GenericAccessDecisionManager implements AccessDecisionManager {

    /** The logger. */
    Logger logger = LoggerFactory.getLogger(AccessDecisionManager.class.getSimpleName());

    /**
     * Decide.
     *
     * @param authentication
     *            the authentication
     * @param object
     *            the object
     * @param configAttributes
     *            the configuration attributes
     * @throws AccessDeniedException
     *             the access denied exception
     * @throws InsufficientAuthenticationException
     *             the insufficient authentication exception
     */
    @Override
    public void decide(final Authentication authentication, final Object object, final Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        boolean allowAccess = false;

        for (final GrantedAuthority grantedAuthority : authentication.getAuthorities()) {

            for (final ConfigAttribute attribute : configAttributes) {
                allowAccess = attribute.getAttribute().equals(grantedAuthority.getAuthority());
                if (allowAccess) {
                    break;// this loop
                }
            }

        }

        if (!allowAccess) {
            throw new AccessDeniedException("Access is denied");
        }
    }

    /**
     * Supports.
     *
     * @param attribute
     *            the attribute
     * @return true, if successful
     */
    @Override
    public boolean supports(final ConfigAttribute attribute) {
        return true;
    }

    /**
     * Supports.
     *
     * @param clazz
     *            the clazz
     * @return true, if successful
     */
    @Override
    public boolean supports(final Class<?> clazz) {

        return true;
    }

}

/**
 * This core implementation for authentication check using tokens.<br>
 * It is replacement of {@link HttpSessionSecurityContextRepository} which store security context in session which we are no longer required
 *
 * @author ThirupathiReddy V
 */
class StatelessSecurityContextRepository implements SecurityContextRepository {

    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(StatelessSecurityContextRepository.class);

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        final String authToken = getToken(requestResponseHolder.getRequest());
        logger.debug("Reading security context token : " + authToken);

        if (authToken == null || hazelcastInstance.getMap("userTokenMap").get(authToken) == null) {
            logger.debug("Returning empty securityContext");
            return SecurityContextHolder.createEmptyContext();
        } else {
            logger.info("Returning valid securityContext");
            return (SecurityContext) hazelcastInstance.getMap("userTokenMap").get(authToken);
        }
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        final String authToken = getToken(request);

        if (authToken != null) {
            hazelcastInstance.getMap("userTokenMap").put(authToken, context, 30, TimeUnit.MINUTES);
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        final String authToken = getToken(request);
        return hazelcastInstance.getMap("userTokenMap").containsKey(authToken);
    }

    static String getToken(HttpServletRequest request) {

        final Enumeration<String> headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {

            System.err.println(headers.nextElement() + "    =   " + request.getHeader(headers.nextElement()));

        }

        String authToken = request.getHeader("x-auth-token");

        if (authToken == null) {
            authToken = request.getParameter("x-auth-token");
        }

        return authToken;
    }

}
