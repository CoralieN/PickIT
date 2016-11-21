#include <SPI.h>

//change the following line to #include <Ethernet.h> to use the eithent shield
#include <Ethernet.h>

//#define DHCP
// Enter a MAC address for your controller below.
byte macPCCaro[] = { 0x18, 0x03, 0x73, 0x52, 0x06, 0x9C }; // MAC address PC Caro
byte mac[] = { 0xA8, 0xB3, 0xC3, 0xD2, 0xE6, 0xFC }; // random MAC address

// the dns server ip
//IPAddress dnServer(195, 83, 9, 11);
// the router's gateway address:
IPAddress gateway(192, 128, 0, 1);
// the subnet:
IPAddress subnet(255, 255, 0, 0);
// local ip
IPAddress ip(192, 168, 0, 10);

// ip server raspberry PI
IPAddress ipRPi(192,168,0,11);

byte server[] = { 192 , 168, 0 ,11 };

// Initialize the Ethernet server library
// with the IP address and port you want to use
// (port 80 is default for HTTP):

// Initialize the Ethernet client library
EthernetClient client;

char c ;

void setup() {

  //set up Ethernet:
  Serial.begin(9600);
  setupEthernet();
}

void loop() {
if (Serial.available())
  if (Serial.read()=='a'){
    httpRaspPI ();
  }  
}


void httpRaspPI (){
  if (client.connect(server, 80))
  {
    Serial.println("Getting data from thingspeak");

    client.print("GET");
    client.println(" HTTP/1.1");
    client.print("Host: ");
    client.println(ipRPi);
    client.println("Connection: close");
    client.println(); 
  }   
  else
  {
    Serial.println(F("Connection failed"));
  } 

  // Check for a response from the server, and route it
  // out the serial port.
  while (client.connected())
  {
    if ( client.available() )
    {
      char c = client.read();
      Serial.print(c);
    }      
  }
  Serial.println();
  client.stop();   
}



void setupEthernet()
{
  Serial.println("Setting up Ethernet...");
  // start the Ethernet connection:
#ifdef DHCP
  if (Ethernet.begin(mac) == 0) {
    Serial.println(F("Failed to configure Ethernet using DHCP"));
    // no point in carrying on, so do nothing forevermore:
    // try to congifure using IP address instead of DHCP:
    Ethernet.begin(mac, ip);
  }
#else
  Serial.println(F("Static ip addressing"));
  Ethernet.begin(macPCCaro, ip);
#endif
  Serial.print("My IP address: ");
  Serial.println(Ethernet.localIP());
  // give the Ethernet shield a second to initialize:
  delay(1000);
}


String URLEncode(const char* msg)
{
  const char *hex = "0123456789abcdef";
  String encodedMsg = "";

  while (*msg!='\0'){
    if( ('a' <= *msg && *msg <= 'z')
      || ('A' <= *msg && *msg <= 'Z')
      || ('0' <= *msg && *msg <= '9') ) {
      encodedMsg += *msg;
    } 
    else {
      encodedMsg += '%';
      encodedMsg += hex[*msg >> 4];
      encodedMsg += hex[*msg & 15];
    }
    msg++;
  }
  return encodedMsg;
}


