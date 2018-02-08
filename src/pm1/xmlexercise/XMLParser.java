/**
 * 
 */
package pm1.xmlexercise;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import pm1.enums.XMLEnums.Markup;
import pm1.enums.XMLEnums.Parsers;
import pm1.interfaces.IParseXML;
import pm1.parse.*;
import utilities.Validation;

/**
 * @author AlexGenio
 *
 */
public class XMLParser {

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	private Map<Markup, String> defaultUriMap = new HashMap<Markup, String>() {
		{
			put(Markup.RSS, "http://www.ledevoir.com/rss/edition_complete.xml");
			put(Markup.NN, "https://users.encs.concordia.ca/~yzs487_4/xml/test1.xml");
			put(Markup.MARFCAT_IN, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-in.xml");
			put(Markup.MARFCAT_OUT, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-out.xml");
			put(Markup.WSDL, "https://raw.githubusercontent.com/smokhov/atsm/master/examples/ws/soap/faultmessage/faultSample.wsdl");
		}
	};

	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "Parsing the default URIs...");
		
		XMLParser parser = new XMLParser();
		parser.parseXML(Markup.RSS, "", Parsers.SAX, "title");
		parser.parseXML(Markup.NN, "", Parsers.SAX, "");
		parser.parseXML(Markup.MARFCAT_IN, "", Parsers.SAX, "");
		parser.parseXML(Markup.MARFCAT_OUT, "", Parsers.SAX, "");
		parser.parseXML(Markup.WSDL, "", Parsers.SAX, "");
	}

	/**
	 * Creates an XML input stream for a given markup and URI. If the URI is
	 * empty or null, it uses the default URIs.
	 * 
	 * @param markup
	 * @param uri
	 */
	public void parseXML(Markup markup, String uri, Parsers parser, String searchTerm) {

		if (Validation.isNotValidString(uri))
			uri = defaultUriMap.get(markup);

		InputStream xml = null;

		try {
			HttpURLConnection connection = getHTTPConnection(uri);
			xml = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (xml != null) {
			switch (markup) {
			case RSS:
				parseRSSXML(xml, searchTerm);
				break;
			case NN:
				parseNNXML(xml, parser);
				break;
			case MARFCAT_IN:
				parseMARFCATINXML(xml, parser);
				break;
			case MARFCAT_OUT:
				parseMARFCATOUTXML(xml, parser);
				break;
			case WSDL:
				parseWSDLXML(xml, parser);
				break;
			default:
				LOGGER.log(Level.SEVERE, "Invalid markup was entered.");
			}
		} else {
			LOGGER.log(Level.SEVERE, "Could not open the url for xml parsing.");
		}
	}

	/**
	 * Establish an HTTP GET Connection
	 * 
	 * @param uri
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	private HttpURLConnection getHTTPConnection(String uri)
			throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");
		return connection;
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseRSSXML(InputStream xml, String searchTerm) {
		LOGGER.log(Level.INFO, "About to parse RSS XML!");
		XMLSearchHelper parser = new XMLSearchHelper();
		logParseResult(parser.search(xml, searchTerm), "RSSXML");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseNNXML(InputStream xml, Parsers parserType) {
		LOGGER.log(Level.INFO, "About to parse NN XML!");
		XMLParserHelper parser = new XMLParserHelper();
		logParseResult(parser.parse(xml, parserType), "NNXML");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseMARFCATINXML(InputStream xml, Parsers parserType) {
		LOGGER.log(Level.INFO, "About to parse MARFCATIN XML!");
		XMLParserHelper parser = new XMLParserHelper();
		logParseResult(parser.parse(xml, parserType), "MARFCATIN");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseMARFCATOUTXML(InputStream xml, Parsers parserType) {
		LOGGER.log(Level.INFO, "About to parse MARFCATOUT XML!");
		XMLParserHelper parser = new XMLParserHelper();
		logParseResult(parser.parse(xml, parserType), "MARFCATOUT");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseWSDLXML(InputStream xml, Parsers parserType) {
		LOGGER.log(Level.INFO, "About to parse WSDL XML!");
		XMLParserHelper parser = new XMLParserHelper();
		logParseResult(parser.parse(xml, parserType), "WSDL");
	}

	/**
	 * 
	 * @param result
	 * @param type
	 */
	private void logParseResult(String result, String type) {
		if (result != null)
			System.out.println(result);
		else
			LOGGER.log(Level.SEVERE, String.format("Could not parse %s XML", type));
	}

}
