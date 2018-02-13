
package neural_network;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}input" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}output" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="index" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="thresh" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "input", "output" })
@XmlRootElement(name = "neuron")
public class Neuron {

	protected List<Input> input;
	protected List<Output> output;
	@XmlAttribute(name = "index")
	protected BigInteger index;
	@XmlAttribute(name = "thresh")
	protected Float thresh;

	/**
	 * Gets the value of the input property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the input property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getInput().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Input }
	 * 
	 * 
	 */
	public List<Input> getInput() {
		if (input == null) {
			input = new ArrayList<Input>();
		}
		return this.input;
	}

	/**
	 * Gets the value of the output property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the output property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getOutput().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Output }
	 * 
	 * 
	 */
	public List<Output> getOutput() {
		if (output == null) {
			output = new ArrayList<Output>();
		}
		return this.output;
	}

	/**
	 * Gets the value of the index property.
	 * 
	 * @return possible object is {@link BigInteger }
	 * 
	 */
	public BigInteger getIndex() {
		return index;
	}

	/**
	 * Sets the value of the index property.
	 * 
	 * @param value
	 *            allowed object is {@link BigInteger }
	 * 
	 */
	public void setIndex(BigInteger value) {
		this.index = value;
	}

	/**
	 * Gets the value of the thresh property.
	 * 
	 * @return possible object is {@link Float }
	 * 
	 */
	public Float getThresh() {
		return thresh;
	}

	/**
	 * Sets the value of the thresh property.
	 * 
	 * @param value
	 *            allowed object is {@link Float }
	 * 
	 */
	public void setThresh(Float value) {
		this.thresh = value;
	}

}
