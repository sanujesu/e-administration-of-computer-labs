/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Timetable;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pakacoco
 */
@Stateless
@LocalBean
public class TimeTableSBean {
    @PersistenceContext(unitName = "eAdminLab-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Timetable> getAll() {
        return em.createNamedQuery("Timetable.findAll").getResultList();
    }
    public Timetable getTTB(){
        return (Timetable)em.createNamedQuery("Timetable.findByTimeTableID").getSingleResult();
    }
    public void insertTTB(Timetable ttb) {
        em.persist(ttb);
    }

    public void removeTTB(Timetable ttb) {
        em.remove(ttb);
    }

    public void updateTTB(Timetable ttb) {
        em.merge(ttb);
    }
}
