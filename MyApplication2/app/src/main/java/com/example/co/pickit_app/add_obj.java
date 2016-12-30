package com.example.co.pickit_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class add_obj extends AppCompatActivity {
    private Button scan = null;
    private ImageButton validate = null;
    Data data = new Data();
    ArrayList<String> data_obj = data.Data_list_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_obj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scan = (Button) findViewById(R.id.scanbutton);
        validate =(ImageButton) findViewById(R.id.validate_new_obj);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Open the Scanner ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            EditText name = (EditText) findViewById(R.id.fillnameobj);

            @Override
            public void onClick(View view) {
                data_obj.add(name.getText().toString());
                Intent intent = new Intent(add_obj.this, MyObj.class);
                startActivity(intent);
            }
        });
    }

}
