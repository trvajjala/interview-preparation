package pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Employee {

    private final List<Employee> employees = new ArrayList<Employee>();

    private String name;
    private double salary;

    public Manager() {

    }

    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        System.out.println("-------------------");
        System.out.println("Name : " + name);
        System.out.println("Salary : " + salary);

        for (final Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("-------------------");

        return "[name : " + name + " , salary : " + salary + "]";
    }
}
