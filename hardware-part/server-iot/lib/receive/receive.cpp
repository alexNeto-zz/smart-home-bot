#include <Arduino.h>

#include <WiFiClientSecure.h>

#include "receive.h"

#define  host "localhost:3000"
#define httpsPort 443
#define url "/status"

const char* fingerprint = "CF 05 98 89 CA FF 8E D8 5E 5C E0 C2 E4 F7 E6 C3 C7 50 DD 5C";

WiFiClientSecure client;

void receive(){
    Serial.print("connecting to ");
    Serial.println(host);
    if (!client.connect(host, httpsPort)){
        Serial.println("connection failed");
        return;
    }

    if (client.verify(fingerprint, host)){
        Serial.println("certificate matches");
    }
    else{
        Serial.println("certificate doesn't match");
    }

    Serial.print("requesting URL: ");
    Serial.println(url);

    client.print(String("GET ") + url + " HTTP/1.1\r\n" +
                 "Host: " + host + "\r\n" +
                 "User-Agent: BuildFailureDetectorESP8266\r\n" +
                 "Connection: close\r\n\r\n");

    Serial.println("request sent");
    while (client.connected()){
        String line = client.readStringUntil('\n');
        if (line == "\r"){
            Serial.println("headers received");
            break;
        }
    }
    String line = client.readStringUntil('\n');
    if (line.startsWith("{\"state\":\"success\"")){
        Serial.println("esp8266/Arduino CI successfull!");
    }
    else{
        Serial.println("esp8266/Arduino CI has failed");
    }
    Serial.println("reply was:");
    Serial.println("==========");
    Serial.println(line);
    Serial.println("==========");
    Serial.println("closing connection");
}