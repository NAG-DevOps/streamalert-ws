import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser {
	
	/**
	 * Parse the content of the XML document using the given tag name at the given URI
	 * and return an ArrayList of String
	 * 
	 * @param uri Location of content to be parsed
	 * @param tagName The tag name to match on during parsing
	 * @return An ArrayList containing the contents of all elements that match tagName from the XML document
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static ArrayList<String> parse(String uri, String tagName) throws ParserConfigurationException, IOException, SAXException {
	    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

	    Document document = documentBuilder.parse(uri);
	    
	    NodeList nodeList = document.getElementsByTagName(tagName); 
	    ArrayList<String> parsedStrings = new ArrayList<>();
	    
	    for (int i = 0; i < nodeList.getLength(); i++) {
	        Node node = nodeList.item(i);
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	            parsedStrings.add(node.getFirstChild().getNodeValue());
	        }
	    }
	    return parsedStrings;
	}
}
