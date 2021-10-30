package com.example.uidaiaddressupdate.requestaddress;

public class AddressModel {
    private String co;
    private String house;
    private String street;
    private String lm;
    private String loc;
    private String vtc;
    private String subdist;
    private String dist;
    private String state;
    private String country;
    private String pc;
    private String po;

    public AddressModel(){
    }

    public AddressModel(String co, String house, String street, String lm, String loc, String vtc, String subdist, String dist, String state, String country, String pc, String po) {
        this.co = co;
        this.house = house;
        this.street = street;
        this.lm = lm;
        this.loc = loc;
        this.vtc = vtc;
        this.subdist = subdist;
        this.dist = dist;
        this.state = state;
        this.country = country;
        this.pc = pc;
        this.po = po;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLm() {
        return lm;
    }

    public void setLm(String lm) {
        this.lm = lm;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getVtc() {
        return vtc;
    }

    public void setVtc(String vtc) {
        this.vtc = vtc;
    }

    public String getSubdist() {
        return subdist;
    }

    public void setSubdist(String subdist) {
        this.subdist = subdist;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }
    
}
