

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
 * Servlet implementation class Splints
 */
@WebServlet("/splints")
public class Splints extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Splints() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "https://api.github.com/repos/NAG-DevOps/splints/events";
		try {
			String getResponse = Requests.get(url);
			final JsonNode arrNode = new ObjectMapper().readTree(getResponse);
			String latestNews = null;
			if (arrNode.isArray()) {
			    for (final JsonNode objNode : arrNode) {
			        System.out.println(objNode);
			        latestNews = objNode.toString();
			        break;
			    }
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			AWSUtils awsUtils = new AWSUtils("soen487.streamalerts", "us-east-1");
			awsUtils.putJSON(latestNews, "splints.json");
			request.setAttribute("feeds", latestNews);
			response.setContentType("text/html");
	        request.getRequestDispatcher("/WEB-INF/jsp/feeds.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("feeds", e.toString());
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
