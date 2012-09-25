/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.ComplaintSBean;
import ejb.EndUserSBean;
import entity.Complaint;
import entity.Status;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private EndUserSBean endUserSBean;
    @EJB
    private ComplaintSBean complaintSBean;
    private List<Complaint> lstToDo = new ArrayList<Complaint>();
    private List<Complaint> lstInProgress = new ArrayList<Complaint>();
    private List<Complaint> lstVerify = new ArrayList<Complaint>();
    private List<Complaint> lstDone = new ArrayList<Complaint>();
    private Map<String, List<Complaint>> mapListComplaint = new HashMap<String, List<Complaint>>();
    private Complaint selectedComplaint;
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

    public Complaint getSelectedComplaint() {
        return selectedComplaint;
    }

    public void setSelectedComplaint(Complaint selectedComplaint) {
        this.selectedComplaint = selectedComplaint;
    }

    @PostConstruct
    public void init() {

        this.lstToDo = this.getComplaintsByStatusID(EConstant.E_TODO_ID);
        this.lstInProgress = this.getComplaintsByStatusID(EConstant.E_INPRO_ID);
        this.lstVerify = this.getComplaintsByStatusID(EConstant.E_VERIFY_ID);
        this.lstDone = this.getComplaintsByStatusID(EConstant.E_DONE_ID);

        mapListComplaint.put(EConstant.E_TODO_ID, lstToDo);
        mapListComplaint.put(EConstant.E_INPRO_ID, lstInProgress);
        mapListComplaint.put(EConstant.E_VERIFY_ID, lstVerify);
        mapListComplaint.put(EConstant.E_DONE_ID, lstDone);
    }

    public List<Complaint> getComplaints() {
        return this.complaintSBean.getAll();
    }

    public Complaint getComplaintById(String id) {
        Complaint complaintRet = null;
        List<Complaint> lstComplaint = getComplaints();
        for (Complaint comp : lstComplaint) {
            String tempID = comp.getComplaintID().trim();
            if (tempID.equalsIgnoreCase(id)) {
                complaintRet = comp;
                break;
            }


        }
        return complaintRet;
        // return this.complaintSBean.getComplaintByID(id)
    }

    public List<Complaint> getComplaintsByStatusID(String statusID) {
        List<Complaint> lstComplaintReturn = new ArrayList<Complaint>();
        List<Complaint> lstComplaint = getComplaints();
        for (Complaint comp : lstComplaint) {
            Short idObj = Short.parseShort(statusID);
            Short idCurrent = comp.getStatus().getStatusID();
            if (String.valueOf(idCurrent).equalsIgnoreCase(String.valueOf(idObj))) {
                lstComplaintReturn.add(comp);
            }
        }
        return lstComplaintReturn;
    }

    public void handleDrop() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String source = (String) map.get(EConstant.E_OLD_STATUS);
        String content = (String) map.get(EConstant.E_CONTENT);
        String dest = (String) map.get(EConstant.E_NEW_STATUS);
        String id = "";
        String[] data = content.split(EConstant.E_SEPERATOR);
        if (data.length > 1) {
            id = data[0].trim();
        }
        Complaint currentComplaint = this.getComplaintById(id);
        Status sta = new Status(Short.parseShort(dest), "");
        currentComplaint.setStatus(sta);
        updateComplaint(currentComplaint);

        mapListComplaint.get(source).remove(currentComplaint);
        mapListComplaint.get(dest).add(currentComplaint);

        //Refresh this page
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./ComplaintBoard.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ComplaintMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INFOR", "Chuyển " + content + " từ " + source + " sang " + dest));
    }

    public void addComplaint(Complaint cmp) {
        this.complaintSBean.insertComplaint(cmp);
    }

    public void updateComplaint(Complaint cmp) {
        this.complaintSBean.updateComplaint(cmp);
    }
}
