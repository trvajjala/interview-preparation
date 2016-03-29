package com.tvajjala.security.managers;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * This will decide user to allow specific resource to view
 * </p>
 * .
 *
 * @author ThirupathiReddy V
 * @since 1.0
 */

public class GenericAccessDecisionManager implements AccessDecisionManager {

    /** The logger. */
    Logger logger = LoggerFactory.getLogger( AccessDecisionManager.class.getSimpleName() );

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
    public void decide( final Authentication authentication, final Object object, final Collection<ConfigAttribute> configAttributes )
            throws AccessDeniedException, InsufficientAuthenticationException {

        boolean allowAccess = false;

        for ( final GrantedAuthority grantedAuthority : authentication.getAuthorities() ) {

            for ( final ConfigAttribute attribute : configAttributes ) {
                allowAccess = attribute.getAttribute().equals( grantedAuthority.getAuthority() );
                if ( allowAccess ) {
                    break;// this loop
                }
            }

        }

        if ( !allowAccess ) {
            throw new AccessDeniedException( "Access is denied" );
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
    public boolean supports( final ConfigAttribute attribute ) {
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
    public boolean supports( final Class<?> clazz ) {

        return true;
    }

}
