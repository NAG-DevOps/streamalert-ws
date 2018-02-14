package marfcat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="output")
@XmlAccessorType(XmlAccessType.FIELD)
public class Output {
	
	@XmlElement(name="textoutput")
	public String textoutput;
}