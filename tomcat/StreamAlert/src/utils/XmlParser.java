
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

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
}
