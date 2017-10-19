package com.ihc.smartbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.ihc.smartbot.telegraminterface.TelegramClient;

public class Main {
	
	public static void main(String[] args) {
		
		ApiContextInitializer.init();

		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			botsApi.registerBot(new TelegramClient());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
