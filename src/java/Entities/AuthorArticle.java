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
public class AuthorArticle implements Serializable {
    private int Id;
    private String Title;
    private String Description;
    private Date UploadDate; 
    private Author Author; 

    public AuthorArticle() {
    }

    public AuthorArticle(int Id, String Title, String Description, Date UploadDate, Author Author) {
        this.Id = Id;
        this.Title = Title;
        this.Description = Description;
        this.UploadDate = UploadDate;
        this.Author = Author;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(Date UploadDate) {
        this.UploadDate = UploadDate;
    }

    public Author getAuthor() {
        return Author;
    }

    public void setAuthor(Author Author) {
        this.Author = Author;
    }
}
