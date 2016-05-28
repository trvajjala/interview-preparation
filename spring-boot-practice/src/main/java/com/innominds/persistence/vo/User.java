package com.innominds.persistence.vo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8825646974241476909L;

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    /** The account non expired. */
    private boolean accountNonExpired;

    /** The credentials non expired. */
    private boolean credentialsNonExpired;

    /** The enabled. */
    private boolean enabled;

    /** The account non locked. */
    private boolean accountNonLocked;

    /** to check weather user registered through SAAS */
    private boolean socialUser;

    /** The authorities. */
    private Set<Authority> authorities = new HashSet<Authority>(0);

    public User() {

    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isSocialUser() {
        return socialUser;
    }

    public void setSocialUser(boolean socialUser) {
        this.socialUser = socialUser;
    }

    @Override
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [");
        if (username != null) {
            builder.append("username=");
            builder.append(username);
        }
        builder.append("]");
        return builder.toString();
    }

}