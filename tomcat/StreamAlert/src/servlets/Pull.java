

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Servlet implementation class Pull
 */
@WebServlet("/pull/*")
public class Pull extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pull() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getHeader("username");
		String pass = request.getHeader("password");

		String uriEnding = request.getPathInfo();
		String [] uriParts = uriEnding.split("/");
		String type = uriParts[1];
		String getResponse = null;
		if (uriParts.length == 5 && type.equals("github")) {
			String username = uriParts[2];
			String repo = uriParts[3];
			String issueNumber = uriParts[4];
			String url = "https://api.github.com/repos/" + username + "/" + repo + "/issues/" + issueNumber;
			try {
				getResponse = Requests.get(url);
				ObjectMapper mapper = new ObjectMapper();
				mapper.enable(SerializationFeature.INDENT_OUTPUT);
				JsonNode jsonNode = mapper.readValue(getResponse, JsonNode.class);
				AWSUtils awsUtils = new AWSUtils("soen487.streamalerts", "us-east-1");
				awsUtils.putJSON(jsonNode.toString(), "pull.json");
				request.setAttribute("feeds", jsonNode.toString());
				response.setContentType("text/html");
		        request.getRequestDispatcher("/WEB-INF/jsp/feeds.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("feeds", e.toString());
				response.setContentType("text/html");
		        request.getRequestDispatcher("/WEB-INF/jsp/feeds.jsp").forward(request, response);
			}
			
		}
		else if (uriParts.length == 3 && type.equals("facebook")) {
			String account = uriParts[2];
			String url = "https://graph.facebook.com/v2.12/" + account;
			try {
				getResponse = Requests.getWithBasicAuth(url, user, pass);
				ObjectMapper mapper = new ObjectMapper();
				mapper.enable(SerializationFeature.INDENT_OUTPUT);
				JsonNode jsonNode = mapper.readValue(getResponse, JsonNode.class);
				AWSUtils awsUtils = new AWSUtils("soen487.streamalerts", "us-east-1");
				awsUtils.putJSON(jsonNode.toString(), "pull.json");
				request.setAttribute("feeds", jsonNode.toString());
				response.setContentType("text/html");
		        request.getRequestDispatcher("/WEB-INF/jsp/feeds.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("feeds", e.toString());
				response.setContentType("text/html");
		        request.getRequestDispatcher("/WEB-INF/jsp/feeds.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("feeds", "error " + uriParts.length);
			response.setContentType("text/html");
	        request.getRequestDispatcher("/WEB-INF/jsp/feeds.jsp").forward(request, response);
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
