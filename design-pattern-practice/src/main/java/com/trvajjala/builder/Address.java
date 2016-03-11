package com.trvajjala.builder;

public class Address {

    private String city;
    private String postalCode;
    private String street;
    private String landmark;

    private String address1;

    private String address2;

    private String town;

    private String firstname;

    private String phone;
    private String lastname;

    private String middlename;

    private String landphone;

    private String mobile;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLandphone() {
        return landphone;
    }

    public void setLandphone(String landphone) {
        this.landphone = landphone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        final StringBuilder builder2 = new StringBuilder();
        builder2.append("Address [");
        if (city != null) {
            builder2.append("city=");
            builder2.append(city);
            builder2.append(", ");
        }
        if (postalCode != null) {
            builder2.append("postalCode=");
            builder2.append(postalCode);
            builder2.append(", ");
        }
        if (street != null) {
            builder2.append("street=");
            builder2.append(street);
            builder2.append(", ");
        }
        if (landmark != null) {
            builder2.append("landmark=");
            builder2.append(landmark);
            builder2.append(", ");
        }
        if (address1 != null) {
            builder2.append("address1=");
            builder2.append(address1);
            builder2.append(", ");
        }
        if (address2 != null) {
            builder2.append("address2=");
            builder2.append(address2);
            builder2.append(", ");
        }
        if (town != null) {
            builder2.append("town=");
            builder2.append(town);
            builder2.append(", ");
        }
        if (firstname != null) {
            builder2.append("firstname=");
            builder2.append(firstname);
            builder2.append(", ");
        }
        if (phone != null) {
            builder2.append("phone=");
            builder2.append(phone);
            builder2.append(", ");
        }
        if (lastname != null) {
            builder2.append("lastname=");
            builder2.append(lastname);
            builder2.append(", ");
        }
        if (middlename != null) {
            builder2.append("middlename=");
            builder2.append(middlename);
            builder2.append(", ");
        }
        if (landphone != null) {
            builder2.append("landphone=");
            builder2.append(landphone);
            builder2.append(", ");
        }
        if (mobile != null) {
            builder2.append("mobile=");
            builder2.append(mobile);
        }
        builder2.append("]");
        return builder2.toString();
    }

    public static class Builder {

        private final Address address;

        public Builder() {
            address = new Address();
        }

        public Address createAddress() {
            return address;
        }

        public Builder withFirstname(String firstname) {
            address.setFirstname(firstname);
            return this;
        }

        public Builder withLastname(String lastname) {
            address.setLastname(lastname);
            return this;
        }

        public Builder withMobile(String mobile) {
            address.setMobile(mobile);
            return this;
        }

        public Builder withCity(String city) {
            address.setCity(city);
            return this;
        }

        public Builder withPostalCode(String postalCode) {
            address.setPostalCode(postalCode);
            return this;
        }

        public Builder withStreet(String street) {
            address.setStreet(street);
            return this;
        }

    }

}
