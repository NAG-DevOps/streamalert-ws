

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlService.XmlServicePayload;

/**
 * Servlet implementation class XmlService
 */
@WebServlet("/XmlService")
public class XmlService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XmlService() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
		    InputStream inStream = request.getInputStream();
		    SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), inStream);
		    SOAPBody soapBody = soapMessage.getSOAPBody();
		    NodeList types = soapBody.getElementsByTagName("type");
		    NodeList uris = soapBody.getElementsByTagName("uri");
		    PrintWriter writer = response.getWriter();
//		    ByteArrayOutputStream out = new ByteArrayOutputStream(); 
//		    soapMessage.writeTo(out); 
//		    String payload = new String(out.toByteArray());
		    Node typeNode = types.item(0);
		    Node uriNode = uris.item(0);
		    
		    String type = typeNode != null ? typeNode.getTextContent() : "";
		    String uri = uriNode != null ? uriNode.getTextContent() : "";

		    RequestDispatcher rd = null;
		    switch(type) {
		    	case("marfcat-input"):
		    		request.setAttribute("uri", uri);
		    		rd = request.getRequestDispatcher("MarfcatInput");
		    		rd.forward(request, response);
		    		break;
		    		
		    	case("le-devoir"):
		    		request.setAttribute("uri", uri);
		    		rd = request.getRequestDispatcher("le_devoir");
		    		rd.forward(request, response);
		    		break;
		    		
		    	default:
		    		writer.println("No type exists for specified type: " + type);
		    }
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		}
	}
}
