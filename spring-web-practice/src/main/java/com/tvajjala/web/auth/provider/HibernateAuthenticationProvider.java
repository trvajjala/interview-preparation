package com.tvajjala.web.auth.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.tvajjala.web.service.AuthenticationService;

/**
 * The Class HibernateAuthenticationProvider.
 *
 * @author ThirupathiReddy V
 */

public class HibernateAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    /** The bad credential exception. */
    private final String badCredentialExcep = " Invalid Username or Password ";

    /** The authentication service exception. */
    private final String authenticationServiceExcep = " Enter Username and Password ";

    /** The password encoder. */
    @Autowired
    private BasePasswordEncoder passwordEncoder;

    /** The salt source. */
    @Autowired
    private SaltSource saltSource;

    /** The authentication service. */
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Additional authentication checks.
     *
     * @param userDetails
     *            the user details
     * @param authentication
     *            the authentication
     * @throws AuthenticationException
     *             the authentication exception
     */
    @Override
    @SuppressWarnings("all")
    protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        Object salt = null;

        if (saltSource != null) {
            salt = saltSource.getSalt(userDetails);
        }

        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException(authenticationServiceExcep);
        }

        final String presentedPassword = authentication.getCredentials().toString();

        if (salt == null) {
            // if salt is null, presume password is plain text
            if (!presentedPassword.equalsIgnoreCase(userDetails.getPassword())) {
                throw new BadCredentialsException(badCredentialExcep);
            }
        } else {
            if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
                throw new BadCredentialsException(badCredentialExcep);
            }
        }

        // getAuthenticationService().updateLastLoginTime(userDetails.getUsername());
    }

    /**
     * Do after properties set.
     *
     * @throws Exception
     *             the exception
     */
    @Override
    protected void doAfterPropertiesSet() throws Exception {

        Assert.notNull(authenticationService, "A UserDetailsService instance must be set");
    }

    /**
     * Gets the authentication service.
     *
     * @return the authentication service
     */
    public AuthenticationService getAuthenticationService() {

        return authenticationService;
    }

    /**
     * Gets the password encoder.
     *
     * @return the password encoder
     */
    protected BasePasswordEncoder getPasswordEncoder() {

        return passwordEncoder;
    }

    /**
     * Gets the salt source.
     *
     * @return the salt source
     */
    protected SaltSource getSaltSource() {

        return saltSource;
    }

    /**
     * Retrieve user.
     *
     * @param username
     *            the username
     * @param authentication
     *            the authentication
     * @return the user details
     * @throws AuthenticationException
     *             the authentication exception
     */
    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        UserDetails loadedUser;

        if (username == null || username.trim().length() < 1) {
            throw new AuthenticationServiceException(authenticationServiceExcep);
        }

        try {

            System.out.println(authenticationService);
            loadedUser = authenticationService.loadUserByUsername(username);

        } catch (final DataAccessException repositoryProblem) {

            throw new AuthenticationServiceException(authenticationServiceExcep);
        }

        if (loadedUser == null) {
            throw new AuthenticationServiceException(badCredentialExcep);
        }
        return loadedUser;
    }

    /**
     * Sets the authentication service.
     *
     * @param authenticationService
     *            the new authentication service
     */
    public void setAuthenticationService(final AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;
    }

    /**
     * Sets the password encoder.
     *
     * @param passwordEncoder
     *            the new password encoder
     */
    public void setPasswordEncoder(final BasePasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Sets the salt source.
     *
     * @param saltSource
     *            the new salt source
     */
    public void setSaltSource(final SaltSource saltSource) {

        this.saltSource = saltSource;
    }

}
