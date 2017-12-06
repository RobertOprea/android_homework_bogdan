package com.courses.bogdan.tema3;

import java.io.Serializable;

/**
 * Created by Bogdan on 12/6/2017.
 */

public class ObjectToSend  implements Serializable {

        private String firstName, lastName, Address;
        int Age;

        public ObjectToSend(String fname, String lname, int age, String address) {

            firstName = fname;
            lastName = lname;
            Age = age;
            Address = address;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String printValues() {

            String data = null;

            data = "First Name :" + firstName + " Last Name :" + lastName
                    + " Age : " + Age + " Address : " + Address;

            return data;
        }
    }
