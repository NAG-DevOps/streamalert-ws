/**
 * 
 */
package pm1.abstractions;

import java.io.InputStream;
import java.util.logging.Logger;

/**
 * @author AlexGenio
 *
 */
public abstract class Parser {

	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	private String searchTerm;
	
	public Parser() {
		this.searchTerm = "";
	}
	
	public Parser(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	/**
	 * Method to be overridden by subclass parsers.
	 * 
	 * @return the parsed output
	 */
	public abstract String parse(InputStream xml);

	/**
	 * @return the searchString
	 */
	public String getSearchTerm() {
		return searchTerm;
	}

	/**
	 * @param searchTerm the search term to set
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * @param logger the logger to set
	 */
	public static void setLogger(Logger logger) {
		LOGGER = logger;
	}
	
}
