/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Complaint;
import java.util.ArrayList;
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

    public List<Complaint> getComplaintsByUserName(String userName) {
        List<Complaint> lstComplaintsReturn=new ArrayList<Complaint>();
        List<Complaint> lstComplaint=getAll();
        if(lstComplaint.size()>0){
            for (Complaint complaint : lstComplaint) {
                String itemUserName = complaint.getEnduser1().getUserName().trim();
                if(itemUserName.equalsIgnoreCase(userName))
                    lstComplaintsReturn.add(complaint);
            }
        }
        return lstComplaintsReturn;
    }
     public List<Complaint> getComplaintsByStatus(String statusID,List<Complaint> lstComplaint){
        List<Complaint> lstComplaintReturn = new ArrayList<Complaint>();
        if(lstComplaint.size()>0){
            for (Complaint comp : lstComplaint) {
            Short idObjStatus = Short.parseShort(statusID);
            Short idCurrentStatus = comp.getStatus().getStatusID();
            if (String.valueOf(idCurrentStatus).equalsIgnoreCase(String.valueOf(idObjStatus))) {
                lstComplaintReturn.add(comp);
            }
        }
        }
        return lstComplaintReturn;
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
