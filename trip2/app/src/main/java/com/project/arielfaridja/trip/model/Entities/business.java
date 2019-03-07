package com.project.arielfaridja.trip.model.Entities;

/**
 * Created by c plus on 05/12/2016.
 */

public class business {
    int ID;
    String name;
    Address address;
    String PhoneNumber;
    String Email;
    String Website;

    public business(String name, Address address, String phoneNumber, String email, String website, int id) {
        this.name = name;
        this.address = address;
        PhoneNumber = phoneNumber;
        Email = email;
        Website = website;
        ID  = id;
    }

    public business() {
        this.ID = 0;
        this.name = "";
        this.address = new Address();
        PhoneNumber = "";
        Email = "";
        Website = "";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }
}
