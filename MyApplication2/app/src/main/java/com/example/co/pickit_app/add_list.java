package com.example.co.pickit_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

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

    private static int loop=0;

    private final String NAMESPACE = "http://docs.insa.fr/";
    private final String URL = "http://192.168.43.191:8080/Localhost_official/Localhost3306Service?WSDL";
    private final String SOAP_ACTION = "http://docs.insa.fr/connect";
    private final String METHOD_NAME = "add_list";

    private final String METHOD_NAME2 = "add_obj_in_list";
    private final String SOAP_ACTION2 = "http://docs.insa.fr/add_obj_in_list";

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

                // 1er thread pour creer une nouvelle liste
                Thread networkThread = new Thread() {
                    @Override
                    public void run() {

                        try {

                            // Set the name of the new list
                            new_list.setName(name_text.getText().toString());
                            Log.d("Nom", name_text.getText().toString());
                            // Set State to false by default
                            new_list.setState(true);
                            Log.d("SetState","false");
                            new_list.setData_obj_of_list(list_obj);
                            ListOfList.add(new_list);

                            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                            String Name = name_text.getText().toString(); //retreive le texte tapé sur l'appli
                            request.addProperty("arg0", Name);
                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.setOutputSoapObject(request);
                            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                            androidHttpTransport.debug = true;


                            androidHttpTransport.call(SOAP_ACTION, envelope);
                            final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                            runOnUiThread(new Runnable() {
                                public void run() {
                                   // TextView result = (TextView) findViewById(R.id.tv_status);

                                   // System.out.println("coucou11");

                                    //result.setText(response.toString());
                                    System.out.println("coucoufinal 1 !!: "+ response.toString());
                                }
                            });

                        } catch (Exception e) {

                            System.out.println("nous sommes dans l'exception " + e.getMessage());
                        }
                    }
                };
                networkThread.start();


                //deuxième thread pour ajouter des objets dans la liste
                Thread networkThread2 = new Thread() {
                    @Override
                    public void run() {




                            // Set the name of the new list
                            new_list.setName(name_text.getText().toString());
                            Log.d("Nom", name_text.getText().toString());
                            // Set State to false by default
                            new_list.setState(true);
                            Log.d("SetState","false");
                            new_list.setData_obj_of_list(list_obj);
                            ListOfList.add(new_list);

                        for(loop=0; loop<list_obj.size();loop++) {

                            try {
                                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);

                                System.out.println("coucou1");

                                request.addProperty("arg0", list_obj.get(loop));
                                String Name = name_text.getText().toString(); //retreive le texte tapé sur l'appli
                                request.addProperty("arg1", Name);


                                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                                envelope.setOutputSoapObject(request);
                                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                                androidHttpTransport.debug = true;

                                System.out.println("coucou3");

                                androidHttpTransport.call(SOAP_ACTION2, envelope);
                                final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                                runOnUiThread(new Runnable() {
                                    public void run() {

                                        System.out.println("coucoufinal 2 !!: " + response.toString());

                                    }
                                });

                            } catch (Exception e) {

                                System.out.println("nous sommes dans l'exception " + e.getMessage());
                            }
                        }
                    }
                };

              // for(loop=0; loop<list_obj.size();loop++)
                networkThread2.start();
                /*try {
                    networkThread2.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("nous sommes dans l'exception" + e.getMessage());
                }
                networkThread2.stop();*/

                //Log.d("OBJFINAL",list_obj.get(1));
                Intent intent = new Intent(add_list.this, MyList.class);
                startActivity(intent);

            }
        });



           /* @Override
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
        );*/
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

