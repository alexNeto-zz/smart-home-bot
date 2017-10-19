package com.ihc.smartbot.telegraminterface;

import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;

public class CallBack {

	public static String getCallBack(Update update) {
		String call_data = update.getCallbackQuery().getData();
		String answer = null;
		int index = getIndex(call_data.substring(0, 1));
		String acao = call_data.substring(1);

		if ("on".equals(acao)) {
			answer = State.liga(index);
		} else if ("off".equals(acao)) {
			answer = State.desliga(index);
		} else if ("todos".equals(call_data)) {
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
		return answer;
	}

	public static int getIndex(String callBackIndex) {
		int index = 0;
		try {
			index = Integer.parseInt(callBackIndex);
			return index;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static EditMessageText callBack(Update update) {
		long message_id = update.getCallbackQuery().getMessage().getMessageId();
		long chat_id = update.getCallbackQuery().getMessage().getChatId();
		EditMessageText newMessage = new EditMessageText().setChatId(chat_id).setMessageId((int) (message_id))
				.setText(getCallBack(update));
		
		return newMessage;
	}
}
