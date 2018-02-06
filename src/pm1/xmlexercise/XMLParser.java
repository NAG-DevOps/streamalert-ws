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

/**
 * @author AlexGenio
 *
 */
public class XMLParser {
	
	private enum Markup {
		RSS, NN, MARFCAT_IN, MARFCAT_OUT, WSDL
	}

	private Map<Markup, String> defaultUriMap = new HashMap<Markup, String>() {
		{
			put(Markup.RSS, "http://www.ledevoir.com/rss/edition_complete.xml");
			put(Markup.NN, "https://users.encs.concordia.ca/~yzs487_4/xml/test1.xml");
			put(Markup.MARFCAT_IN, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-in.xml");
			put(Markup.MARFCAT_OUT, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-out.xml");
			put(Markup.WSDL, "http://users.encs.concordia.ca/~s487_4/examples/soap/faultmessage/faultSample.wsdl");
		}
	};

	public static void main(String[] args) {

		XMLParser parser = new XMLParser();

		System.out.println("Parsing the default URIs...\n");
		parser.parseXML(Markup.RSS, "");
		parser.parseXML(Markup.NN, "");
		parser.parseXML(Markup.MARFCAT_IN, "");
		parser.parseXML(Markup.MARFCAT_OUT, "");
		// parser.parseXML(Markup.WSDL, "");
	}

	/**
	 * Creates an XML input stream for a given markup and URI. If the URI is
	 * empty or null, it uses the default URIs.
	 * 
	 * @param markup
	 * @param uri
	 */
	public void parseXML(Markup markup, String uri) {
		
		if (uri == null || uri.trim().isEmpty())
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
				System.out.println("Error: Invalid markup was entered.");
			}
		} else {
			System.out.println("Error: Could not open the url for xml parsing.");
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
		// TODO: Stub for parsing RSS xml documents
		System.out.println("About to parse RSS XML!");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseNNXML(InputStream xml) {
		// TODO: Stub for parsing NN xml documents
		System.out.println("About to parse NN XML!");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseMARFCATINXML(InputStream xml) {
		// TODO: Stub for parsing MARFCATIN xml documents
		System.out.println("About to parse MARFCATIN XML!");
			
		// use the MARFCATIN parser to parse the XML and output results
		ParseMARFCATINXML parser = new ParseMARFCATINXML();
		String result = parser.parse(xml);
		
		if (result != null) {
			System.out.println(result);
		} else {
			System.out.println("Error: could not parse MARFCATIN xml,");
		}
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseMARFCATOUTXML(InputStream xml) {
		// TODO: Stub for parsing MARFCATOUT xml documents
		System.out.println("About to parse MARFCATOUT XML!");
	}

	/**
	 * Parses XML input stream using appropriate parsing techniques and prints
	 * out elements and attributes in 'name:value' pairs
	 * 
	 * @param xml
	 */
	public void parseWSDLXML(InputStream xml) {
		// TODO: Stub for parsing WSDL xml documents
		System.out.println("About to parse WSDL XML!");
	}

}
