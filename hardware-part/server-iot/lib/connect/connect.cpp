#include <Arduino.h>
#include <ESP8266WiFi.h>

#include "../ssidPass/ssidPass.h"
#include "connect.hpp"

void connect() {
  Serial.begin(115200);
  WiFi.begin(SSID, PASS);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Waiting for connection");
  }
  Serial.println(WiFi.localIP());
}