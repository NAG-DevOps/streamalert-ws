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

import pm1.interfaces.IParseXML;
import pm1.parse.*;
import utilities.Validation;

/**
 * @author AlexGenio
 *
 */
public class XMLParser {

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	private enum Markup {
		RSS, NN, MARFCAT_IN, MARFCAT_OUT, WSDL
	}

	private Map<Markup, String> defaultUriMap = new HashMap<Markup, String>() {
		{
			put(Markup.RSS, "http://www.ledevoir.com/rss/edition_complete.xml");
			put(Markup.NN, "https://users.encs.concordia.ca/~yzs487_4/xml/test1.xml");
			put(Markup.MARFCAT_IN, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-in.xml");
			put(Markup.MARFCAT_OUT, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-out.xml");
			put(Markup.WSDL,
					"https://raw.githubusercontent.com/smokhov/atsm/master/examples/ws/soap/faultmessage/faultSample.wsdl");
		}
	};

	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "Parsing the default URIs...");
		
		XMLParser parser = new XMLParser();
		parser.parseXML(Markup.RSS, "");
		parser.parseXML(Markup.NN, "");
		parser.parseXML(Markup.MARFCAT_IN, "");
		parser.parseXML(Markup.MARFCAT_OUT, "");
		parser.parseXML(Markup.WSDL, "");
	}

	/**
	 * Creates an XML input stream for a given markup and URI. If the URI is
	 * empty or null, it uses the default URIs.
	 * 
	 * @param markup
	 * @param uri
	 */
	public void parseXML(Markup markup, String uri) {

		if (Validation.isNotValidString(uri))
			uri = defaultUriMap.get(markup);

		InputStream xml = null;
		int xmlLength = -1;

		try {
			HttpURLConnection connection = getHTTPConnection(uri);

			// gets length of xml, -1 means file is too large or could not
			// calculate
			xmlLength = connection.getContentLength();
			/*
			 * Further implementation could include, having a smart parser which
			 * desides on using SAX for large files and using DOM for smaller
			 * ones. - Ryan
			 */

			xml = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		IParseXML parser;
		if (xml != null) {
			switch (markup) {
			case RSS:
				parseRSSXML(xml);
				break;
			case NN:
				parseNNXML(xml);
				break;
			case MARFCAT_IN:
				parseMARFCATINXML(xml);
				break;
			case MARFCAT_OUT:
				parseMARFCATOUTXML(xml);
				break;
			case WSDL:
				parseWSDLXML(xml);
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
	public void parseRSSXML(InputStream xml) {
		LOGGER.log(Level.INFO, "About to parse RSS XML!");
		ParseRSSXML parser = new ParseRSSXML();
		logParseResult(parser.parse(xml), "RSSXML");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseNNXML(InputStream xml) {
		LOGGER.log(Level.INFO, "About to parse NN XML!");
		ParseNNXML parser = new ParseNNXML();
		logParseResult(parser.parse(xml), "NNXML");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseMARFCATINXML(InputStream xml) {
		LOGGER.log(Level.INFO, "About to parse MARFCATIN XML!");
		ParseMARFCATINXML parser = new ParseMARFCATINXML();
		logParseResult(parser.parse(xml), "MARFCATIN");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseMARFCATOUTXML(InputStream xml) {
		LOGGER.log(Level.INFO, "About to parse MARFCATOUT XML!");
		ParseMARFCATOUTXML parser = new ParseMARFCATOUTXML();
		logParseResult(parser.parse(xml), "MARFCATOUT");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseWSDLXML(InputStream xml) {
		LOGGER.log(Level.INFO, "About to parse WSDL XML!");
		ParseWSDLXML parser = new ParseWSDLXML();
		logParseResult(parser.parse(xml), "WSDL");
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
