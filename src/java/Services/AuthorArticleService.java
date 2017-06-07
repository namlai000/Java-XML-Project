/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.AuthorArticle;
import Entities.News;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<AuthorArticle> Random3Articles() {
        List<AuthorArticle> random3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            random3.add(Temporary.getAuthorArticles().get(new Random().nextInt(GetAuthorArticlesSize())));
        }

        return random3;
    }
}
