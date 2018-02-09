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

		// must have markup type, url, parser type, and search term
		// url and search term can be empty however
		if (args.length < 2 || args.length > 4) {
			LOGGER.log(Level.SEVERE, "Usage: program [Markup] [ParserType] (URL) (SearchTerm)");
			System.exit(0);
		}
		
		// read the passed arguments
		Markup markup = null;
		Parsers parserType = null;
		String uri = null;
		String searchTerm = null;
		
		try {
			int markupInt = Integer.parseInt(args[0]);
			int parserInt = Integer.parseInt(args[1]);
			
			if (markupInt < 0 || markupInt > Markup.values().length - 1) {
				LOGGER.log(Level.SEVERE, "Markup values must be 0..." + (Markup.values().length - 1));
				System.exit(0);
			}
			markup = Markup.values()[markupInt];
			
			if (parserInt < 0 || parserInt > Parsers.values().length - 1) {
				LOGGER.log(Level.SEVERE, "ParserType values must be 0..." + (Parsers.values().length - 1));
				System.exit(0);
			}
			parserType = Parsers.values()[parserInt];
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			System.exit(0);
		}
		
		if (args.length == 3) {
			uri = args[2];
		}
		
		if (args.length == 4) {
			searchTerm = args[3];
		}
		
		// parse according to what was passed in
		XMLParser parser = new XMLParser();
		parser.parseXML(markup, uri, parserType, searchTerm);
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
				logParseResult(parser.parse(xml, parserType), markup.name());
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
		if (result != null) {
			LOGGER.log(Level.INFO, String.format("Parsing %s XML", type));
			System.out.println(result);
		} else {
			LOGGER.log(Level.SEVERE, String.format("Could not parse %s XML", type));
		}
	}

}
