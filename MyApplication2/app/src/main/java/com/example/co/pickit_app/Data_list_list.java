package com.example.co.pickit_app;

import java.util.ArrayList;

/**
 * Created by Co on 09/01/2017.
 */

public class Data_list_list {

   public static ArrayList<Data_list> data_list_list = new ArrayList<Data_list>();

    //Add an object to the list of all the list

    public static void add_list(Data_list val){data_list_list.add(val);
    }

    //Get the list of all all list
    public static ArrayList<Data_list> getList_List(){
      /*  Data_list new_obj = new Data_list();
        ArrayList<String> obj = new ArrayList<>();
        obj.add("papa");
        obj.add("fegrg");
        obj.add("ataert");
        obj.add("garhreh");
        new_obj.setName("Work");
        new_obj.setState(false);
        new_obj.setData_obj_of_list(obj);
        this.data_list_list.add(new_obj);*/
        return data_list_list;
    }

    //Get the big list
    //Get the Name of the list n°position
    public String getName (int position){
        return this.data_list_list.get(position).getName();
    }

    //Get the State of the list n°position
    public boolean getState (int position){
        return this.data_list_list.get(position).getState();
    }

    //Get a list out of all the list
    public Data_list getList(int position){
        return this.data_list_list.get(position);
    }

    //Get the Object registered in the list n°position
    public ArrayList<String> getObj (int position){
        return this.data_list_list.get(position).getData_obj_of_list();
    }

    public int getCount(){
        return this.data_list_list.size();
    }

    public void addNewList(Data_list nouveau){
        this.data_list_list.add(nouveau);
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