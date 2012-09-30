/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pakacoco
 */
public class ManagerSession {


    public static void assignSession(String name, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.setAttribute(name, value);
    }
      public static Object getSession(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session.getAttribute(name);
    }

    public static void deleteSession(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute(name);
    }

}
