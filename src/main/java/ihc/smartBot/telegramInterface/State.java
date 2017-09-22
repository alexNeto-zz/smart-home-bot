package ihc.smartBot.telegramInterface;

import ihc.smartBot.serverConn.ConnectionServer;

public class State {

	public static boolean[] d = new boolean[9];
	public static boolean todos = true;

	public static String liga(int i) {
		State.d[i] = true;
		new ConnectionServer().getConnection("d" + i + "-on");
		return "led " + (i + 1) + " ligado";
	}

	public static String desliga(int i) {
		State.d[i] = false;
		new ConnectionServer().getConnection("d" + i + "-off");
		return "led " + (i + 1) + " desligado";
	}
}
