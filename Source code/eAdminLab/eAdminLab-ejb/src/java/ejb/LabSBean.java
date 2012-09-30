/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Lab;
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
public class LabSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

     
    public List<Lab> getAll(){
        return em.createNamedQuery("Lab.findAll").getResultList();
    }
    
    public Lab getLabByID(String id){
        Query q = em.createNamedQuery("Lab.findByLabID");
        q.setParameter("labID", id);
        try {
           return (Lab) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public Lab getLabByName(String labName){
        Query q = em.createNamedQuery("Lab.findByLabName");
        q.setParameter("labName", labName);
        try {
           return (Lab) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public void insertLab(Lab lab) {
        em.persist(lab);
    }

    public void removeLab(Lab lab) {
        em.remove(lab);
    }

    public void updateLab(Lab lab) {
        em.merge(lab);
    }
}
