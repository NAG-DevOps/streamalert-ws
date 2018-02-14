
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

import neural_network.Net;

/**
 * Servlet implementation class NeuralNetwork
 */
@WebServlet("/NeuralNetwork")
public class NeuralNetwork extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NeuralNetwork() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		InputStream in = context.getResourceAsStream("/WEB-INF/xml/neural-network.xml");
		Scanner s = null;
		try {
			s = new Scanner(in).useDelimiter("\\A");
			String xmlString = s.hasNext() ? s.next() : "";
			Net neuralNetwork = XmlParser.unmarshal(xmlString, Net.class);
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(neuralNetwork);
			System.out.println(json);
			request.setAttribute("neuralNetwork", json);
			response.setContentType("text/html");
	        request.getRequestDispatcher("/WEB-INF/jsp/neural-network.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = (String) request.getAttribute("uri");
		String xmlString = "";
		
		if(uri == null || uri.equals("")) {
			ServletContext context = request.getServletContext();
			InputStream in = context.getResourceAsStream("/WEB-INF/xml/neural-network.xml");
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
			Net neuralNetwork = XmlParser.unmarshal(xmlString, Net.class);
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(neuralNetwork);
			request.setAttribute("neuralNetwork", json);
			response.setContentType("text/html");
	        request.getRequestDispatcher("/WEB-INF/jsp/neural-network.jsp").forward(request, response);
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
