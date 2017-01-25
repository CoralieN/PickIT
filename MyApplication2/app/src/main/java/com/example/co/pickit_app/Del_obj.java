package com.example.co.pickit_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class Del_obj extends AppCompatActivity {

    Data data = new Data();
    ArrayList<String> Data_obj = data.getList();
    String name ="to_change";
    final String EXTRA_NAME = "name_error";

    private final String NAMESPACE = "http://docs.insa.fr/";
    private final String URL = "http://192.168.43.191:8080/Localhost_official/Localhost3306Service?WSDL";


    private final String SOAP_ACTION2 = "http://docs.insa.fr/delete_object";
    private final String METHOD_NAME2 = "delete_object";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_obj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_del);
//        setSupportActionBar(toolbar);

        Button yes = (Button) findViewById(R.id.del_yes);
        Button no = (Button) findViewById(R.id.del_no);
        TextView text_name = (TextView) findViewById(R.id.del_name);

        //Get the Extra to have the name
        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra(EXTRA_NAME);
        }

        text_name.setText(name);

        //Yes: remove an back to my_Obj
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                boolean find = false;

                while (!find && position<Data_obj.size()) {
                    if ((Data_obj.get(position)).equals(name)) {
                        find = true;
                    } else {
                        position++;
                    }
                }
                if (find){
                    Data_obj.remove(position);
                }


                Thread networkThread = new Thread() {
                    @Override
                    public void run() {

                        try {

                            System.out.println("coucou2");
                            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);

                            System.out.println("coucou3");
                            TextView text_name = (TextView) findViewById(R.id.del_name);
                            request.addProperty("arg0", text_name.getText().toString());
                            System.out.println("le nom de la liste : !!!! " + text_name.getText().toString());


                            System.out.println("coucou6");


                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.setOutputSoapObject(request);
                            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                            androidHttpTransport.debug = true;

                            System.out.println("coucou8");
                            androidHttpTransport.call(SOAP_ACTION2, envelope);

                            System.out.println("coucou9");
                            final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                            System.out.println("coucou10");


                            runOnUiThread(new Runnable() {
                                public void run() {

                                    System.out.println("coucoufinal");
                                    System.out.println(response.toString());
                                }
                            });

                        } catch (Exception e) {

                            System.out.println("nous sommes dans l'exception " + e.getMessage());
                        }
                    }
                };
                networkThread.start();

                Intent intent = new Intent(Del_obj.this, MyObj.class);
                startActivity(intent);

            }
        });

        //No: back to my_Obj
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Del_obj.this, MyObj.class);
                startActivity(intent);
            }
        });

    }
}
