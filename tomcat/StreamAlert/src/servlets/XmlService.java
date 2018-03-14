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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
			InputStream inStream = request.getInputStream();
			SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), inStream);
			SOAPBody soapBody = soapMessage.getSOAPBody();
			NodeList types = soapBody.getElementsByTagName("type");
			NodeList uris = soapBody.getElementsByTagName("uri");
			PrintWriter writer = response.getWriter();
			Node typeNode = types.item(0);
			Node uriNode = uris.item(0);

			String type = typeNode != null ? typeNode.getTextContent() : "";
			String uri = uriNode != null ? uriNode.getTextContent() : "";

			RequestDispatcher rd = null;
			switch (type) {
			case ("marfcat-input"):
				rd = request.getRequestDispatcher("MarfcatInput");
				break;

			case ("marfcat-output"):
				rd = request.getRequestDispatcher("MarfcatOutput");
				break;

			case ("le-devoir"):
				rd = request.getRequestDispatcher("LeDevoir");
				break;

			case ("neural-network"):
				rd = request.getRequestDispatcher("NeuralNetwork");
				break;

			case ("wsdl"):
				rd = request.getRequestDispatcher("WsdlParser");
				break;

			default:
				writer.println("No type exists for specified type: " + type);
			}
			if (rd != null) {
				request.setAttribute("uri", uri);
				rd.forward(request, response);
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
