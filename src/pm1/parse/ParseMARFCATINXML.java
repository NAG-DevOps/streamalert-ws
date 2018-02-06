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
public class ParseMARFCATINXML implements IParseXML {

	/* (non-Javadoc)
	 * @see pm1.interfaces.IParseXML#parse(java.io.InputStream)
	 */
	@Override
	public String parse(InputStream xml) {
		
		// parse with a SAX parser without a search term
		CustomSAXParser parser = new CustomSAXParser();
		String result = parser.parse(xml, "");
	
		return result;
	}

}
