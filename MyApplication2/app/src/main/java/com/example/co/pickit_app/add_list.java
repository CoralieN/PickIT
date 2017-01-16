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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.logging.StreamHandler;

public class add_list extends AppCompatActivity {
    public ImageButton validate_list = null;
    public EditText name_text = null;
    MyCustomAdapter dataAdapter = null;
    Data data = new Data();
    ArrayList<String> data_obj = data.getList(); // List of all the object - To be display for selection
    ArrayList<String> list_obj = new ArrayList<>(); // Content all the selected objects
    Data_list new_list = new Data_list(); // List to be added to our list of list
    Data_list_list Big_list = new Data_list_list();
    ArrayList<Data_list> ListOfList = Big_list.getList_List();
    ArrayList<Object> ObjectList = new ArrayList<Object>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_addlist);
        setSupportActionBar(toolbar);

        //Validate and go back to My lists

        name_text = (EditText) findViewById(R.id.edit_new_list_name);
        validate_list = (ImageButton) findViewById(R.id.valide_new_list);
        validate_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the name of the new list
                new_list.setName(name_text.getText().toString());
                Log.d("Nom", name_text.getText().toString());
                // Set State to false by default
                new_list.setState(true);
                Log.d("SetState","false");
                new_list.setData_obj_of_list(list_obj);
                ListOfList.add(new_list);
                //Log.d("OBJFINAL",list_obj.get(1));
                Intent intent = new Intent(add_list.this, MyList.class);
                startActivity(intent);
            }
        }
        );

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(add_list.this, MyList.class);
                    startActivity(intent);;
                }
            });
        }
        //Generate list View from ArrayList
        displayListView();
        //checkButtonClick();
    }

    private void displayListView() {

        //Array list of object: Get from DB or memory
        //ArrayList<Object> ObjectList = new ArrayList<Object>();
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
                Log.d("Oncheck", "if");

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Object Object = (Object) cb.getTag();
                        if (cb.isChecked()) {
                            Log.d("Addobject", "I add an object ");
                            list_obj.add(Object.getName());
                        }

                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        Object.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                Log.d("Oncheck", "Else");
                holder = (ViewHolder) convertView.getTag();
            }

            Object Object = ObjectList.get(position);
            holder.name.setText(Object.getName());
            holder.name.setChecked(Object.isSelected());
            holder.name.setTag(Object);

            return convertView;
        }
    }

 /*   private void checkButtonClick() {


        ImageButton myButton = (ImageButton) findViewById(R.id.valide_new_list);
        myButton.setOnClickListener(new View.OnClickListener());

            @Override
            public void onClick(View view) {

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

