/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblUser;
import Entities.TblUserInfo;
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
public class LoginService {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();
    
    public List<TblUser> GetAllLogins() {
        TypedQuery query = em.createQuery("SELECT c FROM TblUser c", TblUser.class);
        return query.getResultList();
    }
    
    public TblUserInfo GetUserInfoById(int id) {
        TblUserInfo info = em.find(TblUserInfo.class, id);
        return info;
    }
}
