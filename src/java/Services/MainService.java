/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblCategory;
import Entities.TblNews;
import Entities.TblNewsHeader;
import Resources.Resource;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class MainService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public List<TblNewsHeader> GetTopRecentNews() {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID e JOIN e.tblUser f WHERE f.role.id = :role ORDER BY c.date DESC", TblNewsHeader.class);
        query.setParameter("role", Resource.ROLE_JOURNALIST);
        query.setMaxResults(10);
        return query.getResultList();
    }
    
    public List<TblNewsHeader> GetTopHotNews() {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID e JOIN e.tblUser f WHERE f.role.id = :role ORDER BY c.date ASC", TblNewsHeader.class);
        query.setParameter("role", Resource.ROLE_JOURNALIST);
        query.setMaxResults(10);
        return query.getResultList();
    }
}