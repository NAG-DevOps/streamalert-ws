
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {

	/**
	 * Converts content tree into a string
	 * @param toMarshal Object to marshal
	 * @param clazz Target class to cast
	 * @return Formatted string containing the object's content tree
	 */
	public static <T> String marshal(Object toMarshal, Class<T> clazz) {

		StringWriter sw = new StringWriter();
		Logger logger = Logger.getLogger("XmlParser");
		BasicConfigurator.configure();

		try {
			T castedObject = (T)toMarshal;
			JAXBContext jaxbContext = JAXBContext.newInstance(castedObject.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(toMarshal, sw);
			return sw.toString();
		}
		catch (JAXBException e) {
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info(sw.toString());
			return null;
		}
		catch (Exception e) {
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info(sw.toString());
			return null;
		}

	}

	/**
	 * Converts XML into the appropriate content tree 
	 * @param xmlToUnmarshal XML string
	 * @param clazz Target class to cast the XML
	 * @return Casted object
	 */
	public static <T> T unmarshal(String xmlToUnmarshal, Class<T> clazz) {

		StringReader sr = new StringReader(xmlToUnmarshal);
		StringWriter sw = new StringWriter();
		Logger logger = Logger.getLogger("XmlParser");
		BasicConfigurator.configure();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (T) unmarshaller.unmarshal(sr);
		}
		catch (JAXBException e) {
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info(sw.toString());
			return null;
		}
		catch (Exception e) {
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info(sw.toString());
			return null;
		}
	}

	/**
	 * Prints names and values of nodes from a list
	 * @param nodeList List of nodes
	 * @return Formatted string containing all names and values
	 */
	public static String printNode(NodeList nodeList) {
		String toRet = "";
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodes = nodeList.item(i);
			if (nodes.getNodeType() == Node.ELEMENT_NODE) {
				if (nodes.hasAttributes()) {
					NamedNodeMap nodeMap = nodes.getAttributes();
					for (int j = 0; j < nodeMap.getLength(); j++) {
						Node node = nodeMap.item(j);
						toRet = toRet + node.getNodeName()+":"+node.getNodeValue() + "<br>";
					}
				}
				if (nodes.hasChildNodes()) {
					toRet += printNode(nodes.getChildNodes());
				}
			}
		}
		return toRet;
	}     
}
