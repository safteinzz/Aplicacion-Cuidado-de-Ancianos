/********************************************************************************
    Autor:
      Sergio Bemposta
 
    Mas informacion Comados AT:
      https://www.itead.cc/wiki/ESP8266_Serial_WIFI_Module
 
    Cableado:
    Arduino Mega              ||  Adaptador ESP8266
      RX del arduino          ||  GND
      CH_PD Soft RST: 3.3v    ||  Not Connected
      Not Connected           ||  Not Connected
      VCC: 3.3v               ||  TX del arduino (divisor de tension)
********************************************************************************/
 
const char * ssid = "Mi teléfono";
const char * password = "1234";
 
const char * dbUser = "pr_itcom";
const char * dbPass = "pr.itcom.11";
const char * dbName = "pr_itcom";
//http://2.139.176.212:81/bd/insert.php?db=pr_itcom&user=pr_itcom&pass=pr.itcom.11&insert=sensor_presencia(DNI_PACIENTE,%20ID_TIPO_PRESENCIA)%20VALUES%20(%2770355541N%27,%202);
 
const unsigned int TAM_BUFF = 250;
char cmd[TAM_BUFF];
 
/********************************************************************************/
/********************************************************************************/
bool WaitOK( long, bool = false, bool = true );
int  WaitAnswer( long, bool = false, bool = true );
void sendComand( String, bool = true );

/********************************************************************************/
/********************************************************************************/
const int Trigger = 2;   //Pin digital 2 para el Trigger del sensor
const int Echo = 3;   //Pin digital 3 para el Echo del sensor
/********************************************************************************/
/********************************************************************************/
void setup() {
    Serial.begin( 115200 ); // Connection to PC
    Serial1.begin( 115200 ); // Connection to ESP8266
    pinMode( 13, OUTPUT );
    digitalWrite( 13, LOW );
 
    pinMode( 7, OUTPUT );
    digitalWrite( 7, LOW );

    pinMode(Trigger, OUTPUT); //pin como salida
    pinMode(Echo, INPUT);  //pin como entrada
    digitalWrite(Trigger, LOW);//Inicializamos el pin con 0
}
 
/********************************************************************************/
/********************************************************************************/
bool configuracion = true;
void loop() {
    if(configuracion){
      
      
   
      sendComand( "AT" );
      if( !WaitOK( 1000 ) ) {
          Serial.println( "Error: Modulo Wifi no responde" );
          return;
      }
   
      /*  Esto solo se usa para configurarlo la 1º vez.
          sendComand("AT+RST");
          PrintResponse(6000);
          sendComand("AT");
          WaitOK(1000);
   
          sendComand("AT+CWMODE=1");
          WaitOK(2000);
   
          cad = "AT+CWJAP=\"" + String(ssid) + "\",\"" + String(password) + "\"";
          sendComand(cad);
          WaitOK(10000);
          sendComand("AT+CIFSR");
          if(!WaitOK(3000)){
          Serial.println("Wifi no conectada a la Red");
          return;
          }
      */
   
      // Esto es para dar tiempo a que se conecte a la wireless..
      delay( 5000 );
   
      
   
      
   
      Serial.print( "Terminado" );
      configuracion = false;
    }
    
    //Sensor de proximidad
    long t; //timepo que demora en llegar el eco
    long d; //distancia en centimetros
  
    digitalWrite(Trigger, HIGH);
    delayMicroseconds(10);          //Enviamos un pulso de 10us
    digitalWrite(Trigger, LOW);
    
    t = pulseIn(Echo, HIGH); //obtenemos el ancho del pulso
    d = t/59;             //escalamos el tiempo a una distancia en cm

    if(d < 50){
      Serial.println("Insertar en BD");
      sendAccion();
      delay(2000);
    }
  
   delay(100);          //Hacemos una pausa de 100ms
}

void sendAccion(){
  /* Esto es lo que queremos:
          insert into ccaa(id_ccaa, NombreId, Nombre) values (30, 'nombre', 'Nombre Largo')
          Comando GEET "GET /bd/insert.php?db=test&user=alumno&pass=alumno&insert=ccaa(id_ccaa,%20NombreId,%20Nombre)%20values%20(20,%20%27nombre2%27,%20%27Nombre%20Largo%27) HTTP/1.0";
      */
      String cad;
      cad = "AT+CIPSTART=\"TCP\",\"2.139.176.212\",81";
      sendComand( cad );
      WaitOK( 2000 );
      sendComand( "AT+CIPSTATUS" );
      WaitOK( 1000 );
      
      int respuesta;
      char *format = "GET /bd/insert.php?db=%s&user=%s&pass=%s&insert=%s HTTP/1.0";
      
      char *inserCmd = "sensor_presencia(DNI_PACIENTE,%20ID_TIPO_PRESENCIA)%20VALUES%20(%2770355541N%27,%203)";
      snprintf(cmd, TAM_BUFF, format, dbName, dbUser, dbPass, inserCmd);
   
      sendGet( cmd );
      respuesta = WaitAnswer( 10000 );
      switch( respuesta ) {
          case 0:
              Serial.println( "** Insercion Correcta **" );
              break;
          case 1:
              Serial.println( "Error 1" );
              break;
          case 2:
              Serial.println( "Error 2" );
              break;
          default:
              Serial.println( "Not Fount" );
              sendComand( "AT+CIPCLOSE" );
              break;
      }
}
 
/********************************************************************************/
/********************************************************************************/
void sendComand( String c, bool echo ) {
    Serial1.flush();
    Serial1.println( c );
    if( echo )Serial.println( "*****************************************" );
    if( echo )Serial.println( c );
    Serial1.flush();
}
 
/********************************************************************************/
/********************************************************************************/
void sendGet( String cmd ) {
    String cad = "AT+CIPSEND=" + String( cmd.length() + 4 );
    sendComand( cad );
    WaitOK( 500 );
    sendComand( cmd, true );
    sendComand( "" );
}
 
/********************************************************************************/
/********************************************************************************/
void PrintResponse( long timeoutamount ) {
    unsigned long timeout = millis() + timeoutamount;
    unsigned long timeStart = millis();
    char c;
    Serial.println( "=========================================" );
    while( millis() <= timeout ) {
        if( Serial1.available() > 0 ) {
            c = Serial1.read();
            Serial.write( c );
        }
    }
}
 
/********************************************************************************/
/********************************************************************************/
bool WaitOK( long timeoutamount, bool echo, bool echoError ) {
    static int codigo = 0;
    unsigned long timeout = millis() + timeoutamount;
    unsigned long timeStart = millis();
    char c0 = ' ', c1 = ' ';
    codigo += 1;
    if( echo )Serial.println( "-----------------------------------------" );
    while( millis() <= timeout ) {
        while( Serial1.available() > 0 ) {
            c1 = Serial1.read();
            if( echo )Serial.write( c1 );
            if( c0 == 'O' && c1 == 'K' ) {
                if( echo )Serial.println( "\nWaitOK TRUE [" + String( millis() - timeStart ) + "ms]" );
                delay( 100 );
                while( Serial1.available() > 0 ) Serial1.read();
                return true;
            }
            c0 = c1;
        }
    }
    if( echoError )Serial.println( "WaitOK False {Code:" + String( codigo ) + "} [" + String( millis() - timeStart ) + "ms]" );
    return false;
}
 
/********************************************************************************/
/********************************************************************************/
int WaitAnswer( long timeoutamount, bool echo, bool echoError ) {
    unsigned long timeout = millis() + timeoutamount;
    unsigned long timeStart = millis();
    char c[7] = "       ";
    if( echo )Serial.println( "-----------------------------------------" );
    while( millis() <= timeout ) {
        while( Serial1.available() > 0 ) {
            c[6] = Serial1.read();
            if( echo )Serial.write( c[6] );
            if( c[6] == '#' ) {
                if( c[0] == 'E' && c[1] == 'R' && c[2] == 'R' && c[3] == 'O' && c[4] == 'R' ) {
                    Serial.println( "\nAnswer ERROR:" + String( c[5] ) + " [" + String( millis() - timeStart ) + "ms]" );
                    delay( 100 );
                    while( Serial1.available() > 0 ) Serial1.read();
                    return ( c[5] - '0' );
                }
                if( c[4] == 'O' && c[5] == 'K' ) {
                    Serial1.read();
                    char code = Serial1.read();
                    if( echo )Serial.println( "\nAnswer OK [" + String( millis() - timeStart ) + "ms]" );
                    delay( 100 );
                    while( Serial1.available() > 0 ) Serial1.read();
                    return 0;
                }
            }
            c[0] = c[1]; c[1] = c[2]; c[2] = c[3]; c[3] = c[4]; c[4] = c[5]; c[5] = c[6];
        }
    }
    if( echoError )Serial.println( "WaitAnswer False [" + String( millis() - timeStart ) + "ms]" );
    return -1;
}
