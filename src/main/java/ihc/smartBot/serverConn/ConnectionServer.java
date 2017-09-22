package ihc.smartBot.serverConn;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionServer {
	
	//private String stem = "http://172.16.8.68/";
	private String stem = "http://192.168.1.76/";

	public void getConnection(String path) {
		try {
			URL url = new URL(stem + path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.setReadTimeout(15 * 1000);
			connection.connect();
			connection.getResponseCode();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
