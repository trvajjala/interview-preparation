package pattern.structural.adapter;

public class EmployeeLdap {


    private String cn;
    private String givenName;
    private String surname;
    private String mailAddress;

    public EmployeeLdap(String cn, String givenName, String surname, String mailAddress) {
	super();
	this.cn = cn;
	this.givenName = givenName;
	this.surname = surname;
	this.mailAddress = mailAddress;
    }

    public String getCn() {
	return cn;
    }

    public void setCn(String cn) {
	this.cn = cn;
    }

    public String getGivenName() {
	return givenName;
    }

    public void setGivenName(String givenName) {
	this.givenName = givenName;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getMailAddress() {
	return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
	this.mailAddress = mailAddress;
    }




}
