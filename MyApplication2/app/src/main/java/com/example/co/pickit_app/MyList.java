package com.example.co.pickit_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class MyList extends AppCompatActivity {

    ListView mListView;
    String[] list_name = new String[]{
            "Baby", "Work","Granny", "Picnic", "Grocery", "Trip to La Réunion",
            "Let's go to Montpelier", "Crayon bois et more","Cleaning in Paris",
            "Project food","Yoni :)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_list);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mListView = (ListView) findViewById(R.id.List_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyList.this,
                android.R.layout.simple_expandable_list_item_1, list_name);
        mListView.setAdapter(adapter);
    }
}
