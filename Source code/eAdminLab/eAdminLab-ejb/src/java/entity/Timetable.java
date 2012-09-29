/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Pakacoco
 */
@Entity
@Table(name = "TIMETABLE", catalog = "eAdministration", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Timetable.findAll", query = "SELECT t FROM Timetable t"),
    @NamedQuery(name = "Timetable.findByTimeTableID", query = "SELECT t FROM Timetable t WHERE t.timeTableID = :timeTableID"),
    @NamedQuery(name = "Timetable.findByStartTime", query = "SELECT t FROM Timetable t WHERE t.startTime = :startTime"),
    @NamedQuery(name = "Timetable.findByEndTime", query = "SELECT t FROM Timetable t WHERE t.endTime = :endTime"),
    @NamedQuery(name = "Timetable.findByLabStatus", query = "SELECT t FROM Timetable t WHERE t.labStatus = :labStatus")})
public class Timetable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "timeTableID", nullable = false, length = 15)
    private String timeTableID;
    @Basic(optional = false)
    @Column(name = "startTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "endTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "labStatus", nullable = false, length = 15)
    private String labStatus;
    @JoinColumn(name = "labID", referencedColumnName = "labID", nullable = false)
    @ManyToOne(optional = false)
    private Lab lab;
    @JoinColumn(name = "classID", referencedColumnName = "classID", nullable = false)
    @ManyToOne(optional = false)
    private Class class1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timetable")
    private List<Request> requestList;

    public Timetable() {
    }

    public Timetable(String timeTableID) {
        this.timeTableID = timeTableID;
    }

    public Timetable(String timeTableID, Date startTime, Date endTime, String labStatus) {
        this.timeTableID = timeTableID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.labStatus = labStatus;
    }

    public String getTimeTableID() {
        return timeTableID;
    }

    public void setTimeTableID(String timeTableID) {
        this.timeTableID = timeTableID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLabStatus() {
        return labStatus;
    }

    public void setLabStatus(String labStatus) {
        this.labStatus = labStatus;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public Class getClass1() {
        return class1;
    }

    public void setClass1(Class class1) {
        this.class1 = class1;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timeTableID != null ? timeTableID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timetable)) {
            return false;
        }
        Timetable other = (Timetable) object;
        if ((this.timeTableID == null && other.timeTableID != null) || (this.timeTableID != null && !this.timeTableID.equals(other.timeTableID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Timetable[timeTableID=" + timeTableID + "]";
    }

}
