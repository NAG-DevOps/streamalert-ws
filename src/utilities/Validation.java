/**
 * 
 */
package utilities;

/**
 * @author miqdadamirali
 *
 */
public class Validation {

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isValidURL(String url) {
		return false;
	}

	/**
	 * Validate if the String received is not null and not empty.
	 * 
	 * @param validate
	 * @return
	 */
	public static boolean isValidString(String validate) {
		return (validate != null && !validate.trim().isEmpty());
	}

	/**
	 * Validate if the String null or empty.
	 * 
	 * @param validate
	 * @return
	 */
	public static boolean isNotValidString(String validate) {
		return !isValidString(validate);
	}
}
