/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.News;
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
}

class Temporary {

    private static List<News> listNews;

    public static List<News> getNews() {
        listNews = new ArrayList<News>();
        
        listNews.add(new News(1, "Title 1", "Images/placeholder-blue.png", "Lorem ipsum 1", "Author 1", Date.from(Instant.now())));
        listNews.add(new News(1, "Title 2", "Images/placeholder-blue.png", "Lorem ipsum 2", "Author 2", Date.from(Instant.now())));
        listNews.add(new News(1, "Title 3", "Images/placeholder-blue.png", "Lorem ipsum 3", "Author 3", Date.from(Instant.now())));
        listNews.add(new News(1, "Title 4", "Images/placeholder-blue.png", "Lorem ipsum 4", "Author 4", Date.from(Instant.now())));
        listNews.add(new News(1, "Title 5", "Images/placeholder-blue.png", "Lorem ipsum 5", "Author 5", Date.from(Instant.now())));
        listNews.add(new News(1, "Title 6", "Images/placeholder-blue.png", "Lorem ipsum 6", "Author 6", Date.from(Instant.now())));
        listNews.add(new News(1, "Title 7", "Images/placeholder-blue.png", "Lorem ipsum 7", "Author 7", Date.from(Instant.now())));
        listNews.add(new News(1, "Title 8", "Images/placeholder-blue.png", "Lorem ipsum 8", "Author 8", Date.from(Instant.now())));
        
        return listNews;
    }
}
