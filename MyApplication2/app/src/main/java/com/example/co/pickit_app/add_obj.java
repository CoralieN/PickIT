package com.example.co.pickit_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class add_obj extends AppCompatActivity {

    private final String NAMESPACE = "http://docs.insa.fr/";
    private final String URL = "http://192.168.43.191:8080/Localhost_official/Localhost3306Service?WSDL";
    private final String SOAP_ACTION = "http://docs.insa.fr/connect";
    private final String METHOD_NAME = "add_obj";

    private Button scan = null;
    private ImageButton validate = null;
    Data data = new Data();
    ArrayList<String> data_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_obj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_addObj);
        setSupportActionBar(toolbar);

        //scan = (Button) findViewById(R.id.scanbutton);
        validate =(ImageButton) findViewById(R.id.validate_new_obj);
       /* scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Open the Scanner ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        //Completer ce clickListener pour ajouter un objet dans la table objects
        // Completer pour récupérer le serial number entré par l'utilisateur et le passer en argument de la fonction
        validate.setOnClickListener(new View.OnClickListener() {

            EditText name = (EditText) findViewById(R.id.fillnameobj);
            EditText serial_number = (EditText) findViewById(R.id.serial_number);

            @Override
            public void onClick(View view) {
                data.add_list(name.getText().toString());

                Thread networkThread = new Thread() {
                    @Override
                    public void run() {

                        try {

                            System.out.println("coucou2");
                            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                            System.out.println("coucou3");

                            String Name = name.getText().toString(); //retreive le texte tapé sur l'appli
                            System.out.println("obj_name !!!! " + Name);
                            String Serial_number = serial_number.getText().toString();



                            request.addProperty("arg0", Name);
                            request.addProperty("arg1", Serial_number);


                            System.out.println("coucou6");


                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.setOutputSoapObject(request);
                            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                            androidHttpTransport.debug = true;

                            System.out.println("coucou8");
                            androidHttpTransport.call(SOAP_ACTION, envelope);

                            System.out.println("coucou9");
                            final SoapPrimitive response = (SoapPrimitive)envelope.getResponse();

                            System.out.println("coucou10");



                            runOnUiThread (new Runnable(){
                                public void run() {
                                    TextView result = (TextView) findViewById(R.id.tv_status);

                                    System.out.println("coucou11");

                                    result.setText(response.toString());
                                    System.out.println("coucoufinal");
                                    System.out.println(response.toString());
                                }
                            });

                        }
                        catch(Exception e){

                            System.out.println("nous sommes dans l'exception " + e.getMessage());
                        }
                    }
                };
                networkThread.start();

            }
        });




                /*Intent intent = new Intent(add_obj.this, MyObj.class);
                startActivity(intent);*/
            }

        //mDrawerToggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(add_obj.this, MyObj.class);
                    startActivity(intent);;
                }
            });
        }
    }

}

