
package neural_network;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
@XmlRootElement(name = "input")
public class Input {

	@XmlValue
	protected String value;
	@XmlAttribute(name = "ref")
	protected BigInteger ref;
	@XmlAttribute(name = "weight")
	protected Float weight;

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the value of the ref property.
	 * 
	 * @return possible object is {@link BigInteger }
	 * 
	 */
	public BigInteger getRef() {
		return ref;
	}

	/**
	 * Sets the value of the ref property.
	 * 
	 * @param value
	 *            allowed object is {@link BigInteger }
	 * 
	 */
	public void setRef(BigInteger value) {
		this.ref = value;
	}

	/**
	 * Gets the value of the weight property.
	 * 
	 * @return possible object is {@link Float }
	 * 
	 */
	public Float getWeight() {
		return weight;
	}

	/**
	 * Sets the value of the weight property.
	 * 
	 * @param value
	 *            allowed object is {@link Float }
	 * 
	 */
	public void setWeight(Float value) {
		this.weight = value;
	}

}
