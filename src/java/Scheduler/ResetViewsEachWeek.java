/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

import Entities.TblImage;
import Entities.TblNewsHeader;
import Resources.Resource;
import Ultilities.XMLUltilities;
import java.io.File;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaUpdate;

/**
 *
 * @author thegu
 */
public class ResetViewsEachWeek implements Runnable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    @Override
    public void run() {
        try {
            em = emf.createEntityManager();
            Query query = em.createQuery("UPDATE TblNewsHeader c SET c.views = 0");
            em.getTransaction().begin();
            query.executeUpdate();
            em.flush();
            em.getTransaction().commit();
            System.out.println("Task reset views has ran!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
