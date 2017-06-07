/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.AuthorArticle;
import java.util.List;

/**
 *
 * @author thegu
 */
public class AuthorArticleService {
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
    
    public AuthorArticle GetAuthorArticleById(int id) {
        for (AuthorArticle n : Temporary.getAuthorArticles()) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
    }
    
    public int GetAuthorArticlesSize() {
        return Temporary.getAuthorArticles().size();
    }
}
