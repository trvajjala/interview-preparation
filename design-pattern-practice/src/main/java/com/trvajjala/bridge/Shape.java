package com.trvajjala.bridge;

public abstract class Shape {

    protected ColorImplementor colorImplementor;

    protected void setColor(ColorImplementor colorImplementor) {
	this.colorImplementor = colorImplementor;
    }

    protected ColorImplementor getColorImplementor() {
	return colorImplementor;
    }

    public abstract void colorIt();

    public abstract void draw();

}
