import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<String> parsedStrings = DOMParser.parse("http://www.ledevoir.com/rss/edition_complete.xml", "title");
			request.setAttribute("parsedStrings", parsedStrings);
		} catch (ParserConfigurationException | IOException| SAXException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/jsp/le_devoir.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String uri = (String)request.getAttribute("uri");
			if(uri == null || uri.equals("")) {
				ArrayList<String> parsedStrings = DOMParser.parse("http://www.ledevoir.com/rss/edition_complete.xml", "title");
				request.setAttribute("parsedStrings", parsedStrings);
			}
			else {
				ArrayList<String> parsedStrings = DOMParser.parse(uri, "title");
				request.setAttribute("parsedStrings", parsedStrings);
			}
		} catch (ParserConfigurationException | IOException| SAXException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/jsp/le_devoir.jsp").forward(request, response);
	}
}
