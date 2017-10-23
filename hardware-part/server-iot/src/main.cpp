
#include <ArduinoJson.h>
#include <ESP8266HTTPClient.h>
#include <ESP8266WiFi.h>

#include "ssidPass.h"

void setup() {
  Serial.begin(115200);
  WiFi.begin(SSID, PASS);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Waiting for connection");
  }
  Serial.println(WiFi.localIP());
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
    StaticJsonBuffer<300> JSONbuffer;
    JsonObject &JSONencoder = JSONbuffer.createObject();

    JSONencoder["sensorType"] = "Temperature";

    JsonArray &values = JSONencoder.createNestedArray("values");
    values.add(20);
    values.add(21);
    values.add(23);

    JsonArray &timestamps = JSONencoder.createNestedArray("timestamps");
    timestamps.add("10:10");
    timestamps.add("10:20");
    timestamps.add("10:30");

    char JSONmessageBuffer[300];
    JSONencoder.prettyPrintTo(JSONmessageBuffer, sizeof(JSONmessageBuffer));
    Serial.println(JSONmessageBuffer);

    HTTPClient http;

    http.begin("http://anteph.pythonanywhere.com/postjson");
    http.addHeader("Content-Type", "application/json");

    int httpCode = http.POST(JSONmessageBuffer);
    String payload = http.getString();

    Serial.println(httpCode);
    Serial.println(payload);

    http.end();
  } else {
    Serial.println("Error in WiFi connection");
  }
  delay(30000);
}
/*
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

  if (req.indexOf("led[0-9]+") != -1)
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
*/