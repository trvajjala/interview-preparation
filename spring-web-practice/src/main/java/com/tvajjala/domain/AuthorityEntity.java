package com.tvajjala.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * Authority entity .please keep this entity independent. don't add any reference into it.
 * </p>
 *
 * @author ThirupathiReddy V
 *
 */
@Entity
public class AuthorityEntity implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 418847605346388857L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /**
     * Constructor
     */
    public AuthorityEntity() {

    }

    @Column(name = "authority", unique = true, nullable = false)
    private String authority;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    /**
     * This method returns the value held within the field authority.
     *
     * @return the authority
     */
    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * This method sets the specified value (authority) to the field (authority).
     *
     * @param authority
     *            the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * This method returns the value held within the field title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
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
        return "AuthorityEntity [authority=" + authority + ", title=" + title + "]";
    }

}
