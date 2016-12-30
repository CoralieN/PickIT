package com.example.co.pickit_app;

/**
 * Created by Co on 30/12/2016.
 */

public class Object {

    String name = null;
    boolean selected = false;

    public Object(String name, boolean selected) {
        super();
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
