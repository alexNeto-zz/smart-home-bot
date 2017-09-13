package ihc.smartBot.telegramInterface;

import java.util.List;
import java.util.ArrayList;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import ihc.smartBot.serverConn.ConnectionServer;

public class TelegramClient extends TelegramLongPollingBot implements Token {
	
	@SuppressWarnings("deprecation")
	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			if (update.getMessage().getText().equals("/ligar")) {

				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("You send /ligar");
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
				List<InlineKeyboardButton> rowInline = new ArrayList<InlineKeyboardButton>();
				rowInline.add(new InlineKeyboardButton().setText("liga led 0").setCallbackData("d0_on"));
				// Set the keyboard to the markup
				rowsInline.add(rowInline);
				// Add it to the message
				markupInline.setKeyboard(rowsInline);
				message.setReplyMarkup(markupInline);
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (update.getMessage().getText().equals("/desligar")) {

				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("You send /start");
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
				List<InlineKeyboardButton> rowInline = new ArrayList<InlineKeyboardButton>();
				rowInline.add(new InlineKeyboardButton().setText("liga led 0").setCallbackData("d0_on"));
				// Set the keyboard to the markup
				rowsInline.add(rowInline);
				// Add it to the message
				markupInline.setKeyboard(rowsInline);
				message.setReplyMarkup(markupInline);
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else {

			}

		} else if (update.hasCallbackQuery()) {
			String call_data = update.getCallbackQuery().getData();
			long message_id = update.getCallbackQuery().getMessage().getMessageId();
			long chat_id = update.getCallbackQuery().getMessage().getChatId();

			if (call_data.equals("d0_on")) {
				String answer = "led 1 ligado";
				EditMessageText new_message = new EditMessageText().setChatId(chat_id).setMessageId((int) (message_id))
						.setText(answer);
				try {
					editMessageText(new_message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String getBotUsername() {
		return "homeBot";
	}

	@Override
	public String getBotToken() {
		return TOKEN;
	}
}
