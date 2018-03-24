package xml.parse;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import xml.abstractions.Parser;
import xml.enums.Parsers;
import xml.interfaces.IParseXML;
import utilities.Validation;

public class XMLParserHelper implements IParseXML{

	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	private String searchTerm;
	
	public XMLParserHelper() {
		this("");
	}
	
	public XMLParserHelper(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see pm1.interfaces.IParseXML#parse(java.io.InputStream, pm1.enums.Parsers)
	 */
	@Override
	public String parse(InputStream xml, Parsers parserType) {

		String output = null;
		Parser parser = null;
		boolean noSearchTerm = Validation.isNotValidString(searchTerm);
		
		switch (parserType) {

		case DOM: {
			
			if (noSearchTerm) {
				LOGGER.log(Level.INFO, "Parsing using DOM parser.");
				parser = new ParseDOM();
			} else {
				LOGGER.log(Level.INFO, "Parsing and searching using XPath and DOM parser.");
				parser = new ParseXPATH(searchTerm);
			}
			
		}
			break;

		case SAX: {
			
			if (noSearchTerm) {
				LOGGER.log(Level.INFO, "Parsing using SAX parser.");
				parser = new ParseSAX();
			} else {
				LOGGER.log(Level.INFO, "Parsing and searching using SAX parser.");
				parser = new ParseSAX(searchTerm);
			}
		}
			break;

		default:
			LOGGER.log(Level.WARNING, "Invalid logger type.");
			
		}
		
		if (parser != null) {
			output = parser.parse(xml);
		}

		return output;
	}
}
