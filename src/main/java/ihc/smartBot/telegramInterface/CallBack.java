package ihc.smartBot.telegramInterface;

import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;

public class CallBack {
	
	public static EditMessageText callBack(Update update) {
		String call_data = update.getCallbackQuery().getData();
		long message_id = update.getCallbackQuery().getMessage().getMessageId();
		long chat_id = update.getCallbackQuery().getMessage().getChatId();
		String answer = null;
		int index = 0;
		try {
			index = Integer.parseInt(call_data.substring(0, 1));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String acao = call_data.substring(1);
		if (new String("on").equals(acao)) {
			answer = State.liga(index);
		} else if (new String("off").equals(acao)) {
			answer = State.desliga(index);
		}

		else if ("todos".equals(call_data)) {
			for (int i = 0; i < 9; i++) {
				answer = State.liga(i);
			}
			answer = "todos estão ligados";
		} else if ("desligaTodos".equals(call_data)) {
			for (int i = 0; i < 9; i++) {
				answer = State.desliga(i);
			}
			answer = "todos estão desligados";
		}

		EditMessageText newMessage = new EditMessageText().setChatId(chat_id).setMessageId((int) (message_id))
				.setText(answer);
		
		return newMessage;
	}

}
