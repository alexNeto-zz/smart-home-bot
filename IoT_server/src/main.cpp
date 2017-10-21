
#include <ESP8266WiFi.h>

#include "ssidPass.h"

WiFiServer server(80);

void setup() {

  Serial.begin(9600);
  delay(10);

  // prepare GPIO2
  pinMode(4, OUTPUT);
  digitalWrite(4, 0);

  pinMode(5, OUTPUT);
  digitalWrite(5, 0);

  // Connect to WiFi network
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");

  // Start the server
  server.begin();
  Serial.println("Server started");
  Serial.println(WiFi.localIP());
}

void loop() {

  WiFiClient client = server.available();
  if (!client) {
    return;
  }

  Serial.println("new client");
  while (!client.available()) {
    delay(1);
  }

  String req = client.readStringUntil('\r');
  Serial.println("request");
  Serial.println(req);
  client.flush();

  Serial.println(req.substring(5));

  

  client.flush();

  if (req.indexOf("led5_on") != -1)
    digitalWrite(5, 1);
  else if (req.indexOf("led5_off") != -1)
    digitalWrite(5, 0);
  else if (req.indexOf("led4_on") != -1)
    digitalWrite(4, 1);
  else if (req.indexOf("led4_off") != -1)
    digitalWrite(4, 0);
  else {
    Serial.println("invalid request");
    client.stop();
  }
  Serial.println("Client disonnected");
}