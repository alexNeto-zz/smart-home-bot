#include <Arduino.h>
#include <ArduinoJson.h>
#include <ESP8266HTTPClient.h>
#include <ESP8266WebServer.h>
#include <ESP8266WiFi.h>
#include <ESP8266mDNS.h>
#include <WiFiClient.h>

#include "connect.hpp"

MDNSResponder mdns;
ESP8266WebServer server(80);
String webPage = "";

int gpio0_pin = 0;
int gpio2_pin = 2;

void setup() {
  connect();

  pinMode(gpio0_pin, OUTPUT);
  digitalWrite(gpio0_pin, LOW);
  pinMode(gpio2_pin, OUTPUT);
  digitalWrite(gpio2_pin, LOW);

  if (mdns.begin("esp8266", WiFi.localIP())) {
    Serial.println("MDNS responder started");
  }

  server.on("/", []() { server.send(200, "text/html", webPage); });
  server.on("/socket1On", []() {
    server.send(200, "text/html", webPage);
    digitalWrite(gpio0_pin, HIGH);
    delay(1000);
  });
  server.on("/config/set", HTTP_POST, []() {
    StaticJsonBuffer<200> newBuffer;
    JsonObject& newjson = newBuffer.parseObject(server.arg("plain"));
    server.send(200, "text/json", "{success:true}");
  });
  server.on("/socket1Off", []() {
    server.send(200, "text/html", webPage);
    digitalWrite(gpio0_pin, LOW);
    delay(1000);
  });
  server.on("/socket2On", []() {
    server.send(200, "text/html", webPage);
    digitalWrite(gpio2_pin, HIGH);
    delay(1000);
  });
  server.on("/socket2Off", []() {
    server.send(200, "text/html", webPage);
    digitalWrite(gpio2_pin, LOW);
    delay(1000);
  });
  server.begin();
  Serial.println("HTTP server started");
}

void loop(void) { server.handleClient(); }