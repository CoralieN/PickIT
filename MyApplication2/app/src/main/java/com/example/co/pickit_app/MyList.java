package com.example.co.pickit_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;


public class MyList extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mylist);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.add_list);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyList.this, add_list.class);
                startActivity(intent);
            }
        });

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyList.this, Accueil.class);
                    startActivity(intent);;
                }
            });
        }

        final RecyclerView rv = (RecyclerView) findViewById(R.id.list_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new list_adapter());
    }
}

