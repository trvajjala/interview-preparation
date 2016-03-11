package com.trvajjala.composite;

/**
 * This pattern is used when group of objects treated as the single object.<br>
 * it can be used to create tree like structure.
 *
 *       Manager 
 *       /     \
 *      /       \
 *     /         \
 *   Developer   Manager 
 *                  /
 *                 /
 *                /  
 *             Developer 
 *
 * @author ThirupathiReddy V
 *
 */
public class CompositeExample {

    public static void main(String[] args) {

        final Employee manager = new Manager("Thiru", 1200.00);
        final Employee developer = new Developer("Bhanu", 43.34);
        final Employee developer2 = new Developer("Karthi", 344.22);
        manager.addEmployee(developer);
        manager.addEmployee(developer2);

        System.out.println(manager);

    }
}
