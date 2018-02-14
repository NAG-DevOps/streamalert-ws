

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

import marfcat.Dataset;

/**
 * Servlet implementation class MarfcatInput
 */
@WebServlet("/MarfcatInput")
public class MarfcatInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarfcatInput() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = request.getServletContext();
		InputStream in = context.getResourceAsStream("/WEB-INF/xml/marfcat-in.xml");
		Scanner s = null;
		
		try {
			s = new Scanner(in).useDelimiter("\\A");
			String xmlString = s.hasNext() ? s.next() : "";
			Dataset ds = XmlParser.unmarshal(xmlString, Dataset.class);
			
			request.setAttribute("dataset-generated-by", ds.generatedBy);
			request.setAttribute("dataset-generated-on", ds.generatedOn);
			request.setAttribute("description-file-type-tool", ds.description.fileTypeTool);
			request.setAttribute("description-find-tool", ds.description.findTool);
			request.setAttribute("description-marfcat-tool", ds.description.marfTool);
			request.setAttribute("files", ds.files);
			
			response.setContentType("text/html");
	        request.getRequestDispatcher("/WEB-INF/jsp/marfcat-in.jsp").forward(request, response);
		}
		catch(Exception e) {
			response.setContentType("text/html");
			request.setAttribute("message", e.getMessage());
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
		finally {
			s.close();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = (String) request.getAttribute("uri");
		String xmlString = "";
		
		if(uri == null || uri.equals("")) {
			ServletContext context = request.getServletContext();
			InputStream in = context.getResourceAsStream("/WEB-INF/xml/marfcat-in.xml");
			Scanner s = null;
			s = new Scanner(in).useDelimiter("\\A");
			xmlString = s.hasNext() ? s.next() : "";
			s.close();
		}
		else {
			try {
				xmlString = Requests.get(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Dataset ds = XmlParser.unmarshal(xmlString, Dataset.class);
			request.setAttribute("dataset-generated-by", ds.generatedBy);
			request.setAttribute("dataset-generated-on", ds.generatedOn);
			request.setAttribute("description-file-type-tool", ds.description.fileTypeTool);
			request.setAttribute("description-find-tool", ds.description.findTool);
			request.setAttribute("description-marfcat-tool", ds.description.marfTool);
			request.setAttribute("files", ds.files);
			response.setContentType("text/html");
	        request.getRequestDispatcher("/WEB-INF/jsp/marfcat-in.jsp").forward(request, response);
		}
		catch(NullPointerException e) {
			response.setContentType("text/html");
			request.setAttribute("message", "Cannot parse xml located at: " + uri);
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
		catch(Exception e) {
			response.setContentType("text/html");
			request.setAttribute("message", e.getMessage());
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}
}
