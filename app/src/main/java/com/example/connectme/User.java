package com.example.connectme;

public class User {

String BitsK,DEVSOC,Email,IDno,QC,WSC,gender,interests,movies,whatsapp;

    public  User(){

    }

    public User(String bitsK, String DEVSOC, String email, String IDno, String QC, String WSC, String gender, String interests, String movies, String whatsapp) {
        BitsK = bitsK;
        this.DEVSOC = DEVSOC;
        Email = email;
        this.IDno = IDno;
        this.QC = QC;
        this.WSC = WSC;
        this.gender = gender;
        this.interests = interests;
        this.movies = movies;
        this.whatsapp = whatsapp;
    }

    public String getBitsK() {
        return BitsK;
    }

    public void setBitsK(String bitsK) {
        BitsK = bitsK;
    }

    public String getDEVSOC() {
        return DEVSOC;
    }

    public void setDEVSOC(String DEVSOC) {
        this.DEVSOC = DEVSOC;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIDno() {
        return IDno;
    }

    public void setIDno(String IDno) {
        this.IDno = IDno;
    }

    public String getQC() {
        return QC;
    }

    public void setQC(String QC) {
        this.QC = QC;
    }

    public String getWSC() {
        return WSC;
    }

    public void setWSC(String WSC) {
        this.WSC = WSC;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
}
