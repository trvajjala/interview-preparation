package com.tvajjala.composite;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class DepartmentKey implements Serializable {

    @Basic
    private final Long id;

    @Basic
    private final String code;

    public DepartmentKey(Long id, String code) {
        super();
        this.id = id;
        this.code = code;
    }

}
