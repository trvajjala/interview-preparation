package com.tvajjala.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bike")
public class TwoWheeler extends Vehicle {

    private String steeringHandler;

    public String getSteeringHandler() {
        return steeringHandler;
    }

    public void setSteeringHandler(String steeringHandler) {
        this.steeringHandler = steeringHandler;
    }

}
