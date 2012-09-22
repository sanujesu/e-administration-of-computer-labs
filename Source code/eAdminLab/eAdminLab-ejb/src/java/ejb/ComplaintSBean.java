/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Complaint;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pakacoco
 */
@Stateless
@LocalBean
public class ComplaintSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    public List<Complaint> getAll() {
        Query que=em.createNamedQuery("Complaint.findAll");
        return que.getResultList();
    }
    public Complaint getComplaintByID(String id){
        Query que=em.createNamedQuery("Complaint.findByComplaintID");
        return (Complaint)que.getSingleResult();
    }
    public void insertComplaint(Complaint comp){
        em.persist(comp);
    }
    public void removeComplaint(Complaint comp){
        em.remove(comp);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
