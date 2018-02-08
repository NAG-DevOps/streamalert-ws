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
			put(Markup.MARFCATIN, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-in.xml");
			put(Markup.MARFCATOUT, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-out.xml");
			put(Markup.WSDL,
					"https://raw.githubusercontent.com/smokhov/atsm/master/examples/ws/soap/faultmessage/faultSample.wsdl");
		}
	};

	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "Parsing the default URIs...");

		XMLParser parser = new XMLParser();
		parser.parseXML(Markup.RSS, "", Parsers.DOM, "title");
		//parser.parseXML(Markup.NN, "", Parsers.DOM, "");
		//parser.parseXML(Markup.MARFCATIN, "", Parsers.SAX, "");
		//parser.parseXML(Markup.MARFCATOUT, "", Parsers.SAX, "");
		//parser.parseXML(Markup.WSDL, "", Parsers.DOM, "");
	}

	/**
	 * Creates an XML input stream for a given markup, URI, parser type, and
	 * search term. If the URI is empty or null, it uses the default URIs.
	 * 
	 * @param markup
	 * @param uri
	 * @param parser
	 * @param searchTerm
	 */
	public void parseXML(Markup markup, String uri, Parsers parserType, String searchTerm) {

		if (Validation.isNotValidString(uri))
			uri = defaultUriMap.get(markup);

		InputStream xml = null;

		try {
			HttpURLConnection connection = getHTTPConnection(uri);
			xml = connection.getInputStream();
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}

		if (xml != null) {

			switch (markup) {
			case RSS:
			case NN:
			case MARFCATIN:
			case MARFCATOUT:
			case WSDL: {
				// seachTerm may or may not be empty
				XMLParserHelper parser = new XMLParserHelper(searchTerm);
				logParseResult(parser.parse(xml, parserType), parserType.name());
			}
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
	 * Outputs the result of parsing the XML file. Logs an error if
	 * unsuccessful.
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
