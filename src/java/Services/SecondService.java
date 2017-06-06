/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Author;
import Entities.AuthorArticle;
import java.util.List;

/**
 *
 * @author thegu
 */
public class SecondService {

    public List<Author> getTop5Authors() {
        return Temporary.getAuthors().subList(0, 5);
    }
    
    public List<Author> getNewest10Authors() {
        return Temporary.getAuthors().subList(0, 10);
    }

    public List<Author> getAllAuthor() {
        return Temporary.getAuthors();
    }
    
    public Author getAuthorById(int id) {
        for (Author au : Temporary.getAuthors()) {
            if (au.getId() == id) {
                return au;
            }
        }
        return null;
    }
    
    public List<AuthorArticle> GetAuthorArticlesByPage(int page) {
        if (page == 1) {
            return Temporary.getAuthorArticles().subList(0, 10);
        } else if (page > 1 && page * 10 < GetAuthorArticlesSize()) {
            return Temporary.getAuthorArticles().subList(page * 10 - 10, page * 10);
        } else if (page > 1 && page * 10 >= GetAuthorArticlesSize()) {
            return Temporary.getAuthorArticles().subList(page * 10 - 10, GetAuthorArticlesSize());
        }
        
        return null;
    }
    
    public int GetAuthorArticlesSize() {
        return Temporary.getAuthorArticles().size();
    }
}
