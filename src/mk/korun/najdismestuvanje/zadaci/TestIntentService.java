package mk.korun.najdismestuvanje.zadaci;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class TestIntentService extends IntentService {
	int endCount = 0;

	public TestIntentService() {
		super("TestIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("Service request",
				httpRequest("http://graph.facebook.com/?ids=http://www.imdb.com/title/tt0117500/"));
	}

	private String httpRequest(String urlString) {
		URL url;
		StringBuilder buffer = new StringBuilder();
		String line;
		
		try {
			url = new URL(urlString);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

			InputStream is = urlConnection.getInputStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(is));

			while ((line = rd.readLine()) != null) {
				buffer.append(line);
			}
			urlConnection.disconnect();
			
			/*
			 * old byte reader
			byte[] b = new byte[1024];
			while (in.read(b) != -1)
				buffer.append(new String(b));
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
