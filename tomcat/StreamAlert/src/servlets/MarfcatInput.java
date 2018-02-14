

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

//			request.setAttribute("file-id", f.id);
//			request.setAttribute("file-path", f.path);
//			request.setAttribute("meta-type", f.meta.type);
//			request.setAttribute("number-lines", f.meta.length.lines);
//			request.setAttribute("number-words", f.meta.length.words);
//			request.setAttribute("number-bytes", f.meta.length.bytes);
//			request.setAttribute("location-line", f.location.line);
//			request.setAttribute("location-fraglines", f.location.fraglines);
//			request.setAttribute("location-fragment", f.location.fragment);
//			request.setAttribute("location-explanation", f.location.explanation);
			
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
		
		if(uri == null || uri.equals("")) {
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
		else {
			try {
				String marfcatInputUriString = Requests.get(uri);
				Dataset ds = XmlParser.unmarshal(marfcatInputUriString, Dataset.class);
				if(ds == null) {
					throw new Exception("Cannot unmarshal file located at: " + uri);
				}
				request.setAttribute("dataset-generated-by", ds.generatedBy);
				request.setAttribute("dataset-generated-on", ds.generatedOn);
				request.setAttribute("description-file-type-tool", ds.description.fileTypeTool);
				request.setAttribute("description-find-tool", ds.description.findTool);
				request.setAttribute("description-marfcat-tool", ds.description.marfTool);
				request.setAttribute("files", ds.files);
				
				response.setContentType("text/html");
		        request.getRequestDispatcher("/WEB-INF/jsp/marfcat-in.jsp").forward(request, response);
			} 
			catch (Exception e) {
				response.setContentType("text/html");
				request.setAttribute("message", e.getMessage());
		        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			}
		}
		
	}
}
