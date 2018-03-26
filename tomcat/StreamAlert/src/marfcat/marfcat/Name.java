package marfcat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="name")
@XmlAccessorType(XmlAccessType.FIELD)
public class Name {
	
	@XmlAttribute(name="cweid")
	public String cweid;
	
	@XmlValue()
	public String name;
}