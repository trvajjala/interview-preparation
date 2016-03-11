package com.trvajjala.observer;

public class ObserverOne implements Observer {

    @Override
    public void updateColor(String color) {
        System.out.println("ObserverOne notified about the color :" + color);
    }
}
