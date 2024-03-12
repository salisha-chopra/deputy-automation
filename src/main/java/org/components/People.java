package org.components;

import java.util.ArrayList;
public class People {
    protected String firstname;

    protected String lastname;

    protected String mobile;

    protected String email;

    protected String mainLocation;

    protected ArrayList<String> otherLocationList;

    protected String employeeAccess;
    public People() {
        firstname = "";
        lastname = "";
        mobile = "";
        email = "";
        mainLocation = "";
        employeeAccess = "";
        otherLocationList = new ArrayList();
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMainLocation(String mainLocation) {
        this.mainLocation = mainLocation;
    }
    public void setOtherLocationList(String otherLocation) {
        otherLocationList.add(otherLocation);
    }
    public void setEmployeeAccess(String employeeAccess) {
        this.employeeAccess = employeeAccess;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getMobile() {
        return mobile;
    }
    public String getEmail() {
        return email;
    }
    public String getMainLocation() {
        return mainLocation;
    }
    public String getEmployeeAccess() {
        return employeeAccess;
    }
    public ArrayList<String> getOtherLocationList() {
        return otherLocationList;
    }
}
