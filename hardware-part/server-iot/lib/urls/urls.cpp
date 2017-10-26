#include <Arduino.h>
#include <ArduinoJson.h>

#include "urls.hpp"

String webPage = "";

ESP8266WebServer server(80);

void urls() {
  server.on("/", []() { server.send(200, "text/html", webPage); });
  server.on("/led[0-9]", []() {
    server.send(200, "text/html", webPage);
    Serial.println("led ligado");
    // digitalWrite(gpio0_pin, HIGH);
    delay(1000);
  });
  server.on("/config/set", HTTP_POST, []() {
    StaticJsonBuffer<200> newBuffer;
    JsonObject& newjson = newBuffer.parseObject(server.arg("plain"));
    server.send(200, "application/json", "{success:true}");
  });
  server.on("/socket1Off", []() {
    server.send(200, "text/html", webPage);
    // digitalWrite(gpio0_pin, LOW);
    delay(1000);
  });
  server.on("/socket2On", []() {
    server.send(200, "text/html", webPage);
    // digitalWrite(gpio2_pin, HIGH);
    delay(1000);
  });
  server.on("/socket2Off", []() {
    server.send(200, "text/html", webPage);
    // digitalWrite(gpio2_pin, LOW);
    delay(1000);
  });
  server.begin();
}

void serve(){
    server.handleClient();
}