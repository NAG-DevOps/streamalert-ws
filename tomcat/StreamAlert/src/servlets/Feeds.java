

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Servlet implementation class Feeds
 */
@WebServlet("/feeds")
public class Feeds extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Feeds() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getHeader("username");
		String password = request.getHeader("password");

		String url = "https://api.github.com/feeds";
		try {
			String getResponse = null;
			if(username != null && password != null) {
				getResponse = Requests.getWithBasicAuth(url, username, password);
			}
			else {
				getResponse = Requests.get(url);
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
//			String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getResponse);
			JsonNode jsonNode = mapper.readValue(getResponse, JsonNode.class);
			AWSUtils awsUtils = new AWSUtils("soen487.streamalerts", "us-east-1");
			awsUtils.putJSON(jsonNode.toString(), "feeds.json");
			request.setAttribute("feeds", jsonNode.toString());
			response.setContentType("text/html");
	        request.getRequestDispatcher("/WEB-INF/jsp/feeds.jsp").forward(request, response);
		}
		catch (Exception e) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
