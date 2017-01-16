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
