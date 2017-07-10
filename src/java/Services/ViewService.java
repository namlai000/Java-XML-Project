/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblNewsHeader;
import Resources.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author thegu
 */
public class ViewService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public void IncreaseView(int newsId) {
        TblNewsHeader header = em.find(TblNewsHeader.class, newsId);
        int currentView = header.getViews();
        header.setViews(currentView + 1);
        em.getTransaction().begin();
        em.merge(header);
        em.getTransaction().commit();
    }
}
