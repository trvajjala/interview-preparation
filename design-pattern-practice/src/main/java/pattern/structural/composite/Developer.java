package pattern.structural.composite;

public class Developer implements Employee {

    private String name;
    private double salary;

    public Developer() {

    }

    public Developer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void addEmployee(Employee employee) {

        throw new UnsupportedOperationException();

    }

    @Override
    public void removeEmployee(Employee employee) {

        throw new UnsupportedOperationException();
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
        System.out.println("-------------------");

        return "[name : " + name + " , salary : " + salary + "]";
    }

}
