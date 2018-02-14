package marfcat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="weakness")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weakness {
	
	@XmlAttribute(name="id")
	public String id;
	
	@XmlAttribute(name="tool_specific_id")
	public String toolSpecificId;
	
	@XmlElement(name="name")
	public Name name;
	
	@XmlElement(name="location")
	public LocationOutput location;
	
	@XmlElement(name="grade")
	public Grade grade;
	
	@XmlElement(name="output")
	public Output output;
}