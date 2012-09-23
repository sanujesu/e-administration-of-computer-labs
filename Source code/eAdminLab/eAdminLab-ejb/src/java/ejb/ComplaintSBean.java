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
        Query que = em.createNamedQuery("Complaint.findAll");
        return que.getResultList();
    }

    public Complaint getComplaintByID(String id) {
        Complaint comp = null;
        Query que = em.createNamedQuery("Complaint.findByComplaintID");
        try {
            comp = (Complaint) que.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return comp;
    }

    public void insertComplaint(Complaint comp) {
        em.persist(comp);
    }

    public void removeComplaint(Complaint comp) {
        em.remove(comp);
    }

    public void updateComplaint(Complaint comp) {
        try {
            em.merge(comp);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
