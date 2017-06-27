/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblCategory;
import Entities.TblNewsHeader;
import Resources.Resource;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author thegu
 */
public class ArticleService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public List<TblNewsHeader> Random3NewsByCategories(List<TblCategory> category) {
        String str = "";
        for (int i = 0; i < category.size(); i++) {
            if (i == category.size() - 1) {
                str += category.get(i).getId();
            } else {
                str += category.get(i).getId() + " OR e.id = ";
            }
        }
        int random = new Random().nextInt((int) GetNewsRow(str));
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNewsList d JOIN d.tblCategoryList e JOIN d.authorID f JOIN f.userId g WHERE g.role.id = :role"
                + " AND e.id = "
                + str, TblNewsHeader.class);
        query.setParameter("role", Resource.ROLE_JOURNALIST);
        query.setFirstResult(random);
        query.setMaxResults(3);
        return query.getResultList();
    }

    public TblNewsHeader GetNewsById(int id) {
        TblNewsHeader news = em.find(TblNewsHeader.class, id);
        return news;
    }

    public long GetNewsRow(String str) {
        TypedQuery query = em.createQuery("SELECT COUNT(c) FROM TblNewsHeader c JOIN c.tblNewsList d JOIN d.tblCategoryList e JOIN d.authorID f JOIN f.userId g WHERE g.role.id = :role"
                + " AND e.id = "
                + str, long.class);
        query.setParameter("role", Resource.ROLE_JOURNALIST);
        return (long)query.getSingleResult();
    }
}
