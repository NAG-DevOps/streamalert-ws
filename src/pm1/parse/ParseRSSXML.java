/**
 * 
 */
package pm1.parse;

import java.io.InputStream;

import pm1.interfaces.IParseXML;

/**
 * @author miqdadamirali
 *
 */
public class ParseRSSXML implements IParseXML {

	/* (non-Javadoc)
	 * @see pm1.interfaces.IParseXML#parse(java.io.InputStream)
	 */
	@Override
	public String parse(InputStream xml) {

		// parse with a SAX parser using a search term of title
		CustomSAXParser parser = new CustomSAXParser();
		String result = parser.parse(xml, "title");
	
		return result;
	}

}
