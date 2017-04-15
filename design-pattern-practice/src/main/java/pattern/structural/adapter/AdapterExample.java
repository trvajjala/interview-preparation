package pattern.structural.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Useful client oriented. <br>
 * Generally introduced to integrate a new client with legacy components
 *
 * @author ThirupathiReddy V
 *
 */
public class AdapterExample {

    public static void main(String[] args) {

	final List<Employee> employeeList = new ArrayList<>();

	employeeList.add(new EmployeeDB("1", "Bhanu", "Vajjala", "bhanu@gmail.com"));

	employeeList.add(new EmployeeAdapter(new EmployeeLdap("3883", "Karthi", "Vajjala", "karthi@gmail.com")));


	employeeList.add(new EmployeeCSVAdapter(new EmployeeCSV("3","Thiru","V","thiru@gmail.com")));


	System.out.println(employeeList);

    }

}
