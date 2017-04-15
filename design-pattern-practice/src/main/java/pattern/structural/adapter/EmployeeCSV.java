package pattern.structural.adapter;

public class EmployeeCSV {

    private String fname;

    private String lname;

    private String id;

    private String mailAddress;






    public EmployeeCSV(String fname, String lname, String id, String mailAddress) {
	super();
	this.fname = fname;
	this.lname = lname;
	this.id = id;
	this.mailAddress = mailAddress;
    }

    public String getFname() {
	return fname;
    }

    public void setFname(String fname) {
	this.fname = fname;
    }

    public String getLname() {
	return lname;
    }

    public void setLname(String lname) {
	this.lname = lname;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getMailAddress() {
	return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
	this.mailAddress = mailAddress;
    }

}
