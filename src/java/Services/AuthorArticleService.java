/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblNewsHeader;
import Resources.Resource;
import java.util.ArrayList;
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
public class AuthorArticleService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public List<TblNewsHeader> GetAuthorArticlesByPage(int page) {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNewsList d JOIN d.authorID f JOIN f.userId g WHERE g.role.id = :role", TblNewsHeader.class);
        query.setParameter("role", Resource.ROLE_AUTHORIZEDUSER);

        if (page == 1) {
            query.setFirstResult(0);
            query.setMaxResults(10);
            return query.getResultList();
        } else if (page > 1 && page * 10 < (int) GetAuthorArticlesSize()) {
            query.setFirstResult(page * 10);
            query.setMaxResults(10);
            return query.getResultList();
        }

        return null;
    }

    public TblNewsHeader GetAuthorArticleById(int id) {
        TblNewsHeader news = em.find(TblNewsHeader.class, id);
        return news;
    }

    public long GetAuthorArticlesSize() {
        TypedQuery query = em.createQuery("SELECT COUNT(c) FROM TblNewsHeader c JOIN c.tblNewsList d JOIN d.authorID f JOIN f.userId g WHERE g.role.id = :role", long.class);
        query.setParameter("role", Resource.ROLE_AUTHORIZEDUSER);
        return (long) query.getSingleResult();
    }

    public List<TblNewsHeader> Random3Articles() {
        int size = (int)GetAuthorArticlesSize();
        int random = new Random().nextInt(size);
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNewsList d JOIN d.authorID f JOIN f.userId g WHERE g.role.id = :role", TblNewsHeader.class);
        query.setParameter("role", Resource.ROLE_AUTHORIZEDUSER);
        query.setFirstResult(random);
        query.setMaxResults(3);
        return query.getResultList();
    }
}
