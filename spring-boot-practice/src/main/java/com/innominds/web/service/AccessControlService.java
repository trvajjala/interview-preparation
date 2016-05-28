package com.innominds.web.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;

/**
 * The Interface AccessControlService.
 */
public interface AccessControlService {

    /**
     * @param endpoint
     * @param regex
     * @param list
     */
    void addActivityForRoles( String endpoint, boolean regex, List<String> list );

    /**
     * Adds the activity for roles.
     *
     * @param endpoint
     *            the endpoint
     * @param regex
     *            the regex
     * @param roleNames
     *            the role names
     */
    void addActivityForRoles( String endpoint, boolean regex, String... roleNames );

    /**
     * Gets the all roles.
     *
     * @return the all roles
     */
    Collection<ConfigAttribute> getAllRoles();

    /**
     * Gets the all roles by uri.
     *
     * @param uri
     *            the uri
     * @return the all roles by uri
     */
    Collection<ConfigAttribute> getAllRolesByURI( String uri );
}
