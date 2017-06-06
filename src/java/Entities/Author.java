/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author thegu
 */
public class Author implements Serializable {

    private int Id;
    private String Image;
    private String Firstname;
    private String Lastname;
    private String Address;
    private String Biography;
    private Date Birthday;

    public Author() {
    }

    public Author(int Id, String Image, String Firstname, String Lastname, String Address, String Biography, Date Birthday) {
        this.Id = Id;
        this.Image = Image;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Address = Address;
        this.Biography = Biography;
        this.Birthday = Birthday;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getBiography() {
        return Biography;
    }

    public void setBiography(String Biography) {
        this.Biography = Biography;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public int getAuthorAge() {
        if (this.Birthday != null) {
            return Calendar.getInstance().getTime().getYear() - this.Birthday.getYear();
        } else {
            return -1;
        }
    }
}
