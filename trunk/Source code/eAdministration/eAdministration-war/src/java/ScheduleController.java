
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NOAH
 */
@ManagedBean(name = "scheduleController")
@SessionScoped
public class ScheduleController {

    private ScheduleModel eventModel;
    //private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    //private String theme;

    public ScheduleController() {
        eventModel = new DefaultScheduleModel();
//        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
//        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
//        lazyEventModel = new LazyScheduleModel() {
//
//            public void fetchEvents(Date start, Date end) {
//                clear();
//                Date random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
//                random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
//            }
//        };
        //lazyEventModel = new LazyScheduleModel();
    }

    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
        event = selectEvent.getScheduleEvent();
    }

    public void onDateSelect(DateSelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", selectEvent.getDate(), selectEvent.getDate());
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
        eventModel.addEvent(new DefaultScheduleEvent("Approved " + event.getTitle(), event.getStartDate(), event.getEndDate(), "app"));
        eventModel.deleteEvent(event);
        //event = new DefaultScheduleEvent("Approved", new Date(), new Date(),"app");
        //eventModel.updateEvent(event);
    }

    public void RejEvent(ActionEvent actionEvent) {
        //eventModel.addEvent(new DefaultScheduleEvent("Rejected", new Date(), new Date(), "rej"));
        eventModel.addEvent(new DefaultScheduleEvent("Reject " + event.getTitle(), event.getStartDate(), event.getEndDate(), "rej"));
        eventModel.deleteEvent(event);
    }

    public void DelEvent(ActionEvent actionEvent) {
        //eventModel.addEvent(new DefaultScheduleEvent("Deleted", new Date(), new Date(), "rmv"));
        eventModel.deleteEvent(event);
    }
    //Getters and Setters

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }
//    public ScheduleModel getLazyEventModel() {
//        return lazyEventModel;
//    }
//
//    public void setLazyEventModel(ScheduleModel lazyEventModel) {
//        this.lazyEventModel = lazyEventModel;
//    }
//    public String getTheme() {
//        return theme;
//    }
//
//    public void setTheme(String theme) {
//        this.theme = theme;
//    }
}
