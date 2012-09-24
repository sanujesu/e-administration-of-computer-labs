/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NOAH
 */
@Entity
@Table(name = "CLASS", catalog = "eAdministration", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c"),
    @NamedQuery(name = "Class.findByClassID", query = "SELECT c FROM Class c WHERE c.classID = :classID"),
    @NamedQuery(name = "Class.findByClassName", query = "SELECT c FROM Class c WHERE c.className = :className"),
    @NamedQuery(name = "Class.findByAmountTime", query = "SELECT c FROM Class c WHERE c.amountTime = :amountTime")})
public class Class implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "classID", nullable = false, length = 15)
    private String classID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "className", nullable = false, length = 100)
    private String className;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amountTime", nullable = false)
    private double amountTime;
    @JoinColumn(name = "departmentID", referencedColumnName = "departmentID", nullable = false)
    @ManyToOne(optional = false)
    private Department department;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "class1")
    private List<Timetable> timetableList;

    public Class() {
    }

    public Class(String classID) {
        this.classID = classID;
    }

    public Class(String classID, String className, double amountTime) {
        this.classID = classID;
        this.className = className;
        this.amountTime = amountTime;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getAmountTime() {
        return amountTime;
    }

    public void setAmountTime(double amountTime) {
        this.amountTime = amountTime;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @XmlTransient
    public List<Timetable> getTimetableList() {
        return timetableList;
    }

    public void setTimetableList(List<Timetable> timetableList) {
        this.timetableList = timetableList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classID != null ? classID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if ((this.classID == null && other.classID != null) || (this.classID != null && !this.classID.equals(other.classID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Class[ classID=" + classID + " ]";
    }
    
}
