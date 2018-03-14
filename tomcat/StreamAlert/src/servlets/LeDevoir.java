import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Servlet implementation class LeDevoir
 */
@WebServlet("/LeDevoir")
public class LeDevoir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeDevoir() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ArrayList<String> parsedStrings = DOMParser.parse("http://www.ledevoir.com/rss/edition_complete.xml",
					"title");
			request.setAttribute("parsedStrings", parsedStrings);
		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/jsp/le_devoir.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ArrayList<String> parsedStrings = new ArrayList<String>();
			String uri = (String) request.getAttribute("uri");
			if (uri == null || uri.equals("")) {
				parsedStrings = DOMParser.parse("http://www.ledevoir.com/rss/edition_complete.xml", "title");
				request.setAttribute("parsedStrings", parsedStrings);
			} else {
				parsedStrings = DOMParser.parse(uri, "title");
				request.setAttribute("parsedStrings", parsedStrings);
			}
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			ArrayNode titlesNode = mapper.createArrayNode();
			ArrayNode titles = mapper.valueToTree(parsedStrings);
			for (int i = 0; i < titles.size(); i++) {
				ObjectNode em = mapper.createObjectNode();
				em.put("title", titles.get(i).toString());
				titlesNode.add(em);
			}
			node.putArray("titles").addAll(titlesNode);
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parsedStrings);
			JsonNode jsonNode = mapper.readValue(jsonPretty, JsonNode.class);
			System.out.println(jsonNode);
			AWSUtils awsUtils = new AWSUtils("soen487.streamalerts", "us-east-1");
			awsUtils.putJSON(node.toString(), "le-devoir.json");
			// Send XML data to Slack #streamalert channel
		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/jsp/le_devoir.jsp").forward(request, response);
	}
}
