/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Author;
import Entities.AuthorArticle;
import Entities.News;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thegu
 */
public class AuthorService {

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

    public List<AuthorArticle> GetAuthorArticleListByAuthorId(int authorId) {
        List<AuthorArticle> list = null;
        for (AuthorArticle n : Temporary.getAuthorArticles()) {
            if (n.getAuthor().getId() == authorId) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(n);
            }
        }
        return list;
    }
}
