/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.RequestSBean;
import entity.Request;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ngoc.phan
 */
@ManagedBean
@SessionScoped
public class RequestMBean {
    @EJB
    private RequestSBean requestSBean;

    /**
     * Creates a new instance of RequestMBean
     */
    public RequestMBean() {
    }
    public List<Request> getAllReq(){
        return requestSBean.listAll();
    }
}
