package com.trvajjala.observer;

/**
 * publisher and subscriber = observer when we need to notify one object Behaviour by the other clients. observer pattern is useful.
 * 
 * @author ThirupathiReddy V
 *
 */
public class ObserverPatternDemo {

    public static void main(String[] args) {

        final ColorfulSubject colorfulSubject = new ColorfulSubject();

        final ObserverOne observerOne = new ObserverOne();
        final ObserverTwo observerTwo = new ObserverTwo();

        colorfulSubject.addObserver(observerOne);
        colorfulSubject.addObserver(observerTwo);
        colorfulSubject.setColor("RED");

        System.out.println("---------------------------------");

        colorfulSubject.removeObserver(observerTwo);
        colorfulSubject.setColor("YELLOW");
    }
}
