package com.tvajjala.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import com.tvajjala.security.auth.filter.JSONPayloadAuthenticationFilter;
import com.tvajjala.security.auth.provider.JPAAuthenticationProvider;
import com.tvajjala.web.service.AccessControlService;

@EnableWebSecurity(debug = true)
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean()
    FilterRegistrationBean FilterRegistrationBean() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("springSecurityFilterChain"));
        filterRegistrationBean.setName("springSecurityFilterChain");
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }

    // https://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html
    @Bean(name = "springSecurityFilterChain")
    public FilterChainProxy springSecurityFilterChain() throws ServletException, Exception {

        final List<SecurityFilterChain> listOfFilterChains = new ArrayList<SecurityFilterChain>();

        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/cors")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/dump")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/validatorUrl")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/swagger-resources")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/configuration/ui")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/configuration/security")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/v2/api-docs")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/swagger-ui.html")));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/webjars/**")));
        // no filters
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/webjars/**")));// no filters
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/*"), securityContextPersistenceFilterWithASCFalse(),
                usernamePasswordAuthenticationFilter(), sessionManagementFilter(), exceptionTranslationFilter(), filterSecurityInterceptor()));

        final FilterChainProxy filterChainProxy = new FilterChainProxy(listOfFilterChains);

        return filterChainProxy;
    }

    // allow session creation false
    public SecurityContextPersistenceFilter securityContextPersistenceFilterWithASCFalse() {
        // return new SecurityContextPersistenceFilter(new HttpSessionSecurityContextRepository());// this stores token in httpSession
        return new SecurityContextPersistenceFilter(statelessSecurityContextRepository());
    }

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public JSONPayloadAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        // final UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        final JSONPayloadAuthenticationFilter usernamePasswordAuthenticationFilter = new JSONPayloadAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        usernamePasswordAuthenticationFilter.setJacksonObjectMapper(jacksonObjectMapper);
        usernamePasswordAuthenticationFilter.setHazelcastInstance(hazelcastInstance);
        return usernamePasswordAuthenticationFilter;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    public AnonymousAuthenticationFilter anonymousAuthenticationFilter() {
        final AnonymousAuthenticationFilter anonymousAuthenticationFilter = new AnonymousAuthenticationFilter("tvajjala");
        return anonymousAuthenticationFilter;
    }

    public SessionManagementFilter sessionManagementFilter() {
        final SessionManagementFilter sessionManagementFilter = new SessionManagementFilter(statelessSecurityContextRepository());
        return sessionManagementFilter;
    }

    public ExceptionTranslationFilter exceptionTranslationFilter() {
        final ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(new RestAuthenticationEntryPoint());
        exceptionTranslationFilter.setAccessDeniedHandler(new AuthorizationFailHandler());
        return exceptionTranslationFilter;
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
        return new SecureResouceMetadataSource();// gives allowed roles
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true);// This has no effect since we overridden the springSecurityFilterChain bean definition
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());// custom authentication provider
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new JPAAuthenticationProvider();
    }

    @Bean
    public StatelessSecurityContextRepository statelessSecurityContextRepository() {
        return new StatelessSecurityContextRepository();
    }

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder();// argument can put 256 for SHA-256
    }

    @Bean
    public ReflectionSaltSource saltSource() {

        final ReflectionSaltSource reflectionSaltSource = new ReflectionSaltSource();
        reflectionSaltSource.setUserPropertyToUse("username");

        return reflectionSaltSource;
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

        if (authToken == null || hazelcastInstance.getMap("userTokenMap").get(authToken) == null) {
            logger.info("Returning empty securityContext");
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
            logger.info("Saving authentication token in the cache " + authToken + "  with securityContext = " + context);
            hazelcastInstance.getMap("userTokenMap").put(authToken, context, 30, TimeUnit.MINUTES);
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        final String authToken = getToken(request);

        return authToken != null && hazelcastInstance.getMap("userTokenMap").containsKey(authToken);
    }

    static String getToken(HttpServletRequest request) {

        String authToken = request.getHeader("x-auth-token");

        if (authToken == null) {
            authToken = request.getParameter("x-auth-token");
        } else {
            logger.info("Received authentication token in header / or query parameter with key x-auth-token = " + authToken);
        }

        return authToken;
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

        final String msg = msgList.contains(exception.getMessage()) ? "Invalid token entered or token expire " : exception.getMessage();
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
