/**
 * 
 */
package pm1.parse;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utilities.Validation;

/**
 * @author ryanhotton
 *
 */
public class ParseXPATH {

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	/**
	 * 
	 * Parses XML using XPath and a search string.
	 * 
	 * @param xml
	 * @param searchString
	 * @return output string
	 */
	public String parseXPath(InputStream xml, String searchString) {
		LOGGER.log(Level.INFO, "About to parse XPath with search string: " + searchString);

		if (Validation.isNotValidString(searchString)) {
			return null;
		}

		StringBuilder output = new StringBuilder();

		try {
			// Document parsing with InputStream - Adapted from:
			// https://github.com/smokhov/atsm/blob/master/examples/ws/XML/XMLParsing/src/XPathSample.java
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xml);

			// XPath
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();

			String expression = "//" + searchString;

			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node ithNode = nodeList.item(i);
				// System.out.println("\n" + ithNode.getNodeName() + ":" +
				// ithNode.getTextContent());
				output.append("\n" + ithNode.getNodeName() + ":" + ithNode.getTextContent());
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}

		return output.toString();
	}
}
