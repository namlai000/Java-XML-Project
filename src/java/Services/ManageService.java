/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblImage;
import Entities.TblNews;
import Entities.TblNewsHeader;
import Resources.Resource;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author thegu
 */
public class ManageService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public List<TblNewsHeader> GetArticleListByUserID(int authorId) {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID f WHERE f.userId = :id", TblNewsHeader.class);
        query.setParameter("id", authorId);
        return query.getResultList();
    }

    public void RemoveArticle(int id) {
        TblNewsHeader header = em.find(TblNewsHeader.class, id);
        em.getTransaction().begin();
        em.remove(header);
        em.remove(header.getTblNews());
        for (TblImage images : header.getTblNews().getTblImageList()) {
            em.remove(images);
        }
        em.flush();
        em.getTransaction().commit();
    }
    
    public void DeleteOldImages(int id) {
        TblNews n = em.find(TblNews.class, id);
        List<TblImage> images = n.getTblImageList();
        em.getTransaction().begin();
        for (TblImage i : images) {
            em.remove(i);
        }
        em.flush();
        em.getTransaction().commit();
    }
}
