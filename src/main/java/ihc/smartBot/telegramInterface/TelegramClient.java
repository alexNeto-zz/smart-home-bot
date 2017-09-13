package ihc.smartBot.telegramInterface;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import ihc.smartBot.serverConn.ConnectionServer;

public class TelegramClient extends TelegramLongPollingBot implements Token {

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
			} else {

			}

		} else if (update.hasCallbackQuery()) {
			String call_data = update.getCallbackQuery().getData();
			long message_id = update.getCallbackQuery().getMessage().getMessageId();
			long chat_id = update.getCallbackQuery().getMessage().getChatId();
			String answer = null;
			if (call_data.equals("d0_on")) {
				answer = "led 1 ligado";
				State.d[0] = true;
				new ConnectionServer().getConnection("d0-on");
			}
			if(call_data.equals("d1_on")) {
				answer = "led 2 ligado";
				State.d[1] = true;
				new ConnectionServer().getConnection("d1-on");
			}
			if (call_data.equals("d2_on")) {
				answer = "led 3 ligado";
				State.d[2] = true;
				new ConnectionServer().getConnection("d2-on");
			}
			if(call_data.equals("d3_on")) {
				answer = "led4 ligado";
				State.d[3] = true;
				new ConnectionServer().getConnection("d3-on");
			}
			if (call_data.equals("d4_on")) {
				answer = "led 5 ligado";
				State.d[4] = true;
				new ConnectionServer().getConnection("d4-on");
			}
			if(call_data.equals("d5_on")) {
				answer = "led 6 ligado";
				State.d[5] = true;
				new ConnectionServer().getConnection("d5-on");
			}
			if (call_data.equals("d6_on")) {
				answer = "led 7 ligado";
				State.d[6] = true;
				new ConnectionServer().getConnection("d6-on");
			}
			if(call_data.equals("d7_on")) {
				answer = "led8 ligado";
				State.d[7] = true;
				new ConnectionServer().getConnection("d7-on");
			}
			if(call_data.equals("d8_on")) {
				answer = "led8 ligado";
				State.d[8] = true;
				new ConnectionServer().getConnection("d8-on");
			}
			
			if (call_data.equals("d0_off")) {
				answer = "led 1 desligado";
				State.d[0] = false;
				new ConnectionServer().getConnection("d0-off");
			}
			if(call_data.equals("d1_off")) {
				answer = "led 2 desligado";
				State.d[1] = false;
				new ConnectionServer().getConnection("d1-off");
			}
			if (call_data.equals("d2_off")) {
				answer = "led 3 desligado";
				State.d[2] = false;
				new ConnectionServer().getConnection("d2-off");
			}
			if(call_data.equals("d3_off")) {
				answer = "led4 desligado";
				State.d[3] = false;
				new ConnectionServer().getConnection("d3-off");
			}
			if (call_data.equals("d4_off")) {
				answer = "led 5 desligado";
				State.d[4] = false;
				new ConnectionServer().getConnection("d4-off");
			}
			if(call_data.equals("d5_off")) {
				answer = "led 6 desligado";
				State.d[5] = false;
				new ConnectionServer().getConnection("d5-off");
			}
			if (call_data.equals("d6_off")) {
				answer = "led 7 desligado";
				State.d[6] = true;
				new ConnectionServer().getConnection("d6-off");
			}
			if(call_data.equals("d7_off")) {
				answer = "led8 desligado";
				State.d[7] = true;
				new ConnectionServer().getConnection("d7-off");
			}
			if(call_data.equals("d8_off")) {
				answer = "led8 desligado";
				State.d[8] = true;
				new ConnectionServer().getConnection("d8-off");
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
		return TOKEN;
	}
}
