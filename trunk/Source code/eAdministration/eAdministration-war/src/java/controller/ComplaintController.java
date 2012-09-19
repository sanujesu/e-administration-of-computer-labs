/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constant.EConstant;
import entity.Complaint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author Pakacoco
 */
@ManagedBean(name = "complaintController")
@RequestScoped
public class ComplaintController {

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
    private List<Complaint> lstToDo;
    private List<Complaint> lstInProgress;
    private List<Complaint> lstVerify;
    private List<Complaint> lstDone;
    private int count;
    private Map<String, List<Complaint> > mapListComplaint;

    /** Creates a new instance of ComplaintController */
    public ComplaintController() {
        lstToDo = new ArrayList<Complaint>();
        lstInProgress = new ArrayList<Complaint>();
        lstVerify = new ArrayList<Complaint>();
        lstDone = new ArrayList<Complaint>();
        mapListComplaint=new HashMap<String, List<Complaint>>();
        count = 0;
        Complaint todo1 = new Complaint("01", "todo1", "01");
        Complaint todo2 = new Complaint("02", "todo2", "01");
        Complaint inpro1 = new Complaint("03", "inpro1", "02");
        Complaint inpro2 = new Complaint("04", "inpro2", "02");
        Complaint verify1 = new Complaint("05", "verify1", "03");
        Complaint verify2 = new Complaint("06", "verify2", "03");
        Complaint done1 = new Complaint("07", "done1", "04");
        Complaint done2 = new Complaint("08", "done2", "04");

        lstToDo.add(todo1);
        lstToDo.add(todo2);

        lstInProgress.add(inpro1);
        lstInProgress.add(inpro2);

        lstVerify.add(verify1);
        lstVerify.add(verify2);

        lstDone.add(done1);
        lstDone.add(done2);

        mapListComplaint.put(EConstant.E_TODO_ID, lstToDo);
        mapListComplaint.put(EConstant.E_INPRO_ID, lstInProgress);
        mapListComplaint.put(EConstant.E_VERIFY_ID, lstVerify);
        mapListComplaint.put(EConstant.E_DONE_ID, lstDone);


    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Complaint getComplaintByID(String id, String status) {
        List<Complaint> lstComplaint=mapListComplaint.get(status);
            for (Complaint complaint : lstComplaint) {
                if (complaint.getDescription().equalsIgnoreCase(id)) {
                    return complaint;
                }
            }

        return null;
    }
    public void processForComplaint(String source, String dest, Complaint objCom){

    }

    public void handleDrop() {
        count++;
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String source = (String) map.get(EConstant.E_SOURCE);
        String content = (String) map.get(EConstant.E_CONTENT);
        String dest = (String) map.get(EConstant.E_DEST);
        Complaint objRuntime=getComplaintByID(content,"01");
        lstToDo.remove(objRuntime);
        lstInProgress.add(objRuntime);
        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INFOR", "Chuyển " + content + " từ " + source + " sang " + dest));
    }

    
}
