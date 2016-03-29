package com.tvajjala.named.query;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Positional parameters (using ? ) are deprecated. <br>
 * Named parameters (:someName) are user friendly and widely used
 *
 * @author ThirupathiReddy V
 *
 */
@Entity
@NamedQuery(name = "Employee.byId", query = "FROM Employee WHERE id=:id")
@NamedNativeQuery(name = "Employee.Named.byId", query = "SELECT * FROM Employee WHERE id=:id", resultClass = Employee.class)
@Cacheable(value = true)
@Cache(usage = CacheConcurrencyStrategy.NONE)
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String name;

    @Basic
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}
