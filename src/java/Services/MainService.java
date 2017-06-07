/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Author;
import Entities.AuthorArticle;
import Entities.News;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author thegu
 */
public class MainService {

    public List<News> GetNewsByPage(int page) {
        if (page == 1) {
            return Temporary.getNews().subList(0, 10);
        } else if (page > 1 && page * 10 < GetNewsLength()) {
            return Temporary.getNews().subList(page * 10 - 10, page * 10);
        } else if (page > 1 && page * 10 >= GetNewsLength()) {
            return Temporary.getNews().subList(page * 10 - 10, GetNewsLength());
        }
        
        return null;
    }
    
    public int GetNewsLength() {
        return Temporary.getNews().size();
    }

    public List<News> GetTop5RecentNews() {
        return Temporary.getNews().subList(0, 5);
    }
    
    public List<News> Random3News() {
        List<News> random3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            random3.add(Temporary.getNews().get(new Random().nextInt(GetNewsLength())));
        }
        
        return random3;
    }

    public News GetNewsById(int id) {
        for (News n : Temporary.getNews()) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
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
