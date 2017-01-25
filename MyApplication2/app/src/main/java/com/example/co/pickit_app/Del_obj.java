package com.example.co.pickit_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Del_obj extends AppCompatActivity {

    Data data = new Data();
    ArrayList<String> Data_obj = data.getList();
    String name ="to_change";
    final String EXTRA_NAME = "name_error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_obj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button yes = (Button) findViewById(R.id.del_yes);
        Button no = (Button) findViewById(R.id.del_no);
        TextView text_name = (TextView) findViewById(R.id.del_name);

        //Get the Extra to have the name
        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra(EXTRA_NAME);
        }

        text_name.setText(name);

        //Yes: remove an back to my_Obj
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                boolean find = false;

                while (!find && position<Data_obj.size()) {
                    if ((Data_obj.get(position)).equals(name)) {
                        find = true;
                    } else {
                        position++;
                    }
                }
                if (find){
                    Data_obj.remove(position);
                }
                Intent intent = new Intent(Del_obj.this, MyObj.class);
                startActivity(intent);

            }
        });

        //No: back to my_Obj
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Del_obj.this, MyObj.class);
                startActivity(intent);
            }
        });

    }
}
