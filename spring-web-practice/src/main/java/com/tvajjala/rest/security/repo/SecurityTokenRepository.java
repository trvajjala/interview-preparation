package com.tvajjala.rest.security.repo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.tvajjala.rest.token.TokenStorateUtil;

/**
 * This core implementation for authentication check using tokens.<br>
 * It is replacement of {@link HttpSessionSecurityContextRepository} which store security context in session which we are no longer required
 *
 * @author ThirupathiReddy V
 */
public class SecurityTokenRepository implements SecurityContextRepository {

    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(SecurityTokenRepository.class);

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        logger.info("LoadContext  method with auth-token : " + requestResponseHolder.getRequest().getHeader("x-auth-token"));
        logger.info("Using  ContextHolderStrategy   : " + SecurityContextHolder.getContextHolderStrategy().getClass());
        return TokenStorateUtil.getSecurityContext(requestResponseHolder.getRequest());

    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        logger.info("SaveContext  " + context);
        TokenStorateUtil.updateToken(request, context);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        logger.info("ContainsContext    " + request);
        return TokenStorateUtil.getSecurityContext(request) != null;
    }

}
