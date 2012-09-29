/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import ejb.EndUserSBean;
import entity.Enduser;
import entity.Role;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Pakacoco
 */
@ManagedBean(name = "endUserController")
@RequestScoped
public class EndUserMBean {
    @EJB
    private EndUserSBean endUserSBean;
    private String userName, password, address, email;
    private Date birthDay;
    private Enduser newUser=new Enduser();

    /** Creates a new instance of EndUserMBean */
    public EndUserMBean() {
    }
    @PostConstruct
    public void init() {
        userName = "Enter user name";
        password = "Enter password";
        address = "Enter address";
        email   = "Enter email";
        birthDay = new Date();
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        newUser.setAddress(address);
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
        newUser.setBirthday(birthDay);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        newUser.setEmail(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        newUser.setPassword(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        newUser.setUserName(userName);
    }

    public Enduser getNewUser() {
        return newUser;
    }

    public void setNewUser(Enduser newUser) {
        this.newUser = newUser;
    }
    
    public void clickRegister(ActionEvent event){
        newUser.setUserID("006");
        newUser.setRole(new Role(Short.parseShort("1")));
        this.endUserSBean.insert(newUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INFOR","Register succesfull!"));
    }
    public void abc(ActionEvent e){
        int a=2;
        
        a++;
    }

}
