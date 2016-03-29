package com.tvajjala.security.resource;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.tvajjala.web.service.AccessControlService;

/**
 * The Class SecureResouceMetadataSource.
 *
 * @author ThirupathiReddy V
 */

public class SecureResouceMetadataSource implements FilterInvocationSecurityMetadataSource {

    /** The logger. */
    Logger logger = LoggerFactory.getLogger(SecureResouceMetadataSource.class);

    /** The access control service. */
    @Autowired
    AccessControlService accessControlService;

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
