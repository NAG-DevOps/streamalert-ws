/**
 * 
 */
package pm1.parse;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author AlexGenio
 *
 */
public class ParseSAX {

	private SAXParserFactory factory;
    private SAXParser saxParser;
    private SAXHandler handler;
    
	/**
	 * Constructor for CustomSAXParser
	 */
	public ParseSAX() {
		super();
		
		try {
            this.factory = SAXParserFactory.newInstance();
            this.saxParser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * Uses a SAX parser to parse XML with an optional searchTerm
	 * @param xml
	 * @param searchTerm
	 * @return Resulting output of parsing XML
	 */
	public String parse(InputStream xml, String searchTerm) {
		try {
			this.handler = new SAXHandler(searchTerm);
			this.saxParser.parse(xml, this.handler);
			return this.handler.getValue().toString();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @author AlexGenio
	 *
	 *	Inner class that is a custom implementation of a SAX
	 *  parser handler. This class allows for elements and attributes
	 *  to be store in name:value pairs. If a search term is specified,
	 *  elements matching the search term and its contents will be stored.
	 */
	public class SAXHandler extends DefaultHandler{

		private StringBuilder value;
		private String searchTerm;
		private Boolean matchedSearch;
		
		/**
		 * Contructor for SAXHandler
		 * @param searchTerm
		 */
		public SAXHandler(String searchTerm) {
			this.value = new StringBuilder();
			this.searchTerm = searchTerm;
			this.matchedSearch = false;
		}
	    
	    @Override
	    public void startElement(String uri, String localName,
	            String qName,Attributes attributes)
	            throws SAXException {
	    	
	    	// no search term, just store elements and name value attribute pairs
	    	if (this.searchTerm.isEmpty()) {
		        this.value.append(qName + ":\n");
		        
		        for (int i = 0; i < attributes.getLength(); i++){
		        	this.value.append(attributes.getQName(i) + ":" + attributes.getValue(i) + "\n");
		        }
	    	} else {
	    		
	    		// search term was specified so only store that element
	    		if (qName.equals(this.searchTerm)) {
	    			this.matchedSearch = true;
	    			this.value.append(qName + ":");
	    		}
	    	}
	    }

	    @Override
	    public void endElement(String uri, String localName,
	            String qName) throws SAXException {
	    	
	    	// always store end tag if no seach term is present
	    	if (this.searchTerm.isEmpty()) {
	    		this.value.append(qName + "\n");
	    	} else {
	    		
	    		// only store end tags that match the search term
	    		if (qName.equals(this.searchTerm)) {
	    			this.matchedSearch = false;
	    		}
	    	}
	    }

	    @Override
	    public void characters(char ch[], int start, int length)
	            throws SAXException {
	    	
	    	// only store element content when an element matching the search
	    	// term was encountered
	    	if (this.matchedSearch) {
	    		this.value.append(new String(ch, start, length));
	    	}
	    }

	    /**
	     * Gets the parsed XML output
	     * @return Parsed XML output
	     */
	    public StringBuilder getValue() {
	    	return this.value;
	    }
		
	}
	
}
