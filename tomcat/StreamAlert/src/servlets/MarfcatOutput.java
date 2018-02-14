

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import marfcat.Report;

/**
 * Servlet implementation class MarfcatInput
 */
@WebServlet("/MarfcatOutput")
public class MarfcatOutput extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarfcatOutput() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = request.getServletContext();
		InputStream in = context.getResourceAsStream("/WEB-INF/xml/marfcat-out.xml");
		
		Logger logger = Logger.getLogger("XmlParser");
		BasicConfigurator.configure();
		Scanner s = null;
		
		try {
			s = new Scanner(in).useDelimiter("\\A");
			String xmlString = s.hasNext() ? s.next() : "";
			Report rp = XmlParser.unmarshal(xmlString, Report.class);
			
			request.setAttribute("report-tool_name", rp.toolName);
			request.setAttribute("report-tool_version", rp.toolVersion);
			request.setAttribute("weaknesses", rp.weaknesses);
			
			response.setContentType("text/html");
	        request.getRequestDispatcher("/WEB-INF/jsp/marfcat-out.jsp").forward(request, response);
		}
		catch(Exception e) {
			response.setContentType("text/html");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			request.setAttribute("message", sw.toString());
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
		finally {
			s.close();
		}
	}
}
