/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Department;
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
public class DepartmentSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Department> getAll(){
        return em.createNamedQuery("Department.findAll").getResultList();
    }
    
    public Department getDepByID(String id){
        Query q = em.createNamedQuery("Department.findByDepartmentID");
        q.setParameter("departmentID", id);
        try {
           return (Department) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public Department getDepByName(String depName){
        Query q = em.createNamedQuery("Department.findByDepartmentName");
        q.setParameter("departmentName", depName);
        try {
           return (Department) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public void insertDep(Department dep) {
        em.persist(dep);
    }

    public void removeDep(Department dep) {
        em.remove(dep);
    }

    public void updateDep(Department dep) {
        em.merge(dep);
    }
}
