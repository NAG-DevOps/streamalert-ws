package pm1.enums;

import java.io.Serializable;

public enum Markup implements Serializable {
	RSS(1), NN(2), MARFCATIN(3), MARFCATOUT(4), WSDL(5);

	
	private static final long serialVersionUID = 42L;
	private int value;
	
	Markup(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
