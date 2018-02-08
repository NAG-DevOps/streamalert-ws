/**
 * 
 */
package pm1.interfaces;

import java.io.InputStream;

import pm1.enums.XMLEnums.Parsers;

/**
 * @author miqdadamirali
 *
 */
public interface IParseXML {
	
	/**
	 * Parse XML
	 * @param xml
	 */
	public String parse(InputStream xml, Parsers parserType);

}
