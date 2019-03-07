package com.project.arielfaridja.Operator.model.Entities;

import java.io.Serializable;

/**
 * Created by c plus on 05/12/2016.
 */

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    private String country;
    private String city;
    private String street;
    private int number;

    public Address(String country, String city, String street, int number) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Address() {
        country = "";
        city = "";
        street = "";
        number = 0;
    }

    @Override
    public String toString() {
        return (country + ", " + city + ", " + street + ", " + number);
    }

    public static Address parse(String s) {
        Address address = new Address();
        String[] parts = s.split(", ");
        if (parts.length > 4)
            return null;
        address.setCountry(parts[0]);
        address.setCity(parts[1]);
        address.setStreet(parts[2]);
        address.setNumber(Integer.parseInt(parts[3]));
        return address;
    }

}



