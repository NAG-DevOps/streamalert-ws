

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import neural_network.Net;

/**
 * Servlet implementation class Alert
 */
@WebServlet("/alert")
public class Alert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Alert() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		String contentType = request.getContentType();
		if (contentType.equals("application/json")) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			JsonNode jsonNode = mapper.readValue(body, JsonNode.class);
			AWSUtils awsUtils = new AWSUtils("soen487.streamalerts", "us-east-1");
			awsUtils.putJSON(jsonNode.toString(), "alert.json");
		}
		else if (contentType.equals("application/xml")) {
			AlertModel alertModel = XmlParser.unmarshal(body, AlertModel.class);
			ObjectMapper mapper = new ObjectMapper();
			String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(alertModel);
			JsonNode jsonNode = mapper.readValue(jsonPretty, JsonNode.class);
			AWSUtils awsUtils = new AWSUtils("soen487.streamalerts", "us-east-1");
			awsUtils.putJSON(jsonNode.toString(), "alert.json");
		}
		request.setAttribute("feeds", body);
		response.setContentType("text/html");
        request.getRequestDispatcher("/WEB-INF/jsp/feeds.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		String contentType = request.getContentType();
		if (contentType.equals("application/json")) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			JsonNode jsonNode = mapper.readValue(body, JsonNode.class);
			AWSUtils awsUtils = new AWSUtils("soen487.streamalerts", "us-east-1");
			awsUtils.putJSON(jsonNode.toString(), "alert.json");
		}
		else if (contentType.equals("application/xml")) {
			AlertModel alertModel = XmlParser.unmarshal(body, AlertModel.class);
			ObjectMapper mapper = new ObjectMapper();
			String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(alertModel);
			JsonNode jsonNode = mapper.readValue(jsonPretty, JsonNode.class);
			AWSUtils awsUtils = new AWSUtils("soen487.streamalerts", "us-east-1");
			awsUtils.putJSON(jsonNode.toString(), "alert.json");
		}
	}

}
