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
			long chat_id = update.getMessage().getChatId();
			if (update.getMessage().getText().equals("/start")) {
				SendMessage message = new SendMessage().setChatId(chat_id).setText("You send /start");
				message.setReplyMarkup(new Start().ligar());
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (update.getMessage().getText().equals("")) {
			} else {

			}

		} else if (update.hasCallbackQuery()) {
			String call_data = update.getCallbackQuery().getData();
			long message_id = update.getCallbackQuery().getMessage().getMessageId();
			long chat_id = update.getCallbackQuery().getMessage().getChatId();
			String answer = null;
			int index = 0;
			try {
			index = Integer.parseInt(call_data.substring(0, 1));
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String acao = call_data.substring(1);
			if (new String("on").equals(acao)) {
				answer = State.liga(index);
			} else if (new String("off").equals(acao)) {
				answer = State.desliga(index);
			}

			else if (call_data.equals("todos")) {
				for (int i = 0; i < 9; i++) {
					answer = State.liga(i);
				}
				answer = "todos estão ligados";

			} else if (call_data.equals("desligaTodos")) {
				for (int i = 0; i < 9; i++) {
					answer = State.desliga(i);
				}
				answer = "todos estão desligados";
			}

			EditMessageText new_message = new EditMessageText().setChatId(chat_id).setMessageId((int) (message_id))
					.setText(answer);
			try {
				editMessageText(new_message);
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
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		try {
			
			String data = null;
			while ((data = reader.readLine()) != null) {
				return data;
			}
		} catch (IOException e) {
			// TODO fazer tratamento de exc
		}
		finally {
			fileReader.close();
			reader.close();
		
		}
		return null;
	}
}
