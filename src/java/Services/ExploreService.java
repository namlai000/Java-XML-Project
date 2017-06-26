/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.News;
import Entities.TblCategory;
import Entities.TblNewsHeader;
import Resources.Resource;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author thegu
 */
public class ExploreService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public List<TblCategory> getAllCategories() {
        TypedQuery<TblCategory> query = em.createQuery("SELECT c FROM TblCategory c", TblCategory.class);
        return query.getResultList();
    }

    public List<TblNewsHeader> GetNewsByPage(int page, int menu) {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNewsList d JOIN d.tblCategoryList e WHERE e.id = :menu", TblNewsHeader.class);
        query.setParameter("menu", menu);

        if (page == 1) {
            query.setFirstResult(0);
            query.setMaxResults(10);
            return query.getResultList();
        } else if (page > 1 && page * 10 < GetNewsLength(menu)) {
            query.setFirstResult(page * 10);
            query.setMaxResults(10);
            return query.getResultList();
        }

        return null;
    }

    public int GetNewsLength(int menu) {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNewsList d JOIN d.tblCategoryList e WHERE e.id = :menu", TblNewsHeader.class);
        query.setParameter("menu", menu);
        return query.getResultList().size();
    }
}
