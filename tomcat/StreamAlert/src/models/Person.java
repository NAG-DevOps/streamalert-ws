
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
	
	@XmlElement
	private int height;
	
	@XmlElement
	private int weight;
	
	@XmlElement
	private String gender;
	
	@XmlAttribute
	private String name;
	
	// The no-arg default constructor is required for jaxb
	public Person() {}
	
	public Person(int height, int weight, String gender, String name) {
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.name = name;
	}
}
