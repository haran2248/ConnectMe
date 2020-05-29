package com.example.connectme;

public class Info {
    String name;
    String year;
    String Branch;
    String image;
    String Email;
    String IDno;


    public Info(String name, String year, String branch, String image, String email, String IDno) {
        this.name = name;
        this.year = year;
        Branch = branch;
        this.image = image;
        Email = email;
        this.IDno = IDno;
    }

    public String getIDno() {
        return IDno;
    }

    public void setIDno(String IDno) {
        this.IDno = IDno;
    }

    public Info() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }









    public Info(String name, String year, String branch,String email) {
        this.name = name;
        this.year = year;
        Branch = branch;
        Email=email;
        image="";
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }
}
