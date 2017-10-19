package com.ihc.smartbot.telegraminterface;

import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;

public class CallBack {

	public static String getCallBack(String callData) {
		String answer = null;
		int index = getIndex(callData.substring(0, 1));
		String acao = callData.substring(1);

		if ("on".equals(acao)) {
			answer = State.luz(index, "-on");
		} else if ("off".equals(acao)) {
			answer = State.luz(index, "-off");
		} else if ("todos".equals(callData)) {
			for (int i = 0; i < 9; i++) {
				answer = State.luz(i, "-on");
			}
			answer = "todos estão ligados";
		} else if ("desligaTodos".equals(callData)) {
			for (int i = 0; i < 9; i++) {
				answer = State.luz(i, "-off");
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
		String answer = getCallBack(update.getCallbackQuery().getData());
		EditMessageText newMessage = new EditMessageText().setChatId(chat_id).setMessageId((int) (message_id))
				.setText(answer);
		return newMessage;
	}
}
