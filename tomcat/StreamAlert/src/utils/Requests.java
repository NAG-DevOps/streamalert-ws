
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Requests {
	
	/**
	 * Request a HTTP GET method on a URL.
	 * @param urlToRead URL to open the connection on
	 * @return Formatted string containing the request's input stream
	 * @throws Exception
	 */
	public static String get(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}
	
	/**
	 * Request a HTTP GET method on a URL.
	 * @param urlToRead URL to open the connection on
	 * @return Formatted string containing the request's input stream
	 * @throws Exception
	 */
	public static String getWithBasicAuth(String urlToRead, String username, String password) throws Exception {
		URL url = new URL (urlToRead);
        //String encoding = Base64.getEncoder().encodeToString((username+":"+password).getBytes(‌"utf-8"));
		String authStr = username + ":" + password;
//		String encoding = Base64.getEncoder().encodeToString(Base64.getEncoder().encode(authStr.getBytes()));
		String encoding = Base64.getEncoder().encodeToString((username+":"+password).getBytes(StandardCharsets.UTF_8));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encoding);
        InputStream content = (InputStream)connection.getInputStream();
        BufferedReader in = new BufferedReader (new InputStreamReader (content));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
		in.close();
		return result.toString();
	}
}
