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
    private EntityManager em = emf.createEntityManager();;

    public List<TblNewsHeader> Random3NewsByCategories(int sub) {
        int tmp = (int) GetNewsRow(sub);
        int random = new Random().nextInt(tmp);
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID f WHERE c.type = :role AND d.catID.id = :sub", TblNewsHeader.class);
        query.setParameter("sub", sub);
        query.setParameter("role", Resource.ROLE_JOURNALIST_TYPENEWS);
        query.setFirstResult(random);
        query.setMaxResults(3);
        return query.getResultList();
    }

    public TblNewsHeader GetNewsById(int id) {
        TblNewsHeader news = em.find(TblNewsHeader.class, id);
        return news;
    }

    public long GetNewsRow(int sub) {
        TypedQuery query = em.createQuery("SELECT COUNT(c) FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID f WHERE c.type = :role AND d.catID.id = :sub", long.class);
        query.setParameter("sub", sub);
        query.setParameter("role", Resource.ROLE_JOURNALIST_TYPENEWS);
        return (long) query.getSingleResult();
    }
}
