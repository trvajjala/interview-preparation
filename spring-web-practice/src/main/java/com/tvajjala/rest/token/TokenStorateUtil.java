package com.tvajjala.rest.token;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hazelcast.core.IMap;

/**
 * The Interface TokenStorateUtil.
 */
public class TokenStorateUtil {

    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(TokenStorateUtil.class);

    /** The idle time. */
    public static final int IDLE_TIME = 120;

    static WebApplicationContext getContext(final HttpServletRequest request) {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
    }

    static IMap<String, SecurityContext> getCacheMap(final HttpServletRequest request) {

        @SuppressWarnings("unchecked")
        final IMap<String, SecurityContext> cacheMap = (IMap<String, SecurityContext>) getContext(request).getBean("userTokenMap");
        return cacheMap;
    }

    public static SecurityContext getSecurityContext(final HttpServletRequest request) {
        final String authToken = request.getHeader("x-auth-token");
        logger.info("Reading security context for token : " + authToken);

        if (authToken == null) {
            logger.warn("Returning empty securityContext");
            return SecurityContextHolder.createEmptyContext();
        }

        final SecurityContext securityContext = getCacheMap(request).get(authToken);
        logger.info("Reading securityContext from cache " + securityContext);

        if (securityContext == null) {
            logger.warn("Invalid  securityContext  : " + securityContext);
            logger.info("Returning empty securityContext  ");
            return SecurityContextHolder.createEmptyContext();
        }

        return securityContext;
    }

    /**
     * add securityContext into cache instead of HttpSession. to enable state less behaviour to application
     *
     * @param request
     * @param context
     */
    public static void updateToken(final HttpServletRequest request, SecurityContext context) {

        final String authToken = request.getHeader("x-auth-token");

        if (authToken == null) {
            return;
        }

        if (getCacheMap(request).containsKey(authToken)) {
            logger.info("Updating cache  [ " + authToken + "=" + context + " ] ");
            getCacheMap(request).put(authToken, context, IDLE_TIME, TimeUnit.SECONDS);
        }
    }

    public static void saveToken(final HttpServletRequest request, SecurityContext context, String token) {

        if (token == null) {
            return;
        }
        logger.info("Updating cache  [ " + token + "=" + context + " ] ");
        getCacheMap(request).put(token, context, IDLE_TIME, TimeUnit.SECONDS);
    }

}
