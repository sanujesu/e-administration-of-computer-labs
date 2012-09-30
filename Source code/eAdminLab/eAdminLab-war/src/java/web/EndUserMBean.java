/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.EndUserSBean;
import entity.Enduser;
import entity.Role;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import utilities.EConstant;
import utilities.ManagerSession;
import utilities.FileUtil;

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
    private Enduser newUser = new Enduser();
    private Enduser currentUser = new Enduser();
    private Enduser tempLoginUser = new Enduser();

    /** Creates a new instance of EndUserMBean */
    public EndUserMBean() {
    }

    @PostConstruct
    public void init() {
        String userName=getCurrentUserName();
        currentUser = this.getUserByName(userName);

        userName = EConstant.E_REMINDER_USER_NAME;
        password = EConstant.E_REMINDER_PASSWORD;
        address = EConstant.E_REMINDER_ADDRESS;
        email = EConstant.E_REMINDER_EMAIL;
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

    public Enduser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Enduser currentUser) {
        this.currentUser = currentUser;
    }

    public void clickRegister(ActionEvent event) {
        int number=this.endUserSBean.getAll().size()+1;
        newUser.setUserID(String.valueOf( number));
        newUser.setRole(new Role(EConstant.E_DEFAULT_ROLE));
        this.endUserSBean.insert(newUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INFOR", "Register succesfull!"));
    }   

    public Enduser getEnduser() {
        return tempLoginUser;
    }

    public void setEnduser(Enduser enduser) {
        this.tempLoginUser = enduser;
    }

    public String login() {
        String redirectLogin = "/eProjectDemo-war/faces/index.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        if (userName.length() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "User Not null .");
        } else if (password.length() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Password Not null .");
        } else {
            this.tempLoginUser = this.endUserSBean.loginSystem(userName, password);//MD5.getMD5(password)
            if (this.tempLoginUser != null) {
                //return redirectLogin = "/Status?faces-redirect=true";
                if (tempLoginUser.getRole().getRoleID() == 1) {
                    loggedIn = true;
                    //ManagedSession.assignSession("user",this.tempLoginUser.getUserName());
                    //ManagedSession.assignSession("role",this.tempLoginUser.getRoleID());
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", tempLoginUser.getUserName() + ":" + tempLoginUser.getRole().getRoleID().toString());
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/eAdminLab-war/faces/adminpage/ManageSchedule.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(ComplaintMBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //redirectLogin = "/eProjectDemo-war/faces/adminpage/ManageSchedule.xhtml?faces-redirect=true";
                } else if (tempLoginUser.getRole().getRoleID() == 2) {
                    loggedIn = false;
                    ManagerSession.assignSession("user", this.tempLoginUser.getUserName());
                    ManagerSession.assignSession("role", this.tempLoginUser.getRole().getRoleID());
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", tempLoginUser.getUserName());
                    //redirectLogin = "/eProjectDemo-war/faces/user/instructorDemo.xhtml";
                } else if (tempLoginUser.getRole().getRoleID() == 3) {
                    loggedIn = true;
                    ManagerSession.assignSession("user", this.tempLoginUser.getUserName());
                    ManagerSession.assignSession("role", this.tempLoginUser.getRole().getRoleID());
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", tempLoginUser.getUserName());
                    //  redirectLogin = "/eProjectDemo-war/faces/user/technicalDemo.xhtml";
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "User or Password Error");
                this.tempLoginUser = null;
            }
        }
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        //context.addCallbackParam("loggedIn", loggedIn);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect(redirectLogin);
        } catch (IOException ex) {
            Logger.getLogger(EndUserMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileUtil.storeUser(this.tempLoginUser.getUserName());
        return redirectLogin;
    }

    public String logout() {
        ManagerSession.deleteSession("user");
        ManagerSession.deleteSession("role");
        this.tempLoginUser = null;
        return "/index.xhtml?faces-redirect=true";
    }

    public String registry() {
        this.tempLoginUser = null;
        return "/user/Registration.xhtml?faces-redirect=true";
    }
    public Enduser getUserByName(String userName){
        return this.endUserSBean.getUserByName(userName);
    }

    private String getCurrentUserName() {
        return FileUtil.getCurrentUser();
    }
}
