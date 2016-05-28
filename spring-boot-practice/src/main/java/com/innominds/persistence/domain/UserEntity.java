package com.innominds.persistence.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class UserEntity implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8825646974241476909L;

    /** The username. */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /** The password. */
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    /** The authorities. */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_authorities", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "authority_id", nullable = false, updatable = false) })
    private Set<AuthorityEntity> authorities = new HashSet<AuthorityEntity>(0);

    /** The account non expired. */
    @Column(name = "accountNonExpired")
    @Type(type = "yes_no")
    private boolean accountNonExpired;

    /** The credentials non expired. */
    @Column(name = "credentialsNonExpired")
    @Type(type = "yes_no")
    private boolean credentialsNonExpired;

    /** The enabled. */
    @Column(name = "enabled")
    @Type(type = "yes_no")
    private boolean enabled;

    /** The account non locked. */
    @Column(name = "accountNonLocked")
    @Type(type = "yes_no")
    private boolean accountNonLocked;

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
    public Set<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
