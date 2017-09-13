#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>
#include <ESP8266mDNS.h>

#include "ssidPass.h"

#define D0 16
#define D1 5
#define D2 4
#define D3 0
#define D4 2
#define D5 14
#define D6 12
#define D7 13
#define D8 15

ESP8266WebServer server(80);

void handleRoot(){
  
}

void handleNotFound(){
  String message = "File Not Found\n\n";
  message += "URI: ";
  message += server.uri();
  message += "\nMethod: ";
  message += (server.method() == HTTP_GET)?"GET":"POST";
  message += "\nArguments: ";
  message += server.args();
  message += "\n";
  for (uint8_t i=0; i<server.args(); i++){
    message += " " + server.argName(i) + ": " + server.arg(i) + "\n";
  }
  server.send(404, "text/plain", message);
}

void d0On(){
  digitalWrite(D0, 1);
  server.send(200);
}
void d1On(){digitalWrite(D1, 1);}
void d2On(){digitalWrite(D2, 1);}
void d3On(){digitalWrite(D3, 1);}
void d4On(){digitalWrite(D4, 1);}
void d5On(){digitalWrite(D5, 1);}
void d6On(){digitalWrite(D6, 1);}
void d7On(){digitalWrite(D7, 1);}
void d8On(){digitalWrite(D8, 1);}

void d0Off(){digitalWrite(D0, 0);}
void d1Off(){digitalWrite(D1, 0);}
void d2Off(){digitalWrite(D2, 0);}
void d3Off(){digitalWrite(D3, 0);}
void d4Off(){digitalWrite(D4, 0);}
void d5Off(){digitalWrite(D5, 0);}
void d6Off(){digitalWrite(D6, 0);}
void d7Off(){digitalWrite(D7, 0);}
void d8Off(){digitalWrite(D8, 0);}

void setup(void){
  pinMode(D0, OUTPUT);
  pinMode(D1, OUTPUT);
  pinMode(D2, OUTPUT);
  pinMode(D3, OUTPUT);
  pinMode(D4, OUTPUT);
  pinMode(D5, OUTPUT);
  pinMode(D6, OUTPUT);
  pinMode(D7, OUTPUT);
  pinMode(D8, OUTPUT);
  d0Off();
  d1Off();
  d2Off();
  d3Off();
  d4Off();
  d5Off();
  d6Off();
  d7Off();
  d8Off();
  Serial.begin(115200);
  WiFi.begin(ssid, password);
  Serial.println("");

  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  if (MDNS.begin("esp8266")) {
    Serial.println("MDNS responder started");
  }

  server.on("/", handleRoot);

  server.on("/d0-on", d0On);
  server.on("/d1-on", d1On);
  server.on("/d2-on", d2On);
  server.on("/d3-on", d3On);
  server.on("/d4-on", d4On);
  server.on("/d5-on", d5On);
  server.on("/d6-on", d6On);
  server.on("/d7-on", d7On);
  server.on("/d8-on", d8On);

  server.on("/d0-off", d0Off);
  server.on("/d1-off", d1Off);
  server.on("/d2-off", d2Off);
  server.on("/d3-off", d3Off);
  server.on("/d4-off", d4Off);
  server.on("/d5-off", d5Off);
  server.on("/d6-off", d6Off);
  server.on("/d7-off", d7Off);
  server.on("/d8-off", d8Off);
  
  server.onNotFound(handleNotFound);

  server.begin();
  Serial.println("HTTP server started");
}

void loop(void){
  server.handleClient();
}
