package com.example.co.pickit_app;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by jarrar on 20/01/17.
 */

public class Init {

    private final static String NAMESPACE = "http://docs.insa.fr/";
    private final static String URL = "http://192.168.43.191:8080/Localhost_official/Localhost3306Service?WSDL";
    private final static String SOAP_ACTION = "http://docs.insa.fr/get_obj_names";
    private final static String METHOD_NAME = "get_obj_names";

    private final static String SOAP_ACTION2 = "http://docs.insa.fr/get_all_the_lists_names";
    private final static String METHOD_NAME2 = "get_all_the_lists_names";

    private final static String SOAP_ACTION3 = "http://docs.insa.fr/get_obj_in_lists_name";
    private final static String METHOD_NAME3 = "get_obj_in_lists_name";

    private final static String SOAP_ACTION4 = "http://docs.insa.fr/get_active_lists_name";
    private final static String METHOD_NAME4 = "get_active_lists_name";






//liste des objets;
    //Data_list_obj dans la classe Data

    public static void get_all_the_list_names() throws InterruptedException {

        Thread networkThread = new Thread() {
            @Override
            public void run() {

                try {
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);

                    System.out.println("coucou1");

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                    androidHttpTransport.debug = true;

                    System.out.println("coucou2");
                    androidHttpTransport.call(SOAP_ACTION2, envelope);

                    System.out.println("coucou3");
                    final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                    System.out.println("coucou10");
                    System.out.println("coucoufinal");
                    System.out.println("la réponse est !!!!!!! " + response.toString());

                    String Toparse = response.toString();
                    String delims = "[ ]+";
                    String[] tokens = Toparse.split(delims);

                    //Data_list_list data_list_list = new Data_list_list();
                    //Data_list data_list = new Data_list();

                   for (int i = 0; i < tokens.length; i++) {
                        System.out.println(tokens[i]);
                        //data_list.setName(tokens[0]);
                        //data_list.setState(Boolean.TRUE);

                        //data_list_list.add_list(data_list);

                       Data_list_list.add_list(new Data_list(tokens[i],Boolean.FALSE));

                        //data_list_list.getList_List().get(0).setName(tokens[0]);

                    }

                    System.out.println("KIKOUUU, size= "+Data_list_list.data_list_list.size());


                    /*System.out.println("la liste des objets : !!!! : ");
                    for (int i = 0; i < data.getList().size(); i++) {

                        System.out.println(data.getList().get(i));
                    }*/

                } catch (Exception e) {

                    System.out.println("nous sommes dans l'exception " + e.getMessage());
                }
            }
        };
        networkThread.start();
        //networkThread.sleep(50);
    }



    /*liste des liste
        1) new data_list
            2) set les attributs de data_list
        2) add data_list dans data_list_list*/

    /* récupérer le nom de toutes les listes --> create data_list
    * récupérer la liste des objets dans chaque liste
    * récupérer liste active ?
    * creer un new data_list a ajouter dans data_list_list
    * 3 appels soap
    * */

    public static void get_obj_names() {

        Thread networkThread2 = new Thread() {
            @Override
            public void run() {

                try {
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                    System.out.println("coucou1");

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                    androidHttpTransport.debug = true;

                    System.out.println("coucou2");
                    androidHttpTransport.call(SOAP_ACTION, envelope);

                    System.out.println("coucou3");
                    final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                    System.out.println("coucou10");
                    System.out.println("coucoufinal");
                    System.out.println("la réponse est !!!!!!! " + response.toString());

                    String Toparse = response.toString();
                    String delims = "[ ]+";
                    String[] tokens = Toparse.split(delims);

                    Data data = new Data();

                    for (int i = 0; i < tokens.length; i++) {
                        System.out.println(tokens[i]);
                        data.add_list(tokens[i]);
                    }

                    System.out.println("la liste des objets : !!!! : ");
                    for (int i = 0; i < data.getList().size(); i++) {

                        System.out.println(data.getList().get(i));
                    }

                } catch (Exception e) {

                    System.out.println("nous sommes dans l'exception " + e.getMessage());
                }
            }
        };
        networkThread2.start();
    }


//Ne marche pas, je n'arrive pas a entrer dans le if!!
    public static void update_active_list() throws InterruptedException {
        System.out.println("blabla1");

        Thread networkThread = new Thread() {
            @Override
            public void run() {
                System.out.println("blabla2");
                System.out.println("size!!! : "+Data_list_list.data_list_list.size());

                try {
                        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME4);

                        System.out.println("blabla1");

                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope.setOutputSoapObject(request);
                        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                        androidHttpTransport.debug = true;

                        System.out.println("blabla2");
                        androidHttpTransport.call(SOAP_ACTION4, envelope);

                        System.out.println("blabla3");
                        final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                        System.out.println("blabla4");
                        System.out.println("coucoufinal");
                        System.out.println("blablafinal !!!!!!! " + response.toString());

                        String Toparse = response.toString();
                        String delims = "[ ]+";
                        String[] tokens = Toparse.split(delims);

                        ArrayList<String> data_obj_of_list= new ArrayList<String>();

                        for (int j = 0; j < tokens.length; j++) {

                            System.out.println(tokens[j]);
                            data_obj_of_list.add(tokens[j]);
                            Data_list_list.active_list.add(tokens[j]);
                            Data_list_list.Active_list_String=Data_list_list.Active_list_String+"\n"+tokens[j];
                        }

                       int k;

                       for (int i=0; i< Data_list_list.data_list_list.size(); i++){

                           String name= Data_list_list.data_list_list.get(i).name;

                           for(k=0; k<tokens.length; k++) {
                               System.out.println("list name!! : "+name);
                               System.out.println("tokens!! : "+tokens[k]);

                               //je n'arrive pas à rentrer dans le if!!!
                               if (name.compareTo(tokens[k]) == 0) {
                                   System.out.println("Coucou c'est juuuste ");
                                   Data_list_list.data_list_list.get(i).state=Boolean.TRUE;
                               }

                           }

                       }

                } catch (Exception e) {

                    System.out.println("nous sommes dans l'exception " + e.getMessage());
                }
            }
        };
        networkThread.sleep(1000);
        networkThread.start();
    }


    public static void update_obj_in_list() throws InterruptedException {
        System.out.println("blabla1");

        Thread networkThread = new Thread() {
            @Override
            public void run() {
                System.out.println("blabla2");
                System.out.println("size!!! : "+Data_list_list.data_list_list.size());

                try {

                    for (int i=0; i< Data_list_list.data_list_list.size(); i++) {


                        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME3);

                        System.out.println("blabla1");
                        request.addProperty("arg0", Data_list_list.data_list_list.get(i).getName());

                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope.setOutputSoapObject(request);
                        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                        androidHttpTransport.debug = true;

                        System.out.println("blabla2");
                        androidHttpTransport.call(SOAP_ACTION3, envelope);

                        System.out.println("blabla3");
                        final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                        System.out.println("blabla4");
                        System.out.println("coucoufinal");
                        System.out.println("blablafinal !!!!!!! " + response.toString());

                        String Toparse = response.toString();
                        String delims = "[ ]+";
                        String[] tokens = Toparse.split(delims);

                        ArrayList<String> data_obj_of_list= new ArrayList<String>();

                        System.out.println("les objets de la liste "+ Data_list_list.data_list_list.get(i).name + " : ");

                        for (int j = 0; j < tokens.length; j++) {

                            System.out.println(tokens[j]);
                            data_obj_of_list.add(tokens[j]);
                        }
                        //data_list.setName(tokens[0]);
                        //data_list.setState(Boolean.TRUE);

                        //data_list_list.add_list(data_list);

                        Data_list_list.data_list_list.get(i).setData_obj_of_list(data_obj_of_list);

                        //data_list_list.add_list(new Data_list(tokens[i], Boolean.FALSE));

                        //data_list_list.getList_List().get(0).setName(tokens[0]);


                    }
                    /*System.out.println("la liste des objets : !!!! : ");
                    for (int i = 0; i < data.getList().size(); i++) {

                        System.out.println(data.getList().get(i));
                    }*/

                } catch (Exception e) {

                    System.out.println("nous sommes dans l'exception " + e.getMessage());
                }
            }
        };
        networkThread.sleep(1000);
        networkThread.start();
    }

}

        /* Set the name of the new list
                            new_list.setName(name_text.getText().toString());
                            Log.d("Nom", name_text.getText().toString());
                            // Set State to false by default
                            new_list.setState(true);
                            Log.d("SetState","false");
                            new_list.setData_obj_of_list(list_obj);
                            ListOfList.add(new_list);// Set the name of the new list
                            new_list.setName(name_text.getText().toString());
                            Log.d("Nom", name_text.getText().toString());
                            // Set State to false by default
                            new_list.setState(true);
                            Log.d("SetState","false");
                            new_list.setData_obj_of_list(list_obj);
                            ListOfList.add(new_list);*/


