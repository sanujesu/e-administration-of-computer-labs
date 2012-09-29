/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Enduser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utilities.EConstant;

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

    public Enduser getUserByID(String id) {
        Query query = em.createNamedQuery(id);
        Enduser user = null;
        try {
            user = (Enduser) query.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return user;
    }

    public List<Enduser> getAll() {
        Query que = null;
        try {
            que = em.createNamedQuery("Enduser.findAll");
        } catch (Exception ex) {
        }
        return que.getResultList();
    }
    public Enduser getUserByName(String name){
        List<Enduser> lstUser=getAll();
        Enduser user=null;
        if(lstUser.size()>0 && name!=null){
            for (Enduser enduser : lstUser) {
                if(enduser.getUserName().trim().equalsIgnoreCase(name)){
                    user=enduser;
                    break;
                }
            }
        }
        return user;
    }
    public List<Enduser> getUsersByRole(String roleID){
        if(roleID==null||roleID.equals(EConstant.E_STRING_EMPTY))
            return null;
        List<Enduser> lstUser=this.getAll();
        List<Enduser> lstUserRet=new ArrayList<Enduser>();
        for(Enduser user : lstUser){
            String userRoleID=String.valueOf(user.getRole().getRoleID()).trim();
            if(userRoleID.equalsIgnoreCase(roleID))
                lstUserRet.add(user);
        }
        return lstUserRet;
    }
    public void insert(Enduser user) {
        try{
        em.persist(user);
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    //ngoc phan
    public List<Enduser> getAllIns() {
        return em.createNamedQuery("Enduser.findIns").getResultList();
        //return null;
    }
}
