/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblNewsHeader;
import Entities.TblUserInfo;
import Resources.Resource;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author thegu
 */
public class AuthorService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public List<TblUserInfo> getTop5Authors() {
        TypedQuery<TblUserInfo> query = em.createQuery("SELECT c FROM TblUserInfo c ORDER BY c.createDate ASC", TblUserInfo.class);
        return query.getResultList();
    }

    public List<TblUserInfo> getNewest10Authors() {
        TypedQuery<TblUserInfo> query = em.createQuery("SELECT c FROM TblUserInfo c ORDER BY c.createDate DESC", TblUserInfo.class);
        return query.getResultList();
    }

    public List<TblUserInfo> getAllAuthor() {
        TypedQuery<TblUserInfo> query = em.createQuery("SELECT c FROM TblUserInfo c", TblUserInfo.class);
        return query.getResultList();
    }

    public TblUserInfo getAuthorById(int id) {
        return em.find(TblUserInfo.class, id);
    }

    public List<TblNewsHeader> GetAuthorArticleListByAuthorId(int authorId) {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID f WHERE f.userId = :id AND c.type = :role", TblNewsHeader.class);
        query.setParameter("role", Resource.ROLE_AUTHORIZEDUSER);
        return query.getResultList();
    }
}
