package pm1.parse;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import pm1.enums.XMLEnums.Parsers;
import pm1.interfaces.IParseXML;

public class XMLParserHelper implements IParseXML{

private static Logger LOGGER = Logger.getLogger("InfoLogging");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see pm1.interfaces.IParseXML#parse(java.io.InputStream, pm1.enums.Parsers)
	 */
	@Override
	public String parse(InputStream xml, Parsers parserType) {

		String output = null;

		switch (parserType) {

		case DOM: {
			LOGGER.log(Level.INFO, "Parsing using DOM parser.");
			
			// Parse with a DOM parser without a search term
			ParseDOM parser = new ParseDOM();
			output = parser.parse(xml);
		}
			break;

		case SAX: {
			LOGGER.log(Level.INFO, "Parsing using SAX parser.");
			
			// Parse with a SAX parser without a search term
			ParseSAX parser = new ParseSAX();
			output = parser.parse(xml, "");
		}
			break;

		default:
			LOGGER.log(Level.WARNING, "Invalid logger type.");
			
		}

		return output;
	}
}
