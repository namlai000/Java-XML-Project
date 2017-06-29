/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
public class SearchService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public List<TblNewsHeader> SearchByTittle(String tittle) {
        TypedQuery<TblNewsHeader> query = em.createQuery("SELECT c FROM TblNewsHeader c JOIN c.tblNews d JOIN d.authorID f WHERE f.tblUser.role.id = :role AND c.tittle LIKE :query ORDER BY c.date DESC", TblNewsHeader.class);
        query.setParameter("role", Resource.ROLE_JOURNALIST);
        query.setParameter("query", "%" + tittle + "%");
        return query.getResultList();
    }
}
