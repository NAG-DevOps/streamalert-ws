/**
 * 
 */
package pm1.interfaces;

import java.io.InputStream;

import pm1.enums.XMLEnums.Parsers;

/**
 * @author AlexGenio
 *
 */
public interface ISearchXML {
	
	/**
	 * Searches XML for a given search term.
	 * 
	 * @param xml
	 * @param searchTerm
	 */
	public String search(InputStream xml, String searchTerm);
}
