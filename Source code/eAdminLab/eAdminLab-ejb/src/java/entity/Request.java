/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Pakacoco
 */
@Entity
@Table(name = "REQUEST", catalog = "eAdministration", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r"),
    @NamedQuery(name = "Request.findByRequestID", query = "SELECT r FROM Request r WHERE r.requestID = :requestID"),
    @NamedQuery(name = "Request.findByInstructor", query = "SELECT r FROM Request r WHERE r.instructor = :instructor"),
    @NamedQuery(name = "Request.findByTitle", query = "SELECT r FROM Request r WHERE r.title = :title"),
    @NamedQuery(name = "Request.findByDescription", query = "SELECT r FROM Request r WHERE r.description = :description"),
    @NamedQuery(name = "Request.findByStatusID", query = "SELECT r FROM Request r WHERE r.statusID = :statusID")})
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "requestID", nullable = false, length = 15)
    private String requestID;
    @Basic(optional = false)
    @Column(name = "instructor", nullable = false, length = 15)
    private String instructor;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 1073741823)
    private String description;
    @Basic(optional = false)
    @Column(name = "statusID", nullable = false)
    private boolean statusID;
    @JoinColumn(name = "timeTableID", referencedColumnName = "timeTableID", nullable = false)
    @ManyToOne(optional = false)
    private Timetable timetable;
    @JoinColumn(name = "requestor", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private Enduser enduser;

    public Request() {
    }

    public Request(String requestID) {
        this.requestID = requestID;
    }

    public Request(String requestID, String instructor, String title, String description, boolean statusID) {
        this.requestID = requestID;
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.statusID = statusID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatusID() {
        return statusID;
    }

    public void setStatusID(boolean statusID) {
        this.statusID = statusID;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Enduser getEnduser() {
        return enduser;
    }

    public void setEnduser(Enduser enduser) {
        this.enduser = enduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestID != null ? requestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.requestID == null && other.requestID != null) || (this.requestID != null && !this.requestID.equals(other.requestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Request[requestID=" + requestID + "]";
    }

}
