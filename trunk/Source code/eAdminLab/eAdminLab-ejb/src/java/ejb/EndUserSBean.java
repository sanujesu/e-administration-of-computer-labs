/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Enduser;
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
public class EndUserSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public Enduser getUserByID(String id){
        Query query=em.createNamedQuery(id);
        Enduser user=null;
        try{
        user=(Enduser) query.getSingleResult();
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        return user;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
