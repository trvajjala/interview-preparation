package com.tvajjala.inheritance.perclass;

import javax.persistence.Entity;

@Entity
public class TwoWheeler extends Vehicle {

    private String steeringHandle;

    public void setSteeringHandle(String steeringHandle) {
        this.steeringHandle = steeringHandle;
    }

    public String getSteeringHandle() {
        return steeringHandle;
    }

}
