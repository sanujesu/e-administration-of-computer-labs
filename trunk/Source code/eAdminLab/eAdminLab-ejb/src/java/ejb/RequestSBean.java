/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Enduser;
import entity.Request;
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
public class RequestSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void insertRequest(Request req) {
        try{
        em.persist(req);
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    public void removeRequest(Request req) {
        em.remove(req);
    }

    public void updateRequest(Request req) {
        em.merge(req);
    }
    public Request create(){
        return new Request();
    }
    public List<Request> listAll(){
     return em.createNamedQuery("Request.findAll").getResultList()   ;
    }
//    public List<Enduser> getDiscountCodes() {
//        Query query = em.createNamedQuery("Enduser.findIns");
//        return query.getResultList();
//
//    }
}
