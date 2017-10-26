#include <Arduino.h>
#include <ArduinoJson.h>
#include <ESP8266HTTPClient.h>
#include <ESP8266WiFi.h>
#include <ESP8266mDNS.h>
#include <WiFiClient.h>

#include "connect.hpp"
#include "urls.hpp"

MDNSResponder mdns;

void setup() {
  connect();
  if (mdns.begin("esp8266", WiFi.localIP())) {
    Serial.println("MDNS responder started");
  }
  urls();
  Serial.println("HTTP server started");
}

void loop() { serve(); }