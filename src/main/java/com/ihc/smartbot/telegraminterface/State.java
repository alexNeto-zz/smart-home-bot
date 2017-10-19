package com.ihc.smartbot.telegraminterface;

import com.ihc.smartbot.serveriot.ConnectionServer;

public class State {

	public static boolean[] d = new boolean[9];
	public static boolean todos = true;

	public static String luz(int i, String acao) {
		String resposta = null;
		if("-on".equals(acao)) {
			State.d[i] = true;
			resposta = "led " + (i + 1) + " ligado";
		} else {
			State.d[i] = false;
			resposta = "led " + (i + 1) + " desligado";
		}
		new ConnectionServer().getConnection("d" + i + acao);
		return resposta;
	}
}
