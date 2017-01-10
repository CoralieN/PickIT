package com.example.co.pickit_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Connection extends AppCompatActivity {

    private final String NAMESPACE = "http://docs.insa.fr/";
    private final String URL = "http://192.168.43.191:8080/Localhost_official/Localhost3306Service?WSDL";
    private final String SOAP_ACTION = "http://docs.insa.fr/connect";
    private final String METHOD_NAME = "connect";
    /** Called when the activity is first created. */


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        System.out.println("coucou1");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button_connect);
        fab.setOnClickListener(new View.OnClickListener() {*/

            Button login = (Button) findViewById(R.id.button_connect);
            login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/


                Thread networkThread = new Thread() {
                    @Override
                    public void run() {

                        try {


                            System.out.println("coucou2");
                            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                            System.out.println("coucou3");

                            EditText userName = (EditText) findViewById(R.id.Login);
                            String user_Name = userName.getText().toString(); //retreive le texte tapé sur l'appli
                            System.out.println("username !!!! "+user_Name);

                            EditText userPassword = (EditText) findViewById(R.id.Pwd);
                            String user_Password = userPassword.getText().toString(); // recupérer pswd tappé sur appli
                            System.out.println("user password !!!! "+user_Password);


                            System.out.println("coucou4");

                            System.out.println("coucou5");


                            request.addProperty("arg0", user_Name);
                            request.addProperty("arg1",user_Password);

                            System.out.println("coucou6");


                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.setOutputSoapObject(request);
                            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                            androidHttpTransport.debug = true;

                            System.out.println("coucou7");



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

    }

}
