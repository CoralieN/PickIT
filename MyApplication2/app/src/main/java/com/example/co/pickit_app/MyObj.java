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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;

public class MyObj extends AppCompatActivity {

    ListView mListView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_obj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_myobj);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab_add_obj);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyObj.this, add_obj.class);
                startActivity(intent);
            }
        });

        final RecyclerView rv = (RecyclerView) findViewById(R.id.list_obj);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new obj_adapter());

        getSupportActionBar().setHomeButtonEnabled(true);

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyObj.this, Accueil.class);
                    startActivity(intent);;
                }
            });
        }
    }
}
