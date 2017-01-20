package com.example.co.pickit_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class Edit_list extends AppCompatActivity {

    Data data = new Data();
    Data_list_list data_list_list = new Data_list_list();
    ArrayList<Data_list> data_list= data_list_list.getList_List();
    public Data_list currentlist = null;
    ListView myList;
    final String EXTRA_NAME = "name_error";
    int i=0;

    private final String NAMESPACE = "http://docs.insa.fr/";
    private final String URL = "http://192.168.43.191:8080/Localhost_official/Localhost3306Service?WSDL";
    private final String SOAP_ACTION = "http://docs.insa.fr/activate_list";
    private final String METHOD_NAME = "activate_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        final Switch state = (Switch) findViewById(R.id.edit_switchlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_editlist);
        setSupportActionBar(toolbar);

        // To get the Extra
        Intent intent = getIntent();

        // If validate back to My list
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_valid_list);
        final TextView List_name = (TextView) findViewById(R.id.edit_list_name);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_list.this, MyList.class);
                data_list.get(i).setState(state.isChecked());
                startActivity(intent);
                System.out.println("Coucou j'ai activé la liste!!");


                Thread networkThread = new Thread() {
                    @Override
                    public void run() {

                        try {

                            System.out.println("coucou2");
                            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                            System.out.println("coucou3");

                            //problème ici : Je ne récupère pas le nom de la liste!!!
                            request.addProperty("arg0", List_name.toString());
                            System.out.println("le nom de la liste : !!!! "+ List_name.toString());

                            System.out.println("coucou6");


                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.setOutputSoapObject(request);
                            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                            androidHttpTransport.debug = true;

                            System.out.println("coucou8");
                            androidHttpTransport.call(SOAP_ACTION, envelope);

                            System.out.println("coucou9");
                            final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                            System.out.println("coucou10");


                            runOnUiThread(new Runnable() {
                                public void run() {

                                    System.out.println(response.toString());
                                }
                            });

                        } catch (Exception e) {

                            System.out.println("nous sommes dans l'exception " + e.getMessage());
                        }
                    }
                };
                networkThread.start();




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

        getSupportActionBar().setHomeButtonEnabled(true);

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Edit_list.this, MyList.class);
                    startActivity(intent);;
                }
            });
        }
    }

    // Get the list corresponding to the given name
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



