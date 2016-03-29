package com.tvajjala.bean;

import java.io.Serializable;

public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4077424793944237136L;

    private Integer id;
    private String name;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // other setter and getter

    public User() {
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [");
        if (id != null) {
            builder.append("id=");
            builder.append(id);
            builder.append(", ");
        }
        if (name != null) {
            builder.append("name=");
            builder.append(name);
            builder.append(", ");
        }
        if (address != null) {
            builder.append("address=");
            builder.append(address);
        }
        builder.append("]");
        return builder.toString();
    }

}