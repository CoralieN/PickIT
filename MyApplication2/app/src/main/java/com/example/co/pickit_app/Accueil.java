package com.example.co.pickit_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    Data_list_list data_list_list = new Data_list_list();
    ArrayList<Data_list> data_list = data_list_list.getList_List();
    public ArrayList<String> currentlist = new ArrayList<>();
    ListView myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Generate list View from ArrayList
        currentlist = getEnabledName(data_list);

        myList = (ListView) findViewById(R.id.Enabled_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Accueil.this,
                android.R.layout.simple_list_item_1, currentlist);
        myList.setAdapter(adapter);
            }
    // Get the list of all Enabled List
    ArrayList<String> getEnabledName(ArrayList<Data_list> list){
        ArrayList<String> Enabled_list= new ArrayList<>();
        for (Data_list mylist : list){
            if (mylist.getState()){
                Enabled_list.add(mylist.getName());
            }
        }
        return Enabled_list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_list) {
            Intent intent = new Intent(Accueil.this, MyList.class);
            startActivity(intent);
            return true;
        }

       if (id == R.id.action_object) {
            Intent intent = new Intent(Accueil.this, MyObj.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_about) {
            Intent intent = new Intent(Accueil.this, About.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_basestation) {
            Intent intent = new Intent(Accueil.this, MyBS.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_account) {
            Intent intent = new Intent(Accueil.this, Account.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
