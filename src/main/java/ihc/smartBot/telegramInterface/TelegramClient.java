package ihc.smartBot.telegramInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TelegramClient extends TelegramLongPollingBot {

	private static File file = new File("Token.txt");

	@SuppressWarnings("deprecation")
	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {
			// String message_text = update.getMessage().getText();
			SendMessage message = Message.message(update);
			try {
				sendMessage(message); // Sending our message object to user
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

		} else if (update.hasCallbackQuery()) {
			EditMessageText newMessage = CallBack.callBack(update);
			try {
				editMessageText(newMessage);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	public String getBotUsername() {
		return "homeBot";
	}

	@Override
	public String getBotToken() {
		try {
			return read(file);
		} catch (Exception e) {
			return null;
		}
	}

	private String read(File file) throws IOException {
		try (FileReader fileReader = new FileReader(file); BufferedReader reader = new BufferedReader(fileReader)) {
			return reader.readLine();
		} catch (IOException e) {
			// TODO fazer tratamento de exc
		}
		return null;
	}
}
