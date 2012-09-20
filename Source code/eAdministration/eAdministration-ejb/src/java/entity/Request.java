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
    @NamedQuery(name = "Request.findByDescription", query = "SELECT r FROM Request r WHERE r.description = :description")})
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
    @JoinColumn(name = "requestor", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "timeTableID", referencedColumnName = "timeTableID", nullable = false)
    @ManyToOne(optional = false)
    private Timetable timetable;
    @JoinColumn(name = "statusID", referencedColumnName = "statusID", nullable = false)
    @ManyToOne(optional = false)
    private Status status;

    public Request() {
    }

    public Request(String requestID) {
        this.requestID = requestID;
    }

    public Request(String requestID, String instructor, String title, String description) {
        this.requestID = requestID;
        this.instructor = instructor;
        this.title = title;
        this.description = description;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
