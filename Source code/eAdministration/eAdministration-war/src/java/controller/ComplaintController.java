/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Complaint;
import java.util.ArrayList;
import java.util.List;
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

    /** Creates a new instance of ComplaintController */
    public ComplaintController() {
        lstToDo = new ArrayList<Complaint>();
        lstInProgress = new ArrayList<Complaint>();
        lstVerify = new ArrayList<Complaint>();
        lstDone = new ArrayList<Complaint>();

        Complaint todo1 = new Complaint("01", "todo1", "01");
        Complaint todo2 = new Complaint("02", "todo2", "01");
        Complaint inpro1 = new Complaint("03", "inpro1", "02");
        Complaint inpro2 = new Complaint("04", "inpro2", "02");
        Complaint verify1 = new Complaint("05", "verify1", "03");
        Complaint verify2 = new Complaint("06", "verify2", "03");

        lstToDo.add(todo1);
        lstToDo.add(todo2);

        lstInProgress.add(inpro1);
        lstInProgress.add(inpro2);

        lstVerify.add(verify1);
        lstVerify.add(verify2);
    }

    public void onDropInPro(DragDropEvent event) {
        Complaint complaint = (Complaint) event.getData();

        lstInProgress.add(complaint);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(complaint.getDescription() + " added"));
    }
}
