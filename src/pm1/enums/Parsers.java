package pm1.enums;

import java.io.Serializable;

/**
 * @author miqdadamirali
 *
 */
public enum Parsers implements Serializable {
	DOM(1), SAX(2);
	
	private static final long serialVersionUID = 42L;
	private int value;
	
	Parsers(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
