/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Status;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author NOAH
 */
@Stateless
@LocalBean
public class StatusSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     public List<Status> getAll(){
        return em.createNamedQuery("Status.findAll").getResultList();
    }
    
    public Status getStatusByID(String id){
        Query q = em.createNamedQuery("Status.findByStatusID");
        q.setParameter("statusID", id);
        try {
           return (Status) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public Status getResourceByName(String statusName){
        Query q = em.createNamedQuery("Status.findByStatusName");
        q.setParameter("statusName", statusName);
        try {
           return (Status) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public void insertStatus(Status stt) {
        em.persist(stt);
    }

    public void removeStatus(Status stt) {
        em.remove(stt);
    }

    public void updateStatus(Status stt) {
        em.merge(stt);
    }
}
