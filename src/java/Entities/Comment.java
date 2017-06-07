/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Servlet.ArticleServlet;
import java.io.Serializable;

/**
 *
 * @author thegu
 */
public class Comment implements Serializable {
    private int Id;
    private Author Author;
    private News News;
    private AuthorArticle Articles;

    public Comment() {
    }

    public Comment(int Id, Author Author, News News, AuthorArticle Articles) {
        this.Id = Id;
        this.Author = Author;
        this.News = News;
        this.Articles = Articles;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Author getAuthor() {
        return Author;
    }

    public void setAuthor(Author Author) {
        this.Author = Author;
    }

    public News getNews() {
        return News;
    }

    public void setNews(News News) {
        this.News = News;
    }

    public AuthorArticle getArticles() {
        return Articles;
    }

    public void setArticles(AuthorArticle Articles) {
        this.Articles = Articles;
    }
    
    
}
