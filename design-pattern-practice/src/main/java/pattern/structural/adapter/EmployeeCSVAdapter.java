package pattern.structural.adapter;

public class EmployeeCSVAdapter implements Employee {

    EmployeeCSV employeeCSV;

    public  EmployeeCSVAdapter(EmployeeCSV employeeCSV) {
	this.employeeCSV = employeeCSV;
    }

    @Override
    public String getFirstName() {
	return employeeCSV.getFname();
    }

    @Override
    public String getLastName() {
	return employeeCSV.getLname();
    }

    @Override
    public String getId() {
	return employeeCSV.getId();
    }

    @Override
    public String getEmail() {
	return employeeCSV.getMailAddress();
    }

}
