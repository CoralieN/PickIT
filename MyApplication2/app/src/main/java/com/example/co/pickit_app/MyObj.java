package com.example.co.pickit_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyObj extends AppCompatActivity {

    ListView mListView;
    String[] list_obj = new String[]{
            "Pepito", "Chocolate","Keyboard", "More Pepito", "Screen", "Food",
            "Color lens", "Wooden pencil","WSN paper",
            "Pepito again","Yoni :)"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_obj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mListView = (ListView) findViewById(R.id.List_ob);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyObj.this,
                android.R.layout.simple_expandable_list_item_1, list_obj);
        mListView.setAdapter(adapter);
    }

}
