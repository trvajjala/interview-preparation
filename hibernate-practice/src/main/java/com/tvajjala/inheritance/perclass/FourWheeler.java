package com.tvajjala.inheritance.perclass;

import javax.persistence.Entity;

@Entity
public class FourWheeler extends Vehicle {

    private String steeringWheel;

    public void setSteeringWheel(String steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    public String getSteeringWheel() {
        return steeringWheel;
    }
}
