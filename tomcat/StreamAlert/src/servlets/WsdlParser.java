

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
//import javax.wsdl.Message;
//import javax.wsdl.Service;
import javax.xml.parsers.ParserConfigurationException;

import wsdl.ComplexType;
import wsdl.Definitions;
import wsdl.Import;
import wsdl.Schema;
import wsdl.Sequence;
import wsdl.Message;
import wsdl.Element;
import wsdl.ObjectFactory;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
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
		
		//		InputStream in = context.getResourceAsStream("/WEB-INF/wsdl/faultSample.wsdl");
		//		
		//		Logger logger = Logger.getLogger("XmlParser");
		//		BasicConfigurator.configure();
		//		Scanner s = null;
		//		
		//		try {
		//			s = new Scanner(in).useDelimiter("\\A");
		//			String wsdlString = s.hasNext() ? s.next() : "";
		//			
		////			Definitions def = XmlParser.unmarshal(wsdlString, Definitions.class);
		//
		//			Element element = XmlParser.unmarshal(wsdlString, Element.class);
		//			System.out.println("Element.getname = " + element.getName());
		//			System.out.println("Element.getValue = " + element.getValue());
		////			Schema schema = XmlParser.unmarshal(wsdlString, Schema.class);
		////			System.out.println("Schema = " + schema);
		//			ComplexType ct = XmlParser.unmarshal(wsdlString, ComplexType.class);
		//			System.out.println("ct = " + ct);
		////			Sequence seq = XmlParser.unmarshal(wsdlString, Sequence.class);
		////			System.out.println("seq= " + seq);
		////			Import imp = XmlParser.unmarshal(wsdlString, Import.class);
		////			System.out.println("imp= " + imp);
		////			ObjectFactory of = XmlParser.unmarshal(wsdlString, ObjectFactory.class);
		////			System.out.println("of= " + of);
		//			
		//			request.setAttribute("elementName", element.getName());
		//			request.setAttribute("elementName", element.getValue());
		//			
		//			response.getWriter().append("Served at: ").append(request.getContextPath());
		//			response.setContentType("text/html");
		//			request.getRequestDispatcher("/WEB-INF/jsp/wsdl.jsp").forward(request, response);
		//		}
		//		catch(Exception e) {
		//			e.printStackTrace();
		//			response.setContentType("text/html");
		//			StringWriter sw = new StringWriter();
		//			PrintWriter pw = new PrintWriter(sw);
		//			e.printStackTrace(pw);
		//			
		//			request.setAttribute("message", sw.toString());
		//	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);

		//		}


		String url = "https://raw.githubusercontent.com/smokhov/atsm/master/examples/ws/soap/faultmessage/faultSample.wsdl";
		String str = "";
		Logger logger = Logger.getLogger("XmlParser");
		BasicConfigurator.configure();
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(url);
			// Helps to structure data if content in XML is not properly formatted
			doc.getDocumentElement().normalize();
//			System.out.println("test getting the node");
//			System.out.println(doc.getDocumentElement().getNodeName());

			if (doc.hasChildNodes()) {
//				System.out.println("printNode");
				str = XmlParser.printNode(doc.getChildNodes());
			}
			logger.info("str is " + str);
			request.setAttribute("elementName", str);
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
