
package neural_network;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the neural_network package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema
	 * derived classes for package: neural_network
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link Output }
	 * 
	 */
	public Output createOutput() {
		return new Output();
	}

	/**
	 * Create an instance of {@link Input }
	 * 
	 */
	public Input createInput() {
		return new Input();
	}

	/**
	 * Create an instance of {@link Neuron }
	 * 
	 */
	public Neuron createNeuron() {
		return new Neuron();
	}

	/**
	 * Create an instance of {@link Net }
	 * 
	 */
	public Net createNet() {
		return new Net();
	}

	/**
	 * Create an instance of {@link Layer }
	 * 
	 */
	public Layer createLayer() {
		return new Layer();
	}

}
