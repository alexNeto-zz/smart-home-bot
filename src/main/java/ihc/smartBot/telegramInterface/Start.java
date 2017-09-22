package ihc.smartBot.telegramInterface;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Start {

	public InlineKeyboardMarkup ligar() {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
		List<InlineKeyboardButton> row1 = new ArrayList<InlineKeyboardButton>();
		for (int i = 0; i < 9; i++) {
			if (!State.d[i]) {
				row1.add(new InlineKeyboardButton().setText("liga led " + (i + 1)).setCallbackData(i + "on"));
			} else {
				row1.add(new InlineKeyboardButton().setText("desliga led " + (i + 1)).setCallbackData(i + "off"));
			}
			if (i % 2 == 0 || i == 0) {
				rowsInline.add(row1);
				row1 = new ArrayList<InlineKeyboardButton>();
			}
		}
		rowsInline.add(acionaTodos());
		rowsInline.add(desacionaTodos());
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}

	public List<InlineKeyboardButton> acionaTodos() {

		List<InlineKeyboardButton> row1 = new ArrayList<InlineKeyboardButton>();
		row1.add(new InlineKeyboardButton().setText("liga todos").setCallbackData("todos"));

		return row1;

	}

	public List<InlineKeyboardButton> desacionaTodos() {

		List<InlineKeyboardButton> row1 = new ArrayList<InlineKeyboardButton>();
		row1.add(new InlineKeyboardButton().setText("desliga todos").setCallbackData("desligaTodos"));

		return row1;

	}
}
