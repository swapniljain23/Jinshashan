package com.swapniljain.transformation;

public class JNListDataModel {

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
            dikshaName = "N/A";
            dikshaDate = "N/A";
            dikshaCity = "N/A";
            dikshaState = "N/A";
            dikshitBy = "N/A";
            dikshaGuru = "N/A";
            remarks = "N/A";
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
        public String gender;
        public String fatherNmae;
        public String motherName;
        public String birthCity;
        public String birthState;
        public String education;
        public String remarks;

        // Default constructor.
        public PersonalInfo () {
            fullName = "N/A";
            dateOfBirth = "N/A";
            gender = "N/A";
            fatherNmae = "N/A";
            motherName = "N/A";
            birthCity = "N/A";
            birthState = "N/A";
            education = "N/A";
            remarks = "N/A";
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
            sect1 = "N/A";
            sect2 = "N/A";
            sect3 = "N/A";
            sect4 = "N/A";
            sect5 = "N/A";
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
            address = "N/A";
            city = "N/A";
            state = "N/A";
            contactName = "N/A";
            contactPhoneNo = "N/A";
            contactEmail = "N/A";
            remarks = "N/A";
            lastUpdatedTimestamp = "N/A";
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

    public JNListDataModel () {
        dikshaInfo = new DikshaInfo();
        personalInfo = new PersonalInfo();
        recentInfo = new RecentInfo();
        sect = new Sect();
        photoURL = "";
        specialRemarks = "N/A";
    }

    @Override
    public String toString() {
        return  dikshaInfo.toString() + "\n" +
                personalInfo.toString() + "\n" +
                sect.toString() + "\n" +
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

