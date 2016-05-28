package com.innominds.persistence.vo;

import java.io.Serializable;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

import com.innominds.persistence.constants.ApplicationRole;

/**
 * The Class Authority.
 */
public class Authority implements Serializable, GrantedAuthority, ConfigAttribute {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2209238250163252206L;

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /** The authority. */
    private String authority;

    /** The title */
    private String title;

    /**
     * Instantiates a new authority.
     */
    public Authority() {

    }

    /**
     * Constructor
     *
     * @param applicationRole
     */
    public Authority(ApplicationRole applicationRole) {
        authority = applicationRole.name();
        title = applicationRole.name();
    }

    /**
     * Instantiates a new authority.
     *
     * @param authority
     *            the authority
     * @param title
     *            the title
     */
    public Authority(final String authority, final String title) {

        this.authority = authority;
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Authority)) {
            return false;
        }
        final Authority other = (Authority) obj;
        if (authority == null) {
            if (other.authority != null) {
                return false;
            }
        } else if (!authority.equals(other.authority)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public String getAttribute() {

        return authority;
    }

    @Override
    public String getAuthority() {

        return authority;
    }

    /**
     * This method returns the value held within the field title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (authority == null ? 0 : authority.hashCode());
        result = prime * result + (title == null ? 0 : title.hashCode());
        return result;
    }

    /**
     * Sets the authority.
     *
     * @param authority
     *            the new authority
     */
    public void setAuthority(final String authority) {

        this.authority = authority;
    }

    /**
     * This method sets the specified value (title) to the field (title).
     *
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Authority [authority=" + authority + ", title=" + title + "]";
    }

}
