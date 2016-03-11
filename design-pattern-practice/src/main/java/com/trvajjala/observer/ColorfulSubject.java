package com.trvajjala.observer;

import java.util.ArrayList;
import java.util.List;

public class ColorfulSubject implements Subject {

    List<Observer> observers;

    private String color;

    public void setColor(String color) {
        this.color = color;
        notifyObservers();
    }

    public String getColor() {
        return color;
    }

    public ColorfulSubject() {
        observers = new ArrayList<Observer>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (final Observer observer : observers) {
            observer.updateColor(color);
        }

    }

}
