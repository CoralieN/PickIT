package com.example.co.pickit_app;

import java.util.ArrayList;

/**
 * Created by Co on 09/01/2017.*
 */

//A List is compose by its name, state (Enabled/disabled) and the list of its objects
public class Data_list {

    String name;
    Boolean state;
    ArrayList<String> data_obj_of_list = new ArrayList<>();

    public Data_list() {
    }

            //GET
    public String getName() {
        return name;
    }

    public Boolean getState() {
        return state;
    }

    public ArrayList<String> getData_obj_of_list(){
        return data_obj_of_list;
    }

            //SET
    public void setState(Boolean state){
        this.state = state;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setData_obj_of_list(ArrayList<String> data_obj_of_list) {
        this.data_obj_of_list = data_obj_of_list;
    }
}
