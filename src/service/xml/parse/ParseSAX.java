/**
 * 
 */
package xml.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import xml.abstractions.Parser;
import utilities.Validation;

/**
 * @author AlexGenio
 *
 */
public class ParseSAX extends Parser{
	
	private SAXParserFactory factory;
	private SAXParser saxParser;
	private SAXHandler handler;

	/**
	 * Constructor for CustomSAXParser
	 */
	public ParseSAX() {
		this("");
	}
	
	/**
	 * Overloaded constructor for CustomSAXParser
	 */
	public ParseSAX(String searchTerm) {
		super(searchTerm);
		
		try {
			this.factory = SAXParserFactory.newInstance();
			this.saxParser = factory.newSAXParser();
		} catch (Exception e) {
			getLogger().log(Level.WARNING, e.getMessage());
		}
		
	}

	/**
	 * Uses a SAX parser to parse XML with an optional searchTerm
	 * 
	 * @param xml
	 * @param searchTerm
	 * @return Resulting output of parsing XML
	 */
	public String parse(InputStream xml) {
		String output = null;
		try {
			this.handler = new SAXHandler(getSearchTerm());
			this.saxParser.parse(xml, this.handler);
			output = this.handler.getValue().toString();
		} catch (SAXException | IOException e) {
			getLogger().log(Level.WARNING, e.getMessage());
		}

		return output;
	}

	/**
	 * 
	 * @author AlexGenio
	 *
	 *         Inner class that is a custom implementation of a SAX parser
	 *         handler. This class allows for elements and attributes to be
	 *         store in name:value pairs. If a search term is specified,
	 *         elements matching the search term and its contents will be
	 *         stored.
	 */
	public class SAXHandler extends DefaultHandler {

		// Document parsing with InputStream - Adapted from:
		// https://github.com/smokhov/atsm/blob/master/examples/ws/XML/XMLParsing/src/SAXSample.java
		
		private StringBuilder value;
		private String searchTerm;
		private Boolean matchedSearch;

		/**
		 * Constructor for SAXHandler
		 * 
		 * @param searchTerm
		 */
		public SAXHandler(String searchTerm) {
			this.value = new StringBuilder();
			this.searchTerm = searchTerm;
			this.matchedSearch = false;
		}

		@Override
		public void characters(char ch[], int start, int length) throws SAXException {

			// Only store element content when an element matching the search
			// term was encountered
			if (this.matchedSearch) {
				this.value.append(new String(ch, start, length));
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {

			// Always store end tag if no search term is present
			if (Validation.isNotValidString(this.searchTerm)) {
				this.value.append(qName + "\n");
			} else {
				// only store end tags that match the search term
				if (qName.equals(this.searchTerm)) {
					this.matchedSearch = false;
					this.value.append("\n");
				}
			}
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {

			// No search term, just store elements and name value attribute
			// pairs
			if (Validation.isNotValidString(this.searchTerm)) {
				this.value.append(qName + ":\n");

				for (int i = 0; i < attributes.getLength(); i++) {
					this.value.append(attributes.getQName(i) + " = " + attributes.getValue(i) + "\n");
				}
			} else {

				// Search term was specified so only store that element
				if (qName.equals(this.searchTerm)) {
					this.matchedSearch = true;
					this.value.append(qName + ":");
				}
			}
		}

		/**
		 * Gets the parsed XML output
		 * 
		 * @return Parsed XML output
		 */
		public StringBuilder getValue() {
			return this.value;
		}

	}

}
