/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblCategory;
import Entities.TblNewsHeader;
import Entities.TblSubCategory;
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
    
    public List<TblSubCategory> getAllSubCategories() {
        TypedQuery<TblSubCategory> query = em.createQuery("SELECT c FROM TblSubCategory c", TblSubCategory.class);
        return query.getResultList();
    }

    public List<TblNewsHeader> GetNewsByPage(int page, int menu) {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d WHERE d.catID.id = :menu AND c.type = :role ORDER BY c.date DESC", TblNewsHeader.class);
        query.setParameter("menu", menu);
        query.setParameter("role", Resource.ROLE_JOURNALIST_TYPENEWS);

        if (page == 0) {
            query.setFirstResult(0);
            query.setMaxResults(10);
            return query.getResultList();
        } else {
            query.setFirstResult((page - 1) * 10);
            query.setMaxResults(10);
            return query.getResultList();
        }
    }

    public long GetNewsLength(int menu) {
        TypedQuery query = em.createQuery("SELECT COUNT(c) FROM TblNewsHeader c JOIN c.tblNews d WHERE d.catID.id = :menu AND c.type = :role", long.class);
        query.setParameter("menu", menu);
        query.setParameter("role", Resource.ROLE_JOURNALIST_TYPENEWS);
        return (long)query.getSingleResult();
    }
}
