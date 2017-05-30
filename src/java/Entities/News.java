/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author thegu
 */
public class News implements Serializable {
    private int Id;
    private String Title;
    private String Image;
    private String Description;
    private String Author;
    private Date Date;

    public News() {
    }

    public News(int Id, String Title, String Image, String Description, String Author, Date Date) {
        this.Id = Id;
        this.Title = Title;
        this.Image = Image;
        this.Description = Description;
        this.Author = Author;
        this.Date = Date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
}
