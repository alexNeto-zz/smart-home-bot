# Smart home bot

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://travis-ci.org/alexNeto/smart-home-bot.svg?branch=master)](https://travis-ci.org/alexNeto/smart-home-bot)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/24ec6c739bfc405ead8973438883f4a3)](https://www.codacy.com/app/alexNeto/smart-home-bot?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=alexNeto/smart-home-bot&amp;utm_campaign=Badge_Grade)
[![Maintainability](https://api.codeclimate.com/v1/badges/14f8fbc90ea509f00f63/maintainability)](https://codeclimate.com/github/alexNeto/smart-home-bot/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/14f8fbc90ea509f00f63/test_coverage)](https://codeclimate.com/github/alexNeto/smart-home-bot/test_coverage)

## WIP
  - [ ] communication between bot and hardware
  - [ ] communication between esp8266 and arduinos
  - [ ] Unit test in the bot
  - [ ] Unit test in the IoT part
  - [ ] implement deep learning (I need to learn to do this first haha)
  - [ ] give less mechanical response to the user
  - [ ] Improvise. Adapt. Overcome

## Materials and methods

To make it easy, the development was separated into two parts, one to the _chatbot_ and other to the _IoT_ that will control the "house"
These two context have to work separately.

### Bot context
The [Telegram api](https://core.telegram.org/) offers a complete platform to develp chatbot and even games and it's free.
The weapon of choose is Java, because of reasons. 

### IoT context
#### _Hardware_
The Thing is the esp8266 because is cheap and have all that we need for this project, I'm also using some LEDs ans resistors to see the response.

#### _Software_
The language to develop the IoT part is the Arduino language (almost C++) and some parts of pure C++ and C.

# How it works
The chatbot gets the command from the user, and for this fase of the project this command is passed with sockets to the esp8266 via WiFi

