package pm1.xmlexercise;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;

//TODO is it better to get all tags and loop instead of recursive? https://examples.javacodegeeks.com/core-java/xml/dom/visit-all-elements-in-dom-document/
//TODO change input type-not file but url
/**
 * @Author Sophia Kavousifard
 */
public class DomParser {
	private DocumentBuilderFactory docBuilderFactory;
	private  DocumentBuilder docBuilder;
	
	/**
	 * Default constructor for DomParser
	 * @param docBuilderFactory
	 */
	public DomParser() {
		super();
		
		try {
			this.docBuilderFactory = docBuilderFactory.newInstance();
			this.docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parses the source and gets ready to parse the nodes
	 */
	public  void parseDom() {
		try {
			File fXmlFile = new File("src/pm1/xmlexercise/file.xml");
			Document doc = docBuilder.parse(fXmlFile);	
			findChildNodes(doc);
			//TODO .normalize()
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Iterates through each node and prints out attribute-value pairs
	 * @param doc object that has parsed file
	 */
	public  void findChildNodes(Document doc) {
		NodeList entries = doc.getElementsByTagName("*");
 
		//iterating through each tag
		for(int i=0; i < entries.getLength(); i++) {
			Element tag = (Element) entries.item(i);
			System.out.println(tag.getNodeName() + ":");
			//TODO if multiple attributes print all
			Node attribute = tag.getAttributes().item(0);
			
			//current node has an attribute
			if(attribute != null) {
				System.out.println("\t" + attribute.getNodeName() + "-" + attribute.getNodeValue());
			}
		}
		
		//TODO indentation as go in each parent-child node
		
//		//base case reached most inner child node
//		if(!node.hasChildNodes()) {
//			System.out.println("Most inner child node: " + node.getNodeName());
//			Element tag = (Element)node;
//			String attribute = tag.getAttribute("*");
//			System.out.println("Attribute: " + attribute);
//			return;
//		}
//		
//		String nodeName = node.getNodeName();
//	    NodeList nodeList = node.getChildNodes();
//	    
//	    //for every child node iterate through
//	    for (int i = 0; i < nodeList.getLength(); i++) {
//	        Node currentNode = nodeList.item(i);
//	        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
//	            //calls this method for all the children which is Element
//	            System.out.println("Current Parent Node: " + currentNode.getNodeName());
//	            System.out.println("With its current attributes-value pair: " + currentNode.ATTRIBUTE_NODE);
//	            System.out.println("Calling recursive method now...");
//	            System.out.println();
//	        	findChildNodes(currentNode);
//	        }
//	    }
	}
}
