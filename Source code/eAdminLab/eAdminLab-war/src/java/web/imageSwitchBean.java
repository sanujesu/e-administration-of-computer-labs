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
public class ImageSwitchBean {

    private List<String> images;

    public ImageSwitchBean() {
        images = new ArrayList<String>();
        images.add("op5.png");
        images.add("op6.png");
        images.add("op7.png");
        images.add("op8.png");
    }

    public List<String> getImages() {
        return images;
    }
}
