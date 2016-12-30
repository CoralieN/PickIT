package com.example.co.pickit_app;

import android.app.Application;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Co on 30/12/2016.
 */

public class Data extends Application{

    public ArrayList<String> Data_list_obj = new ArrayList<String>();

    public ArrayList<String> getList_obj() {

        Data_list_obj.add("Pepito");
        Data_list_obj.add("Power Supply");
        Data_list_obj.add("Teddy Bear  <3");
        Data_list_obj.add("Keyboard");
        Data_list_obj.add("Color contacts");
        Data_list_obj.add("Wooden pencil");
        Data_list_obj.add("Chocolate");
        Data_list_obj.add("WSN paper");
        Data_list_obj.add("Tisseo Card");
        Data_list_obj.add("ID");
        Data_list_obj.add("Wallet");
        Data_list_obj.add("USRP");
        Data_list_obj.add("Headphones");

        return Data_list_obj;
    }

    /*public void setList_obj(ArrayList<String> aList_obj) {
        Data_list_obj = aList_obj;
    }

    //add object to list
    public void addObj_to_List(String addObj){
        Data_list_obj.add(addObj);
    }*/

    // delete obj from list
}
