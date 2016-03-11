package com.trvajjala.observer;

public class ObserverTwo implements Observer {

    @Override
    public void updateColor(String color) {
        System.out.println("ObserverTwo notified about color : " + color);
    }
}
