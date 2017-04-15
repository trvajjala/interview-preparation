package pattern.structural.adapter;

public class EmployeeAdapter implements Employee {

    EmployeeLdap employeeLdap;

    public EmployeeAdapter(EmployeeLdap employeeLdap) {
	this.employeeLdap = employeeLdap;
    }


    @Override
    public String getFirstName() {
	return employeeLdap.getGivenName();
    }


    @Override
    public String getLastName() {
	return employeeLdap.getSurname();
    }



    @Override
    public String getId() {
	return employeeLdap.getCn();
    }


    @Override
    public String getEmail() {
	return employeeLdap.getMailAddress();
    }





    @Override
    public String toString() {
	return "EmployeeAdapter [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", email=" + getEmail() + "]";
    }


}
