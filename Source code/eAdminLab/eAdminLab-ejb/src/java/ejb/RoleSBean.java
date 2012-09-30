/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Role;
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
public class RoleSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     public List<Role> getAll(){
        return em.createNamedQuery("Role.findAll").getResultList();
    }
    
    public Role getRoleByID(String id){
        Query q = em.createNamedQuery("Role.findByRoleID");
        q.setParameter("roleID", id);
        try {
           return (Role) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public Role getResourceByName(String roleName){
        Query q = em.createNamedQuery("Role.findByRoleName");
        q.setParameter("roleName", roleName);
        try {
           return (Role) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public void insertRole(Role rol) {
        em.persist(rol);
    }

    public void removeRole(Role rol) {
        em.remove(rol);
    }

    public void updateRole(Role rol) {
        em.merge(rol);
    }
}
