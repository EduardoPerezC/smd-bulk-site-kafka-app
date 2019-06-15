package com.vec.smd.avro.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String custId;
    private String firstName;
    private String lastName;
    private List<Address> addresses;
    private int age;

    public List<Address> getAddresses() {
        return addresses;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Customer(String custId, String firstName, String lastName){

        this.custId = custId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = new ArrayList<>();

    }

    public Customer(int Id, int age){

       this( "code cust " + Id,"first cust " + Id ,"last cust " + Id);
       this.age = age;

    }


    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addAddress(Address address){
        this.addresses.add(address);

    }

    @Override
    public String toString() {

        return "Customer # " + this.custId + " age " + this.age;

    }
}
