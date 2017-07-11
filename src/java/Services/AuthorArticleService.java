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
    private EntityManager em = emf.createEntityManager();;

    public List<TblNewsHeader> GetAuthorArticlesByPage(int page) {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID e WHERE c.type = :role ORDER BY c.date DESC", TblNewsHeader.class);
        query.setParameter("role", Resource.ROLE_AUTHORIZEDUSER_TYPEARTICLE);

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

    public TblNewsHeader GetAuthorArticleById(int id) {
        TblNewsHeader news = em.find(TblNewsHeader.class, id);
        return news;
    }

    public long GetAuthorArticlesSize() {
        TypedQuery query = em.createQuery("SELECT COUNT(c) FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID e WHERE c.type = :role", long.class);
        query.setParameter("role", Resource.ROLE_AUTHORIZEDUSER_TYPEARTICLE);
        return (long) query.getSingleResult();
    }

    public List<TblNewsHeader> Random3Articles() {
        int size = (int)GetAuthorArticlesSize();
        int random = new Random().nextInt(size);
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID e WHERE c.type = :role", TblNewsHeader.class);
        query.setParameter("role", Resource.ROLE_AUTHORIZEDUSER_TYPEARTICLE);
        query.setFirstResult(random);
        query.setMaxResults(3);
        return query.getResultList();
    }
}
