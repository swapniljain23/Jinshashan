package com.swapniljain.jinshashan.model;

public class JNListDataModel {

    // Inner classes.

    public class PersonalInfo {
        public String fullName;
        public String dateOfBirth;
        public String gender;
        public String fatherNmae;
        public String motherName;
        public String birthCity;
        public String birthCityPincode;
        public String birthState;
        public String education;
        public String remarks;
    }

    public class DikshaInfo {
        public String fullNewName;
        public String dikshaDate;
        public String dikshaCity;
        public String dikshaCityPincode;
        public String dikshaState;
        public String dikshitBy;
        public String dikshaGuru;
        public String remarks;
    }

    public class Subcaste {
        public String subcaste1;
        public String subcaste2;
        public String subcaste3;
        public String subcaste4;
        public String subcaste5;
    }

    public class RecentInfo {
        public String address;
        public String city;
        public String state;
        public String pincode;
        public String contactName;
        public String contactPhoneNo;
        public String contactEmail;
        public String remarks;
    }

    // Properties.

    public PersonalInfo personalInfo;

    public DikshaInfo dikshaInfo;

    public RecentInfo mostRecentInfo;

    public Subcaste subcaste;

    public String photoURL;

    public String specialRemarks;

    // Setters, Getters.

}
