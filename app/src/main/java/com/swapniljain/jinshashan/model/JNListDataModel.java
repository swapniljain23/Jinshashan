package com.swapniljain.jinshashan.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.firebase.database.DataSnapshot;
import com.swapniljain.jinshashan.utils.JNUtils;

public class JNListDataModel implements Parcelable {

    // Inner classes.

    public class DikshaInfo {
        public String dikshaName;
        public String dikshaDate;
        public String dikshaCity;
        public String dikshaState;
        public String dikshitBy;
        public String dikshaGuru;
        public String remarks;

        // Default constructor.
        public DikshaInfo () {

        }

        // Constructor.
        public DikshaInfo(DataSnapshot snapshot) {
            dikshaName = snapshot.child("dikshaName").getValue(String.class);
            dikshaDate = snapshot.child("dikshaDate").getValue(String.class);
            dikshaCity = snapshot.child("dikshaCity").getValue(String.class);
            dikshaState = snapshot.child("dikshaState").getValue(String.class);
            dikshitBy = snapshot.child("dikshitBy").getValue(String.class);
            dikshaGuru = snapshot.child("dikshaGuru").getValue(String.class);
            remarks = snapshot.child("remarks").getValue(String.class);
        }

        @Override
        public String toString() {
            return "DikshaInfo: " +
                    dikshaName + ", " +
                    dikshaDate + ", " +
                    dikshaCity + ", " +
                    dikshaState + ", " +
                    dikshitBy + ", " +
                    dikshaGuru + ", " +
                    remarks;
        }
    }

    public class PersonalInfo {
        public String fullName;
        public String dateOfBirth;
        public int age;
        public String gender;
        public String fatherNmae;
        public String motherName;
        public String birthCity;
        public String birthState;
        public String education;
        public String remarks;

        // Default constructor.
        public PersonalInfo () {

        }

        // Constructor.
        public PersonalInfo(DataSnapshot snapshot) {
            fullName = snapshot.child("fullName").getValue(String.class);
            dateOfBirth = snapshot.child("dateOfBirth").getValue(String.class);
            age = JNUtils.calculateAge(dateOfBirth);
            gender = snapshot.child("gender").getValue(String.class);
            fatherNmae = snapshot.child("fatherName").getValue(String.class);
            motherName = snapshot.child("motherName").getValue(String.class);
            birthCity = snapshot.child("birthCity").getValue(String.class);
            birthState = snapshot.child("birthState").getValue(String.class);
            education = snapshot.child("education").getValue(String.class);
            remarks = snapshot.child("remarks").getValue(String.class);
        }

        @Override
        public String toString() {
            return "Personal Info: " +
                    fullName + ", " +
                    dateOfBirth + ", " +
                    age + ", " +
                    gender + ", " +
                    fatherNmae + ", " +
                    motherName + ", " +
                    birthCity + ", " +
                    birthState + ", " +
                    education + ", " +
                    remarks;
        }
    }

    public class Sect {
        public String sect1;
        public String sect2;
        public String sect3;
        public String sect4;
        public String sect5;

        // Default constructor.
        public Sect () {

        }

        // Constructor.
        public Sect (DataSnapshot snapshot) {
            sect1 = snapshot.child("sect1").getValue(String.class);
            sect2 = snapshot.child("sect2").getValue(String.class);
            sect3 = snapshot.child("sect3").getValue(String.class);
            sect4 = snapshot.child("sect4").getValue(String.class);
            sect5 = snapshot.child("sect5").getValue(String.class);
        }

        @Override
        public String toString() {
            return "Sect: " +
                    sect1 + ", " +
                    sect2 + ", " +
                    sect3 + ", " +
                    sect4 + ", " +
                    sect5;
        }
    }

    public class RecentInfo {
        public String address;
        public String city;
        public String state;
        public String contactName;
        public String contactPhoneNo;
        public String contactEmail;
        public String remarks;
        public String lastUpdatedTimestamp;

        // Default constructor.
        public RecentInfo () {

        }

        // Constructor.
        public RecentInfo(DataSnapshot snapshot) {
            address = snapshot.child("address").getValue(String.class);
            city = snapshot.child("city").getValue(String.class);
            state = snapshot.child("state").getValue(String.class);
            contactName = snapshot.child("contactName").getValue(String.class);
            contactPhoneNo = snapshot.child("contactPhoneNo").getValue(String.class);
            contactEmail = snapshot.child("contactEmail").getValue(String.class);
            remarks = snapshot.child("remarks").getValue(String.class);
            lastUpdatedTimestamp = snapshot.child("lastUpdatedTimestamp").getValue(String.class);
        }

        @Override
        public String toString() {
            return "RecentInfo: " +
                    address + ", " +
                    city + ", " +
                    state + ", " +
                    contactName + ", " +
                    contactPhoneNo + ", " +
                    contactEmail + ", " +
                    remarks + ", " +
                    lastUpdatedTimestamp;
        }
    }

    // Properties.

    public DikshaInfo dikshaInfo;

    public PersonalInfo personalInfo;

    public RecentInfo recentInfo;

    public Sect sect;

    public String photoURL;

    public String specialRemarks;

    // Constructor.

    public JNListDataModel(DataSnapshot dataSnapshot) {
        dikshaInfo = new DikshaInfo(dataSnapshot.child("dikshaInfo"));
        personalInfo = new PersonalInfo(dataSnapshot.child("personalInfo"));
        sect = new Sect(dataSnapshot.child("sect"));
        recentInfo = new RecentInfo(dataSnapshot.child("recentInfo"));
        photoURL = dataSnapshot.child("photoURL").getValue(String.class);
        specialRemarks = dataSnapshot.child("specialRemarks").getValue(String.class);
    }

    protected JNListDataModel(Parcel in) {
        dikshaInfo = new DikshaInfo();
        dikshaInfo.dikshaName = in.readString();
        dikshaInfo.dikshaDate = in.readString();
        dikshaInfo.dikshaCity = in.readString();
        dikshaInfo.dikshaState = in.readString();
        dikshaInfo.dikshitBy = in.readString();
        dikshaInfo.dikshaGuru = in.readString();
        dikshaInfo.remarks = in.readString();

        personalInfo = new PersonalInfo();
        personalInfo.fullName = in.readString();
        personalInfo.dateOfBirth = in.readString();
        personalInfo.age = in.readInt();
        personalInfo.gender = in.readString();
        personalInfo.fatherNmae = in.readString();
        personalInfo.motherName = in.readString();
        personalInfo.birthCity = in.readString();
        personalInfo.birthState = in.readString();
        personalInfo.education = in.readString();
        personalInfo.remarks = in.readString();

        sect = new Sect();
        sect.sect1 = in.readString();
        sect.sect2 = in.readString();
        sect.sect3 = in.readString();
        sect.sect4 = in.readString();
        sect.sect5 = in.readString();

        recentInfo = new RecentInfo();
        recentInfo.address = in.readString();
        recentInfo.city = in.readString();
        recentInfo.state = in.readString();
        recentInfo.contactName = in.readString();
        recentInfo.contactPhoneNo = in.readString();
        recentInfo.contactEmail = in.readString();
        recentInfo.remarks = in.readString();
        recentInfo.lastUpdatedTimestamp = in.readString();

        photoURL = in.readString();
        specialRemarks = in.readString();
    }

    public static final Creator<JNListDataModel> CREATOR = new Creator<JNListDataModel>() {
        @Override
        public JNListDataModel createFromParcel(Parcel source) {
            return new JNListDataModel(source);
        }

        @Override
        public JNListDataModel[] newArray(int size) {
            return new JNListDataModel[size];
        }
    };

    @Override
    public String toString() {
        return  dikshaInfo.toString() + "\n" +
                personalInfo.toString() + "\n" +
                sect.toString() + "\n" +
                recentInfo.toString() + "\n" +
                "Photo Url: " + photoURL + "\n" +
                "Special Remarks: " + specialRemarks;
    }

    // Parcelable implementation.

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(dikshaInfo.dikshaName);
        dest.writeString(dikshaInfo.dikshaDate);
        dest.writeString(dikshaInfo.dikshaCity);
        dest.writeString(dikshaInfo.dikshaState);
        dest.writeString(dikshaInfo.dikshitBy);
        dest.writeString(dikshaInfo.dikshaGuru);
        dest.writeString(dikshaInfo.remarks);

        dest.writeString(personalInfo.fullName);
        dest.writeString(personalInfo.dateOfBirth);
        dest.writeInt(personalInfo.age);
        dest.writeString(personalInfo.gender);
        dest.writeString(personalInfo.fatherNmae);
        dest.writeString(personalInfo.motherName);
        dest.writeString(personalInfo.birthCity);
        dest.writeString(personalInfo.birthState);
        dest.writeString(personalInfo.education);
        dest.writeString(personalInfo.remarks);

        dest.writeString(sect.sect1);
        dest.writeString(sect.sect2);
        dest.writeString(sect.sect3);
        dest.writeString(sect.sect4);
        dest.writeString(sect.sect5);

        dest.writeString(recentInfo.address);
        dest.writeString(recentInfo.city);
        dest.writeString(recentInfo.state);
        dest.writeString(recentInfo.contactName);
        dest.writeString(recentInfo.contactPhoneNo);
        dest.writeString(recentInfo.contactEmail);
        dest.writeString(recentInfo.remarks);
        dest.writeString(recentInfo.lastUpdatedTimestamp);

        dest.writeString(photoURL);
        dest.writeString(specialRemarks);
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

    public Sect getSect() {
        return sect;
    }

    public void setSect(Sect sect) {
        this.sect = sect;
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
