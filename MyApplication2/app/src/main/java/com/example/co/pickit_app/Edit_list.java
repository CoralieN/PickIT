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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Edit_list extends AppCompatActivity {

    Data data = new Data();
    Data_list_list data_list_list = new Data_list_list();
    ArrayList<Data_list> data_list= data_list_list.getList_List();
    public Data_list currentlist = null;
    ListView myList;
    final String EXTRA_NAME = "name_error";
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        final Switch state = (Switch) findViewById(R.id.edit_switchlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // To get the Extra
        Intent intent = getIntent();

        // If validate back to My list
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_valid_list);
        TextView List_name = (TextView) findViewById(R.id.edit_list_name);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_list.this, MyList.class);
                data_list.get(i).setState(state.isChecked());
                startActivity(intent);
            }
        });

        if (intent != null) {
            List_name.setText(intent.getStringExtra(EXTRA_NAME));
            currentlist = findList(intent.getStringExtra(EXTRA_NAME), data_list);
        }

        state.setChecked(currentlist.getState());

        //Generate list View from ArrayList
        myList = (ListView) findViewById(R.id.listview_obj_in_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Edit_list.this,
                android.R.layout.simple_list_item_1, currentlist.getData_obj_of_list());
        myList.setAdapter(adapter);
    }

    Data_list findList(String name, ArrayList<Data_list> MyList){
        for (i = 0; i<MyList.size(); i++){
            Log.d("Name test", name);
            Log.d("Name", MyList.get(i).getName());
            if (MyList.get(i).getName().equals(name)){
                Log.d("Trouvé",MyList.get(i).getName());
                return MyList.get(i);
            }
        }
        Log.d("Faileeeed","Error" );
        return null;
    }
}



