/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Resource;
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
public class ResourceSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     public List<Resource> getAll(){
        return em.createNamedQuery("Resource.findAll").getResultList();
    }
    
    public Resource getResourceByID(String id){
        Query q = em.createNamedQuery("Resource.findByResourceID");
        q.setParameter("resourceID", id);
        try {
           return (Resource) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public Resource getResourceByName(String resName){
        Query q = em.createNamedQuery("Resource.findByResourceName");
        q.setParameter("resourceName", resName);
        try {
           return (Resource) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public void insertRes(Resource res) {
        em.persist(res);
    }

    public void removeRes(Resource res) {
        em.remove(res);
    }

    public void updateRes(Resource res) {
        em.merge(res);
    }
}
