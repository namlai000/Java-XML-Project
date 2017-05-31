/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Author;
import Entities.News;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thegu
 */
public class MainService {

    public List<News> GetAllNews() {
        return Temporary.getNews();
    }

    public List<News> GetTop5RecentNews() {
        return Temporary.getNews().subList(0, 5);
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

    public static List<News> getNews() {
        if (listNews == null) {
            listNews = new ArrayList<News>();

            for (int i = 0; i < 15; i++) {
                listNews.add(new News(i, "Title " + i, "Images/placeholder-blue.png", "Lorem ipsum ip ip " + i, "Author " + i, Calendar.getInstance().getTime()));
            }
        }

        return listNews;
    }

    public static List<Author> getAuthors() {
        if (listAuthor == null) {
            listAuthor = new ArrayList<Author>();

            for (int i = 0; i < 10; i++) {
                listAuthor.add(new Author(i, "Images/avatar.png", "Firstname " + i, "Lastname " + i, "Address " + i, "Lorem ipsum author " + i, Calendar.getInstance().getTime()));
            }
        }

        return listAuthor;
    }
}
