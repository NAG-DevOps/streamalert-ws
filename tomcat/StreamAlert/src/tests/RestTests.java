
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class RestTests {
		
	@Test
	public void facebook_get() throws IOException {
		URL url = new URL("http://localhost:8000/StreamAlert/pull/facebook/2342342342");
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.connect();

		int code = httpURLConnection.getResponseCode();
		assertEquals(200, code);
	}
	
	@Test
	public void github_get() throws IOException {
		URL url = new URL("http://localhost:8000/StreamAlert/pull/github/tensorflow/tensorflow/17711");
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.connect();

		int code = httpURLConnection.getResponseCode();
		assertEquals(200, code);
	}
}
