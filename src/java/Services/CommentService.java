/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblComment;
import Entities.TblNews;
import Entities.TblNewsHeader;
import Entities.TblUserInfo;
import Resources.Resource;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author thegu
 */
public class CommentService {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();
    
    public List<TblComment> getCommentsByNewId(int newId) {
        TypedQuery query = em.createQuery("SELECT c FROM TblComment c WHERE c.newsID.headerID = :id ORDER BY c.uploadDate DESC", TblComment.class);
        query.setParameter("id", newId);
        return query.getResultList();
    }
    
    public boolean postComment(String comment, int userId, int newId) {
        TblComment com = new TblComment();
        com.setNewsID(new TblNews(newId));
        com.setUserID(new TblUserInfo(userId));
        com.setContent(comment);
        com.setUploadDate(Calendar.getInstance().getTime());
        em.getTransaction().begin();
        em.persist(com);
        em.getTransaction().commit();
        return true;
    }
}
