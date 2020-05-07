//<!--------------------------------------- Referencias ------------------------------------------>
//Conector MySQL clase
//https://github.com/ChuckBell/MySQL_Connector_Arduino/blob/master/src/MySQL_Connection.cpp
//insert basico
//https://github.com/ChuckBell/MySQL_Connector_Arduino/blob/master/examples/basic_insert/basic_insert.ino
//insert complejo
//https://github.com/ChuckBell/MySQL_Connector_Arduino/blob/master/examples/complex_insert/complex_insert.ino

//<!------------------- Constantes ---------------------->
#define USE_ARDUINO_INTERRUPTS true // Set-up low-level interrupts for most acurate BPM math.

//<!--------------------------------------- Includes ------------------------------------------>
//<!------------------- Lib Pulsesensor ---------------------->
#include <PulseSensorPlayground.h>
//<!------------------- Libs wifi ---------------------->
#include <SPI.h> 
#include <WiFiNINA.h>
//<!------------------- Libs MySQL ---------------------->
#include <MySQL_Connection.h>
#include <MySQL_Cursor.h>

//<!--------------------------------------- Variables ------------------------------------------>
//<!------------------- Pines ---------------------->
// PulseSensor PURPLE WIRE connected to ANALOG PIN 0
const int PulseWire = 0;

//<!------------------- MySQL variables ---------------------->
// IP of the MySQL server here
IPAddress server_addr(2,139,176,212); 
// MySQL user login username
char user[] = "pr_itcom"; 
// MySQL user login password
char password[] = "pr.itcom.11"; 

char INSERT_DATA[] = "INSERT INTO pr_itcom.SENSOR_RITMO_CARDIACO (DNI_PACIENTE, VALOR, FECHA) VALUES ('%s', %d, NOW())";
char query[128];
char dni[10]= "70355541N";


//<!------------------- WiFi variables ---------------------->
//SSID of your network
char ssid[] = "stackoverflow";
//password of your WPA Network
char pass[] = "SQLexcept69";


//<!------------------- Sensor ritmo cardiaco ---------------------->
// Variable del sensor
PulseSensorPlayground pulseSensor;

// Use this for WiFi instead of EthernetClient
WiFiClient client; 
MySQL_Connection conn((Client *)&client);

//int status = WL_IDLE_STATUS;



//<!-------------------------------------------------------- Setup ----------------------------------------------------------->
void setup()
{
  //<!--------------------------------------- Serial Conexion ------------------------------------------>
  // Initialize serial and wait for port to open:
  Serial.begin(9600);
  /*
  while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }*/


  //<!--------------------------------------- WiFi Conexion ------------------------------------------>
  int status = WiFi.begin(ssid, pass);
  if ( status != WL_CONNECTED) 
  {
    Serial.println("Couldn't get a wifi connection");
    while(true);
  }
  // print out info about the connection:
  else 
  {
    Serial.println("Connected to network");
    IPAddress ip = WiFi.localIP();
    Serial.print("My IP address is: ");
    Serial.println(ip);
  }

  //<!--------------------------------------- MySQL Conexion ------------------------------------------>
  Serial.println("Connecting...");
  if (conn.connect(server_addr, 3306, user, password)) {
    delay(1000);
  }
  else
  {
    Serial.println("Connection failed.");
    conn.close();
  }

  
  if (pulseSensor.begin()) 
  {
    Serial.println("We created a pulseSensor Object !"); //This prints one time at Arduino power-up, or on Arduino reset.
  }
}

//<!-------------------------------------------------------- LOOP ----------------------------------------------------------->
void loop() {
  int pulso = pulseSensor.getBeatsPerMinute();
  
  //int pulsotest = random (60, 80);
  
  if (pulseSensor.sawStartOfBeat()) 
  {
    sprintf(query, INSERT_DATA, dni, pulso);
    //Serial.println(query);
    MySQL_Cursor *cur_mem = new MySQL_Cursor(&conn);
    cur_mem->execute(query);
    delete cur_mem;
    //Serial.println("Data recorded.");
  }
  
  delay(10000);
}
