package ihc.smartBot.telegramInterface;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Start {

	public InlineKeyboardMarkup ligar() {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
		List<InlineKeyboardButton> row1 = new ArrayList<InlineKeyboardButton>();;
		for (int i = 0; i < 9; i++) {
			if (!State.d[i]) {
				row1.add(new InlineKeyboardButton().setText("liga led " + (i + 1)).setCallbackData("d" + i + "_on"));
			} else {
				row1.add(new InlineKeyboardButton().setText("desliga led " + (i + 1))
						.setCallbackData("d" + i + "_off"));
			}
			if (i % 2 == 0 || i == 0) {
				rowsInline.add(row1);
				row1 = new ArrayList<InlineKeyboardButton>();
			}
		}
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
}
