package web;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author dell
 */
@ManagedBean(name="imageSwitchBean")
public class imageSwitchBean {

    private List<String> images;

    public imageSwitchBean() {
        images = new ArrayList<String>();
        images.add("op5.png");
        images.add("op6.png");
        images.add("op7.png");
        images.add("op8.png");
        //images.add("op9.png");
    }

    public List<String> getImages() {
        return images;
    }
}
