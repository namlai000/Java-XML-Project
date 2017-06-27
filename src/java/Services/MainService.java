/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Author;
import Entities.AuthorArticle;
import Entities.News;
import Entities.TblCategory;
import Entities.TblNews;
import Entities.TblNewsHeader;
import Resources.Resource;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author thegu
 */
public class MainService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public List<TblNewsHeader> GetTop5RecentNews() {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT TOP 5 c FROM TblNewsHeader c ORDER BY c.date DESC", TblNewsHeader.class);
        return query.getResultList();
    }
}

class Temporary {

    private static List<News> listNews;
    private static List<Author> listAuthor;
    private static List<AuthorArticle> listArticles;

    public static List<News> getNews() {
        if (listNews == null) {
            listNews = new ArrayList<>();

            for (int i = 0; i < 45; i++) {
                listNews.add(new News(i, "Title " + i, "Images/placeholder-blue.png", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." + i, "Author " + i, Calendar.getInstance().getTime()));
            }
        }

        return listNews;
    }

    public static List<Author> getAuthors() {
        if (listAuthor == null) {
            listAuthor = new ArrayList<>();

            for (int i = 0; i < 20; i++) {
                listAuthor.add(new Author(i, "Images/avatar.png", "Firstname " + i, "Lastname " + i, "Address " + i, "Lorem ipsum author " + i, Calendar.getInstance().getTime()));
            }
        }

        return listAuthor;
    }

    public static List<AuthorArticle> getAuthorArticles() {
        if (listArticles == null) {
            listArticles = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 25; i++) {
                listArticles.add(new AuthorArticle(i, "Title" + i, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." + i, Calendar.getInstance().getTime(), getAuthors().get(random.nextInt(getAuthors().size()))));
            }
        }

        return listArticles;
    }
}
