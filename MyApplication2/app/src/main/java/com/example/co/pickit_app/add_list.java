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

public class add_list extends AppCompatActivity {
    public ImageButton validate_list = null;
    MyCustomAdapter dataAdapter = null;
    Data data = new Data();
    ArrayList<String> data_obj = data.getList_obj();

    // We just copy; not really share the data !!!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Validate and go back to My lists
        validate_list = (ImageButton) findViewById(R.id.valide_new_list);
        validate_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(add_list.this, MyList.class);
                startActivity(intent);
            }
        });

        //Generate list View from ArrayList
        displayListView();

        //checkButtonClick();

    }

    private void displayListView() {

        //Array list of object: Get from DB or memory
        ArrayList<Object> ObjectList = new ArrayList<Object>();
        for (String list : data_obj)
        {
            Object object = new Object(list,false);
            ObjectList.add(object);
        }

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.list_info, ObjectList);
        ListView listView = (ListView) findViewById(R.id.listview_object);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Object Object = (Object) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + Object.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Object> {

        private ArrayList<Object> ObjectList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Object> ObjectList) {
            super(context, textViewResourceId, ObjectList);
            this.ObjectList = new ArrayList<Object>();
            this.ObjectList.addAll(ObjectList);
        }

        private class ViewHolder {
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_info, null);

                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.cBox_list);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Object Object = (Object) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        Object.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Object Object = ObjectList.get(position);
            holder.name.setText(Object.getName());
            holder.name.setChecked(Object.isSelected());
            holder.name.setTag(Object);

            return convertView;

        }

    }

    /*private void checkButtonClick() {


        ImageButton myButton = (ImageButton) findViewById(R.id.valide_new_list);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Object> ObjectList = dataAdapter.ObjectList;
                for(int i=0;i<ObjectList.size();i++){
                    Object Object = ObjectList.get(i);
                    if(Object.isSelected()){
                        responseText.append("\n" + Object.getName());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }*/

}

