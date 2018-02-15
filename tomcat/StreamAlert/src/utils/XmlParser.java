
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
	
	public static String printNode(NodeList nodeList) {
		String toRet = "";
		System.out.println("in the method of printNode");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodes = nodeList.item(i);
			if (nodes.getNodeType() == Node.ELEMENT_NODE) {
				if (nodes.hasAttributes()) {
					// Getting name:value pairs
					NamedNodeMap nodeMap = nodes.getAttributes();
					for (int j = 0; j < nodeMap.getLength(); j++) {
						Node node = nodeMap.item(j);
						toRet = toRet + node.getNodeName()+":"+node.getNodeValue() + "\n";
						System.out.println(node.getNodeName()+":"+node.getNodeValue());
					}
				}
				if (nodes.hasChildNodes()) {
					// Loop again to check for child nodes
					toRet += printNode(nodes.getChildNodes());
				}
			}
		}
		return toRet;
	}     
}
