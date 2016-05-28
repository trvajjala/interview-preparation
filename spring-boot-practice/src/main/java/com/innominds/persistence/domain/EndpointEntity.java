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

@Entity
public class EndpointEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4699479233392145577L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /** The endpoint. */
    @Column(name = "resource_url", nullable = false)
    private String endpoint;

    /** The regex. */
    @Column(name = "is_regex")
    @Type(type = "yes_no")
    private Boolean regex;

    /** The authorities. */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "endpoint_permissions", joinColumns = { @JoinColumn(name = "permission_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "authority_id", nullable = false, updatable = false) })
    private Set<AuthorityEntity> authorities = new HashSet<AuthorityEntity>(0);

    /**
     * @return set of authorities
     */
    public Set<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    /**
     * @param authorities
     */
    public void setAuthorities(final Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    /**
     * Gets the regex.
     *
     * @return the regex
     */
    public Boolean getRegex() {
        return regex;
    }

    /**
     * @param regex
     */
    public void setRegex(final Boolean regex) {
        this.regex = regex;
    }

    /**
     * @return endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint
     */
    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (endpoint == null ? 0 : endpoint.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (regex == null ? 0 : regex.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EndpointEntity other = (EndpointEntity) obj;
        if (endpoint == null) {
            if (other.endpoint != null) {
                return false;
            }
        } else if (!endpoint.equals(other.endpoint)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (regex == null) {
            if (other.regex != null) {
                return false;
            }
        } else if (!regex.equals(other.regex)) {
            return false;
        }
        return true;
    }

}
