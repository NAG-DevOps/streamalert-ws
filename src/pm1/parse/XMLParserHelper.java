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
	 * @see pm1.interfaces.IParseXML#parse(java.io.InputStream)
	 */
	@Override
	public String parse(InputStream xml, Parsers parserType) {

		String output = null;

		switch (parserType) {

		case DOM:
			break;

		case SAX: {
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
