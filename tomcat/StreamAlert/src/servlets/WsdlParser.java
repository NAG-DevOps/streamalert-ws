import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.w3c.dom.*;


/**
 * Servlet implementation class WsdlParser
 */
@WebServlet("/WsdlParser")
public class WsdlParser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WsdlParser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		ServletContext context = request.getServletContext();

		String url = "https://raw.githubusercontent.com/smokhov/atsm/master/examples/ws/soap/faultmessage/faultSample.wsdl";
		String str = "";
		String totalString = "";
		Logger logger = Logger.getLogger("XmlParser");
		BasicConfigurator.configure();
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(url);
			doc.getDocumentElement().normalize();
			totalString = doc.getDocumentElement().getNodeName() + "<br>";

			if (doc.hasChildNodes()) {
				str = XmlParser.printNode(doc.getChildNodes());
			}
			totalString += str;
			request.setAttribute("wsdlContent", totalString);
			response.setContentType("text/html");
			request.getRequestDispatcher("/WEB-INF/jsp/wsdl.jsp").forward(request, response);
		}
		catch (Exception e) {
			response.setContentType("text/html");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			request.setAttribute("message", sw.toString());
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
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
