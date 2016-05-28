package com.innominds.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (authority == null ? 0 : authority.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (title == null ? 0 : title.hashCode());
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
        final AuthorityEntity other = (AuthorityEntity) obj;
        if (authority == null) {
            if (other.authority != null) {
                return false;
            }
        } else if (!authority.equals(other.authority)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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

}
