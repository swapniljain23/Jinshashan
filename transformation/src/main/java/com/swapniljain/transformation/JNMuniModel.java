package com.swapniljain.transformation;

import java.util.Locale;

public class JNMuniModel {

    private int mid;
    private double lat;
    private double lng;
    private String location;
    private String locality;
    private String timestamp;
    private int id;
    private int upadhi;
    private String title;
    private String name;
    private String alias;
    private int sangha;
    private String vairagya;
    private String birthname;
    private String dob;
    private String father;
    private String mother;
    private String spouse;
    private int approved;
    private String grahtyag;
    private String education;
    private int uid;
    private String uname;
    private String prefix;
    private String suffix;
    private String imgUrl;

    @Override
    public String toString() {
        return String.format(Locale.US,
                "%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                uname,
                name,
                location,
                birthname,
                dob,
                father,
                mother,
                spouse,
                education,
                suffix);
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUpadhi() {
        return upadhi;
    }

    public void setUpadhi(int upadhi) {
        this.upadhi = upadhi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getSangha() {
        return sangha;
    }

    public void setSangha(int sangha) {
        this.sangha = sangha;
    }

    public String getVairagya() {
        return vairagya;
    }

    public void setVairagya(String vairagya) {
        this.vairagya = vairagya;
    }

    public String getBirthname() {
        return birthname;
    }

    public void setBirthname(String birthname) {
        this.birthname = birthname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getGrahtyag() {
        return grahtyag;
    }

    public void setGrahtyag(String grahtyag) {
        this.grahtyag = grahtyag;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
