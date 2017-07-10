/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblNewsHeader;
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
public class SearchService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public List<TblNewsHeader> SearchByTittle(String tittle) {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID f WHERE c.tittle LIKE :query ORDER BY c.date DESC", TblNewsHeader.class);
        query.setParameter("query", "%" + tittle + "%");
        return query.getResultList();
    }
    
    public List<TblUserInfo> SearchAuthorByName(String tittle) {
        TypedQuery<TblUserInfo> query = em.createQuery("SELECT c FROM TblUserInfo c JOIN c.tblUser d WHERE d.role.id <= 2 AND (c.firstname LIKE :query OR c.middlename LIKE :query OR c.lastname LIKE :query) ORDER BY c.createDate DESC", TblUserInfo.class);
        query.setParameter("query", "%" + tittle + "%");
        return query.getResultList();
    }
}
