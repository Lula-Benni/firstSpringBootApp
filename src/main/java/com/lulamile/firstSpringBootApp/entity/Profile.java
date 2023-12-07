package com.lulamile.firstSpringBootApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String fullName;
    private String userName;
    private String cellNumber;
    private String password;
    //BCryptPasswordEncoder bCryptPasswordEncoder;
   /* public Profile() {
        fullName ="lula";
        email = "lulabenni45@gmail.com";
        cellNumber = "0783432345";
        password="12345";
    }*/
    /*public Profile(String fullName, String email,String cellNumber, String password) {
        this.fullName = fullName;
        this.email = email;
        this.cellNumber = cellNumber;
        this.password=password;
        //this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        //this.password = this.bCryptPasswordEncoder.encode(password);
    }*/
    public String getUserName() {return userName;}
    public String getFullName() {
        return fullName;
    }
    public String getPassword() {
        return password;
    }
    public String getCellNumber() {return cellNumber;}
    public void setCellNumber(String cellNumber) {this.cellNumber = cellNumber;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setFullName(String useName) {
        this.fullName = useName;
    }
    public void setPassword(String password) {
        //this.password = bCryptPasswordEncoder.encode(password);
        this.password=password;
    }
    @Override
    public String toString() {
        return "Profile{" +
                "fullName='" + fullName + '\'' +
                ", email='" + userName + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                ", password='" + password + '\'' +
                //", bCryptPasswordEncoder=" + bCryptPasswordEncoder +
                '}';
    }

    public void logging(){
        System.out.println("Logged in");
    }
}
