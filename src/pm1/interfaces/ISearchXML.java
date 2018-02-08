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
public interface ISearchXML {
	
	/**
	 * Parse XML
	 * @param xml
	 */
	public String search(InputStream xml, String searchTerm);
}
