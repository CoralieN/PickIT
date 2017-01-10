package com.example.co.pickit_app;

import java.util.ArrayList;

/**
 * Created by Co on 09/01/2017.
 */

public class Data_list_list {

    ArrayList<Data_list> data_list_list = new ArrayList<Data_list>();
         /*this.Data_list_list.add("Work");
        this.Data_list_list.add("Trip to La Reunion");
        this.Data_list_list.add("Weekend in Montpellier");
        this.Data_list_list.add("Baby");*/

    //Add an object to the list of all the list
    public void add_list(Data_list val){
        this.data_list_list.add(val);
    }

    //Get the list of all all list
    public ArrayList<Data_list> getList(){
        return this.data_list_list;
    }

    //Get the Name of the list n°position
    private String getName (int position){
        return this.data_list_list.get(position).getName();
    }

    //Get the State of the list n°position
    private boolean getState (int position){
        return this.data_list_list.get(position).getState();
    }

    //Get the Object registered in the list n°position
    private ArrayList<String> getObj (int position){
        return this.data_list_list.get(position).getData_obj_of_list();
    }
    /*
    public void remove_obj(Data_list obj){ // A revoir  recherche par nom !!

        int position = 1;
        boolean find = false;

        while (!find){
            if (this.data_list_list.get(position) == obj){
                find = true;}
            else {
                position++;
            }
        }

        this.data_list_list.remove(position);
    }
    */
}