package com.example.co.pickit_app;

import android.app.Application;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Co on 30/12/2016.
 */

public class Data extends Application {

    private static ArrayList<String> Data_list_obj = new ArrayList<String>();

    public Data() {

       /* this.Data_list_obj.add("Pepito");
        this.Data_list_obj.add("Power Supply");
        this.Data_list_obj.add("Teddy Bear  <3");
        this.Data_list_obj.add("Keyboard");
        this.Data_list_obj.add("Color contacts");
        this.Data_list_obj.add("Wooden pencil");
        this.Data_list_obj.add("Chocolate");
        this.Data_list_obj.add("WSN paper");
        this.Data_list_obj.add("Tisseo Card");
        this.Data_list_obj.add("ID");
        this.Data_list_obj.add("Wallet");
        this.Data_list_obj.add("USRP");
        this.Data_list_obj.add("Headphones");*/
    }
    public void add_list(String val){
        this.Data_list_obj.add(val);
    }

    public ArrayList<String> getList(){
        return this.Data_list_obj;
    }

    public void remove_obj(String obj) {
        int position = 1;
        boolean find = false;

        while (!find) {
            if (this.Data_list_obj.get(position) == obj) {
                find = true;
            } else {
                position++;
            }
        }

        this.Data_list_obj.remove(position);
    };
    // delete obj from list
}
