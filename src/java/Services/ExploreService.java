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
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.tblSubCategoryList e WHERE e.id = :menu AND d.authorID.tblUser.role.id = :role", TblNewsHeader.class);
        query.setParameter("menu", menu);
        query.setParameter("role", Resource.ROLE_JOURNALIST);

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
        TypedQuery query = em.createQuery("SELECT COUNT(c) FROM TblNewsHeader c JOIN c.tblNews d JOIN d.tblSubCategoryList e WHERE e.id = :menu AND d.authorID.tblUser.role.id = :role", long.class);
        query.setParameter("menu", menu);
        query.setParameter("role", Resource.ROLE_JOURNALIST);
        return (long)query.getSingleResult();
    }
}
