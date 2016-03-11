package com.trvajjala.flyweight;

/**
 * intrinsic shape is : share extrinsic shape is : color
 *
 * @author ThirupathiReddy V
 *
 */
public interface Shape {

    void draw();

    void setColor(String color);
}
