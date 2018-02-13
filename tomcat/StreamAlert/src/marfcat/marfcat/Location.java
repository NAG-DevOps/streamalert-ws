package marfcat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="location")
@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
	
	@XmlAttribute(name="line")
	public String line;
	
	@XmlAttribute(name="fraglines")
	public String fraglines;
	
	@XmlElement(name="fragment")
	public String fragment;
	
	@XmlElement(name="explanation")
	public String explanation;
}
