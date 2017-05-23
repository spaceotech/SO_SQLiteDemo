package com.example.sqlitedemo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by sotsys-217 on 19/8/16.
 */
@DatabaseTable(tableName = "students")
public class Student {

    // Fields

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(columnName = "id",generatedId = true)
    private int id;

    @DatabaseField(columnName = "first_name")
    private String firstName;

    @DatabaseField(columnName = "last_name")
    private String lastName;

    @DatabaseField(columnName = "address")
    private String address;

    @DatabaseField(columnName = "mobile_number")
    private String mobileNumber;


    // Getter and Setter method of fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
