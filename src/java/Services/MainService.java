/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.News;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
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
        return Temporary.getNews().subList(0, 4);
    }
    
    public News GetNewsById(int id) {
        for (News n: Temporary.getNews()) {
            if (n.getId() == id) return n;
        }
        return null;
    }
}

class Temporary {

    private static List<News> listNews;

    public static List<News> getNews() {
        if (listNews == null) {
            listNews = new ArrayList<News>();
            
            listNews.add(new News(1, "Title 1", "Images//placeholder-blue.png", "Lorem ipsum 1", "Author 1", null));
            listNews.add(new News(2, "Title 2", "Images//placeholder-blue.png", "Lorem ipsum 2", "Author 2", null));
            listNews.add(new News(3, "Title 3", "Images//placeholder-blue.png", "Lorem ipsum 3", "Author 3", null));
            listNews.add(new News(4, "Title 4", "Images//placeholder-blue.png", "Lorem ipsum 4", "Author 4", null));
            listNews.add(new News(5, "Title 5", "Images//placeholder-blue.png", "Lorem ipsum 5", "Author 5", null));
            listNews.add(new News(6, "Title 6", "Images//placeholder-blue.png", "Lorem ipsum 6", "Author 6", null));
            listNews.add(new News(7, "Title 7", "Images//placeholder-blue.png", "Lorem ipsum 7", "Author 7", null));
            listNews.add(new News(8, "Title 8", "Images//placeholder-blue.png", "Lorem ipsum 8", "Author 8", null));
        }

        return listNews;
    }
}
