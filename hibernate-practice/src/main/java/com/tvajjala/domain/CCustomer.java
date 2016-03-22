package com.tvajjala.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class CCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @ElementCollection
    @JoinTable(name = "CADDRESS", joinColumns = @JoinColumn(name = "STUDENT_ID"))
    @GenericGenerator(name = "my_gen", strategy = "increment")
    @CollectionId(generator = "my_gen", columns = { @Column(name = "ADDRESS_ID") }, type = @Type(type = "int"))
    private Collection<CAddress> addressList = new ArrayList<CAddress>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<CAddress> getAddressList() {
        return addressList;
    }

    public void setAddressList(Collection<CAddress> addressList) {
        this.addressList = addressList;
    }

}
