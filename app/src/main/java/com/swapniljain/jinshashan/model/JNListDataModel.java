package com.swapniljain.jinshashan.model;

import com.google.firebase.database.DataSnapshot;

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

        // Constructor.
        public PersonalInfo(DataSnapshot snapshot) {
            fullName = snapshot.child("fullName").getValue(String.class);
            dateOfBirth = snapshot.child("dateOfBirth").getValue(String.class);
            gender = snapshot.child("gender").getValue(String.class);
            fatherNmae = snapshot.child("fatherName").getValue(String.class);
            motherName = snapshot.child("motherName").getValue(String.class);
            birthCity = snapshot.child("birthCity").getValue(String.class);
            birthCityPincode = snapshot.child("birthCityPincode").getValue(String.class);
            birthState = snapshot.child("birthState").getValue(String.class);
            education = snapshot.child("education").getValue(String.class);
            remarks = snapshot.child("remarks").getValue(String.class);
        }

        @Override
        public String toString() {
            return "Personal Info: " +
                    fullName + ", " +
                    dateOfBirth + ", " +
                    gender + ", " +
                    fatherNmae + ", " +
                    motherName + ", " +
                    birthCity + ", " +
                    birthCityPincode + ", " +
                    birthState + ", " +
                    education + ", " +
                    remarks;
        }
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

        // Constructor.
        public DikshaInfo(DataSnapshot snapshot) {
            fullNewName = snapshot.child("fullNewName").getValue(String.class);
            dikshaDate = snapshot.child("dikshaDate").getValue(String.class);
            dikshaCity = snapshot.child("dikshaCity").getValue(String.class);
            dikshaCityPincode = snapshot.child("dikshaCityPincode").getValue(String.class);
            dikshaState = snapshot.child("dikshaState").getValue(String.class);
            dikshitBy = snapshot.child("dikshitBy").getValue(String.class);
            dikshaGuru = snapshot.child("dikshaGuru").getValue(String.class);
            remarks = snapshot.child("remarks").getValue(String.class);
        }

        @Override
        public String toString() {
            return "DikshaInfo: " +
                    fullNewName + ", " +
                    dikshaDate + ", " +
                    dikshaCity + ", " +
                    dikshaCityPincode + ", " +
                    dikshaState + ", " +
                    dikshitBy + ", " +
                    dikshaGuru + ", " +
                    remarks;
        }
    }

    public class Subcaste {
        public String subcaste1;
        public String subcaste2;
        public String subcaste3;
        public String subcaste4;
        public String subcaste5;

        // Constructor.
        public Subcaste (DataSnapshot snapshot) {
            subcaste1 = snapshot.child("subcaste1").getValue(String.class);
            subcaste2 = snapshot.child("subcaste2").getValue(String.class);
            subcaste3 = snapshot.child("subcaste3").getValue(String.class);
            subcaste4 = snapshot.child("subcaste4").getValue(String.class);
            subcaste5 = snapshot.child("subcaste5").getValue(String.class);
        }

        @Override
        public String toString() {
            return "Subcaste: " +
                    subcaste1 + ", " +
                    subcaste2 + ", " +
                    subcaste3 + ", " +
                    subcaste4 + ", " +
                    subcaste5;
        }
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

        // Constructor.
        public RecentInfo(DataSnapshot snapshot) {
            address = snapshot.child("address").getValue(String.class);
            city = snapshot.child("city").getValue(String.class);
            state = snapshot.child("state").getValue(String.class);
            pincode = snapshot.child("pincode").getValue(String.class);
            contactName = snapshot.child("contactName").getValue(String.class);
            contactPhoneNo = snapshot.child("contactPhoneNo").getValue(String.class);
            contactEmail = snapshot.child("contactEmail").getValue(String.class);
            remarks = snapshot.child("remarks").getValue(String.class);
        }

        @Override
        public String toString() {
            return "RecentInfo: " +
                    address + ", " +
                    city + ", " +
                    state + ", " +
                    pincode + ", " +
                    contactName + ", " +
                    contactPhoneNo + ", " +
                    contactEmail + ", " +
                    remarks;
        }
    }

    // Properties.

    public PersonalInfo personalInfo;

    public DikshaInfo dikshaInfo;

    public RecentInfo recentInfo;

    public Subcaste subcaste;

    public String photoURL;

    public String specialRemarks;

    // Constructor.

    public JNListDataModel(DataSnapshot dataSnapshot) {
        photoURL = dataSnapshot.child("photoUrl").getValue(String.class);
        specialRemarks = dataSnapshot.child("specialRemarks").getValue(String.class);
        personalInfo = new PersonalInfo(dataSnapshot.child("personalInfo"));
        dikshaInfo = new DikshaInfo(dataSnapshot.child("dikshaInfo"));
        subcaste = new Subcaste(dataSnapshot.child("subcaste"));
        recentInfo = new RecentInfo(dataSnapshot.child("recentInfo"));
    }

    @Override
    public String toString() {
        return  personalInfo.toString() + "\n" +
                dikshaInfo.toString() + "\n" +
                subcaste.toString() + "\n" +
                recentInfo.toString() + "\n" +
                "Photo Url: " + photoURL + "\n" +
                "Special Remarks: " + specialRemarks;
    }

    // Setters, Getters.

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public DikshaInfo getDikshaInfo() {
        return dikshaInfo;
    }

    public void setDikshaInfo(DikshaInfo dikshaInfo) {
        this.dikshaInfo = dikshaInfo;
    }

    public RecentInfo getRecentInfo() {
        return recentInfo;
    }

    public void setRecentInfo(RecentInfo recentInfo) {
        this.recentInfo = recentInfo;
    }

    public Subcaste getSubcaste() {
        return subcaste;
    }

    public void setSubcaste(Subcaste subcaste) {
        this.subcaste = subcaste;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getSpecialRemarks() {
        return specialRemarks;
    }

    public void setSpecialRemarks(String specialRemarks) {
        this.specialRemarks = specialRemarks;
    }
}
