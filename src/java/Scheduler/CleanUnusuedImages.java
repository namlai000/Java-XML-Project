/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

import Entities.TblImage;
import Resources.Resource;
import Ultilities.XMLUltilities;
import java.io.File;
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
public class CleanUnusuedImages implements Runnable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    @Override
    public void run() {
        try {
            em = emf.createEntityManager();
            TypedQuery<TblImage> query = em.createQuery("SELECT c FROM TblImage c", TblImage.class);
            List<TblImage> images = query.getResultList();
            em.setProperty("javax.persistence.query.timeout", 10000);
            em.getTransaction().begin();
            for (TblImage image : images) {
                if (image.getTblNewsList().isEmpty() && image.getTblUserInfoList().isEmpty()) {
                    try {
                        String file = image.getLink();
                        em.remove(image);
                        em.flush();
                        System.out.println(file + " has been removed from database!");
                        File targetFile = new File(Resource.LOCATION_PATH + "/../../web/" + file);
                        if (targetFile.exists()) {
                            targetFile.delete();
                            System.out.println(file + " has been removed from hard disks!");
                        }
                    } catch (Exception e) {
                        System.out.println("Image is being used!");
                    }
                }
            }
            em.getTransaction().commit();
            System.out.println("Task clean images has ran!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
