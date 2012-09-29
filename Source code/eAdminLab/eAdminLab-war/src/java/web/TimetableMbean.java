/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.EndUserSBean;
import ejb.RequestSBean;
import ejb.TimeTableSBean;
import entity.Enduser;
import entity.Request;
import entity.Timetable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import utilities.EConstant;

/**
 *
 * @author NOAH
 */
@ManagedBean(name = "timetableMbean")
@SessionScoped
public class TimetableMbean {

    @EJB
    private RequestSBean requestSBean;
    @EJB
    private EndUserSBean enduserSBean;
    @EJB
    private TimeTableSBean timeTableSBean;
    private List<Timetable> lst = new ArrayList<Timetable>();
    private List<Timetable> lstF = new ArrayList<Timetable>();
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private Date dateSelect;
    private int startHours;
    private List<String> lstInstructor = new ArrayList<String>();
    private Request req;
    private String titleReq;
    private String description;
    private String defaultConstructor;
    private Enduser instructor;
    private Timetable selectedTimeTable = new Timetable();
    private Request selectedRequest = new Request();

    /**
     * Creates a new instance of TimetableMbean
     */
    public TimetableMbean() {
        //eventModel.addEvent(new DefaultScheduleEvent("abc", previousDay8Pm(), previousDay11Pm()));
        //lst = this.getAllTTB();
//        for(int i = 0; i < lst.size(); i ++){
//          eventModel.addEvent(new DefaultScheduleEvent(lst.get(i).getTimeTableID(),lst.get(i).getStartTime(),lst.get(i).getStartTime()));
//        }
        eventModel = new DefaultScheduleModel();
    }

    @PostConstruct
    public final void init() {

        lst = getAllTTB();
        lstInstructor = getAllIns();
        setDefaultConstructor(EConstant.E_DEFAULT_INSTRUCTOR);
       
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getLabStatus().trim().equals("fix")) {
                eventModel.addEvent(new DefaultScheduleEvent(lst.get(i).getTimeTableID() + " - " + lst.get(i).getLab().getLabName() + " - " + lst.get(i).getClasses().getClassName(), lst.get(i).getStartTime(), lst.get(i).getEndTime()));
            } else if (lst.get(i).getLabStatus().trim().equals("free")) {
                eventModel.addEvent(new DefaultScheduleEvent(lst.get(i).getTimeTableID() + " - " + lst.get(i).getLab().getLabName() + " - " + lst.get(i).getClasses().getClassName(), lst.get(i).getStartTime(), lst.get(i).getEndTime(), "free"));
            } else if (lst.get(i).getLabStatus().trim().equals("pending")) {
                eventModel.addEvent(new DefaultScheduleEvent(lst.get(i).getTimeTableID() + " - " + lst.get(i).getLab().getLabName() + " - " + lst.get(i).getClasses().getClassName(), lst.get(i).getStartTime(), lst.get(i).getEndTime(), "pen"));
            }
        }
    }

    public Request getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(Request selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public Timetable getSelectedTimeTable() {
        return selectedTimeTable;
    }

    public void setSelectedTimeTable(Timetable selectedTimeTable) {
        this.selectedTimeTable = selectedTimeTable;
    }

    public List<Timetable> getAllTTB() {
        return this.timeTableSBean.getAll();

    }

    public String getDefaultConstructor() {
        return defaultConstructor;
    }

    public void setDefaultConstructor(String defaultConstructor) {
        this.defaultConstructor = defaultConstructor;
    }

    public Timetable getTimeTBId(String id) {
        Timetable timeTB = null;
        List<Timetable> lstTTB = getAllTB();
        for (Timetable TTB : lstTTB) {
            String tempID = TTB.getTimeTableID().trim();
            if (tempID.equalsIgnoreCase(id)) {
                timeTB = TTB;
                break;
            }
        }
        return timeTB;
        // return this.complaintSBean.getComplaintByID(id)
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Calendar addHours() {
        Calendar cld = Calendar.getInstance();
        cld.setTime(event.getStartDate());
        cld.add(Calendar.HOUR_OF_DAY, this.getStartHours());
        return cld;
    }

    public void addEvent(ActionEvent actionEvent) {
        eventModel.addEvent(new DefaultScheduleEvent(event.getTitle(), addHours().getTime(), addHours().getTime()));
    }

    public void sendEvent(ActionEvent actionEvent) {
        //this.req = requestSBean.create();
        req = new Request();
        req.setRequestID("Req" + String.valueOf(requestSBean.listAll().size() + 1));
        req.setTitle(getTitleReq());
        req.setDescription(getDescription());
        req.setTimetable(getTimeTBId(customizeTitle(event.getTitle().trim())));
        req.setEnduser(instructor);
        req.setEnduser1(instructor);
        req.setStatusID(Boolean.FALSE);
        insertRequest(req);
        ////////////////////
        eventModel.addEvent(new DefaultScheduleEvent(event.getTitle().trim(), addHours().getTime(), addHours().getTime(), "pen"));
        eventModel.deleteEvent(event);
        /////////////////
        Timetable ttb = getTimeTBId(customizeTitle(event.getTitle().trim()));
        ttb.setLabStatus("pending");
        updateTTB(ttb);
        //Refresh this page
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("./faces/index.xhtml");
//        } catch (IOException ex) {
//            Logger.getLogger(TimetableMbean.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
        event = selectEvent.getScheduleEvent();
        this.setStartHours(event.getStartDate().getHours());
        selectedTimeTable = getTimeTBId(customizeTitle(event.getTitle().trim()));
        selectedRequest = getRequestFromTimeTableID(selectedTimeTable);
        if(selectedRequest.getEnduser()!=null)
            setDefaultConstructor(selectedRequest.getEnduser().getUserName());
        else
            setDefaultConstructor(EConstant.E_DEFAULT_INSTRUCTOR);
    }

    public void onDateSelect(DateSelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", selectEvent.getDate(), selectEvent.getDate());
        this.setStartHours(event.getStartDate().getHours());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Test", "Day delta:" + event);
        addMessage(message);

    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void AppEvent(ActionEvent actionEvent) {
        eventModel.addEvent(new DefaultScheduleEvent("Approved " + event.getTitle(), addHours().getTime(), addHours().getTime()));
        eventModel.deleteEvent(event);
        ///////////////////////////////////////////
        Timetable ttb = getTimeTBId(customizeTitle(event.getTitle().trim()));
        ttb.setLabStatus("fix");
        updateTTB(ttb);
    }

    public void RejEvent(ActionEvent actionEvent) {
        eventModel.addEvent(new DefaultScheduleEvent("Reject " + event.getTitle(), addHours().getTime(), addHours().getTime(), "free"));
        eventModel.deleteEvent(event);
        //////////////////////////////////////////
        Timetable ttb = getTimeTBId(customizeTitle(event.getTitle().trim()));
        ttb.setLabStatus("free");
        updateTTB(ttb);
    }

    public void DelEvent(ActionEvent actionEvent) {
        eventModel.deleteEvent(event);
    }
    // xu ly su kien giao dien
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateTTB(Timetable ttb) {
        this.timeTableSBean.updateTTB(ttb);
    }

//    public void sendRequest() {
////        System.out.println("zo send");
////        this.req = requestSBean.create();
////        req.setTitle(getTitle());
////        insertRequest(req);
////        System.out.println("zosau khi goi insert");
//        this.req = requestSBean.create();
//        req = new Request();
//        req.setRequestID("Req" + String.valueOf(requestSBean.listAll().size() + 1));
//        req.setTitle(getTitleReq());
//        req.setDescription(getDescription());
//        req.setTimetable(getTimeTBId(event.getTitle().trim()));
//        req.setEnduser(instructor);
//        req.setEnduser1(instructor);
//        insertRequest(req);
//    }
//
    public void insertRequest(Request req) {
        requestSBean.insertRequest(req);
    }

    public Timetable getTTB() {
        return this.timeTableSBean.getTTB();
    }

    public List<String> getAllIns() {
        List<String> lstInsName = new ArrayList<String>();
        List<Enduser> lstIns = this.enduserSBean.getAllIns();
        for (Enduser ins : lstIns) {
            if (ins != null) {
                lstInsName.add(ins.getUserName());
            }
        }
        return lstInsName;
    }

    public String customizeTitle(String str) {
        return str.substring(0, str.indexOf("-")).trim();
    }

    public void getInstructor(AjaxBehaviorEvent event) {
        String name = (String) ((UIInput) event.getComponent()).getValue();
        instructor = getUserByName(name);
    }

    public Enduser getUserByName(String name) {
        return this.enduserSBean.getUserByName(name);
    }
    //Getter and Setter

    public List<Timetable> getAllTB() {
        return getTimeTableSBean().getAll();
    }

    /**
     * @return the timeTableSBean
     */
    public TimeTableSBean getTimeTableSBean() {
        return timeTableSBean;
    }

    /**
     * @param timeTableSBean the timeTableSBean to set
     */
    public void setTimeTableSBean(TimeTableSBean timeTableSBean) {
        this.timeTableSBean = timeTableSBean;
    }

    /**
     * @return the lst
     */
    public List<Timetable> getLst() {
        return lst;
    }

    /**
     * @param lst the lst to set
     */
    public void setLst(List<Timetable> lst) {
        this.lst = lst;
    }

    /**
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    /**
     * @return the event
     */
    public ScheduleEvent getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    /**
     * @return the dateSelect
     */
    public Date getDateSelect() {
        return dateSelect;
    }

    /**
     * @param dateSelect the dateSelect to set
     */
    public void setDateSelect(Date dateSelect) {
        this.dateSelect = dateSelect;
    }

    /**
     * @return the startHours
     */
    public int getStartHours() {
        return startHours;
    }

    /**
     * @param startHours the startHours to set
     */
    public void setStartHours(int startHours) {
        this.startHours = startHours;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    /**
     * @return the lstF
     */
    public List<Timetable> getLstF() {
        return lstF;
    }

    /**
     * @param lstF the lstF to set
     */
    public void setLstF(List<Timetable> lstF) {
        this.lstF = lstF;
    }

    /**
     * @return the lstInstructor
     */
    public List<String> getLstInstructor() {
        return lstInstructor;
    }

    /**
     * @param lstInstructor the lstInstructor to set
     */
    public void setLstInstructor(List<String> lstInstructor) {
        this.lstInstructor = lstInstructor;
    }

    /**
     * @return the req
     */
    public Request getReq() {
        return req;
    }

    /**
     * @param req the req to set
     */
    public void setReq(Request req) {
        this.req = req;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        if(selectedRequest!=null)
            return selectedRequest.getDescription();
        else
            return "";
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the titleReq
     */
    public String getTitleReq() {
        if(selectedRequest!=null)
            return selectedRequest.getTitle();
        else
            return "";
    }

    /**
     * @param titleReq the titleReq to set
     */
    public void setTitleReq(String titleReq) {
        this.titleReq = titleReq;
    }

    /**
     * @return the instructor
     */
    public Enduser getInstructor() {
        return instructor;
    }

    /**
     * @param instructor the instructor to set
     */
    public void setInstructor(Enduser instructor) {
        this.instructor = instructor;
    }

    public void valueChange(ValueChangeEvent event) {
        String name = (String) ((UIInput) event.getComponent()).getValue();
        instructor = getUserByName(name);
        req.setEnduser(instructor);
    }

    public Request getRequestFromTimeTableID(Timetable timeTable) {
        Request ret=new Request();
        String timeTableID=timeTable.getTimeTableID().trim();
        List<Request> lstRequest=this.requestSBean.listAll();
        if(lstRequest.size()>0){
            for (Request request : lstRequest) {
                String itemTimeTableID=request.getTimetable().getTimeTableID().trim();
                if(timeTableID.equalsIgnoreCase(itemTimeTableID)){
                    ret=request;
                    break;
                }
            }
        }
        return ret;
    }
}
