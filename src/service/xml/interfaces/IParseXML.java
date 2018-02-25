/**
 * 
 */
package xml.interfaces;

import java.io.InputStream;

import xml.enums.Parsers;

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
