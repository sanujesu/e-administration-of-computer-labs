/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Classes;
import entity.Enduser;
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
public class ClasseSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Classes> getAll(){
        return em.createNamedQuery("Classes.findAll").getResultList();
    }
    
    public Classes getClassByID(String id){
        Query q = em.createNamedQuery("Enduser.findByUserID");
        q.setParameter("userID", id);
        try {
           return (Classes) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public Classes getClassByName(String username){
        Query q = em.createNamedQuery("Enduser.findByUserName");
        q.setParameter("userName", username);
        try {
           return (Classes) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public void insertClass(Classes cll) {
        em.persist(cll);
    }

    public void removeClass(Classes cll) {
        em.remove(cll);
    }

    public void updateClass(Classes cll) {
        em.merge(cll);
    }
}
