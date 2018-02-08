/**
 * 
 */
package pm1.parse;

import java.io.InputStream;

import pm1.enums.XMLEnums.Parsers;
import pm1.interfaces.IParseXML;
import pm1.interfaces.ISearchXML;
import pm1.parse.ParseXPATH;

/**
 * @author miqdadamirali
 * @author ryanhotton
 */
public class XMLSearchHelper implements ISearchXML {

	/*
	 * (non-Javadoc)
	 * 
	 * @see pm1.interfaces.ISearchXML#parse(java.io.InputStream)
	 */
	@Override
	public String search(InputStream xml, String searchTerm) {

		ParseXPATH pXPath = new ParseXPATH();
		return pXPath.parseXPath(xml,  searchTerm);
		
	}

}
