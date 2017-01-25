package com.example.co.pickit_app;

import java.util.ArrayList;

/**
 * Created by Co on 09/01/2017.*
 */

//A List is compose by its name, state (Enabled/disabled) and the list of its objects
public class Data_list {

    public String name; //nom de la liste
    public Boolean state; //etat de la liste
    public ArrayList<String> data_obj_of_list = new ArrayList<String>(); //liste des objects

    public Data_list() {
    }

    public Data_list(String name, Boolean state) {
        this.name=name;
        this.state=state;
    }

            //GET
    public String getName() {
        return  this.name;
    }

    public Boolean getState() {
        return this.state;
    }

    public ArrayList<String> getData_obj_of_list(){
        return this.data_obj_of_list;
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
