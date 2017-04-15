package pattern.structural.adapter;

public class EmployeeDB implements Employee{

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    public EmployeeDB(){

    }

    public EmployeeDB(String id, String firstName, String lastName, String email) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
    }

    @Override
    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;

    }

    @Override
    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    @Override
    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    @Override
    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }










}
