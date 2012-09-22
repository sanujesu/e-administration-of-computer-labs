/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.ComplaintSBean;
import entity.Complaint;
import entity.Enduser;
import entity.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import utilities.EConstant;

/**
 *
 * @author Pakacoco
 */
@ManagedBean(name = "complaintController")
@RequestScoped
public class ComplaintMBean {

    @EJB
    private ComplaintSBean complaintSBean;
    private List<Complaint> lstToDo = new ArrayList<Complaint>();
    private List<Complaint> lstInProgress = new ArrayList<Complaint>();
    private List<Complaint> lstVerify = new ArrayList<Complaint>();
    private List<Complaint> lstDone = new ArrayList<Complaint>();
    private Map<String, List<Complaint>> mapListComplaint = new HashMap<String, List<Complaint>>();

    /** Creates a new instance of ComplaintMBean */
    public ComplaintMBean() {
    }

    public List<Complaint> getLstDone() {
        return lstDone;
    }

    public void setLstDone(List<Complaint> lstDone) {
        this.lstDone = lstDone;
    }

    public List<Complaint> getLstInProgress() {
        return lstInProgress;
    }

    public void setLstInProgress(List<Complaint> lstInProgress) {
        this.lstInProgress = lstInProgress;
    }

    public List<Complaint> getLstToDo() {
        return lstToDo;
    }

    public void setLstToDo(List<Complaint> lstToDo) {
        this.lstToDo = lstToDo;
    }

    public List<Complaint> getLstVerify() {
        return lstVerify;
    }

    public void setLstVerify(List<Complaint> lstVerify) {
        this.lstVerify = lstVerify;
    }

    @PostConstruct
    public void init() {

//
//        Complaint inpro=new Complaint("01", "hello", "on pro");
//        Status sta2=new Status(Short.valueOf("2"), "in progress");
//        Enduser end2=new Enduser("001");
//        inpro.setStatus(sta2);
//        inpro.setEnduser(end2);

        this.lstToDo = this.getComplaints();
        this.lstInProgress = this.getComplaints();
        this.lstVerify = this.getComplaints();
        this.lstDone = this.getComplaints();

//        lstToDo.add(todo);
//        lstInProgress.add(inpro);

        mapListComplaint.put(EConstant.E_TODO_ID, lstToDo);
        mapListComplaint.put(EConstant.E_INPRO_ID, lstInProgress);
        mapListComplaint.put(EConstant.E_VERIFY_ID, lstVerify);
        mapListComplaint.put(EConstant.E_DONE_ID, lstDone);
    }

    public List<Complaint> getComplaints() {
        return this.complaintSBean.getAll();
    }

    public void handleDrop() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String source = (String) map.get(EConstant.E_OLD_STATUS);
        String content = (String) map.get(EConstant.E_CONTENT);
        String dest = (String) map.get(EConstant.E_NEW_STATUS);
        //Complaint objRuntime=getComplaintByID(content,"01");
        //lstToDo.remove(objRuntime);
        //lstInProgress.add(objRuntime);
        Complaint todo = new Complaint("01", "hello", "todo");
        Status sta = new Status(Short.valueOf("1"), "TODO");
        Enduser end = new Enduser("001");
        todo.setStatus(sta);
        todo.setEnduser(end);

        try {
            this.addComplaint(todo);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INFOR", "Chuyển " + content + " từ " + source + " sang " + dest));
    }

    public void addComplaint(Complaint cmp) {
        this.complaintSBean.insertComplaint(cmp);
    }
    
}
