package com.vec.smd.avro.model;

public class Address {

    private String country;
    private String state;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    private String addressLine1;

    public Address(String country, String state, String addr1){

        this.country = country;
        this.state = state;
        this.addressLine1 = addr1;

    }

    @Override
    public String toString() {
        return this.country + " " + this.state + " " + this.addressLine1;
    }
}
