package com.example.co.pickit_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Edit_list extends AppCompatActivity {

    Data data = new Data();
    ArrayList<String> data_obj = data.getList();
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // If validate back to My list
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_valid_list);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_list.this, MyList.class);
                startActivity(intent);
            }
        });

        //Generate list View from ArrayList
        myList = (ListView) findViewById(R.id.listview_obj_in_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Edit_list.this,
                android.R.layout.simple_list_item_1, data_obj);
        myList.setAdapter(adapter);

    }
}



