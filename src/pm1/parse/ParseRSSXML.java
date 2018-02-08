/**
 * 
 */
package pm1.parse;

import java.io.InputStream;

import pm1.interfaces.IParseXML;
import pm1.parse.ParseXPATH;

/**
 * @author miqdadamirali
 * @author ryanhotton
 */
public class ParseRSSXML implements IParseXML {

	private final String TITLE = "title";

	/*
	 * (non-Javadoc)
	 * 
	 * @see pm1.interfaces.IParseXML#parse(java.io.InputStream)
	 */
	@Override
	public String parse(InputStream xml) {

		ParseXPATH pXPath = new ParseXPATH();

		// If XPath is selected - right now it is default
		return pXPath.parseXPath(xml, TITLE);
	}

}
