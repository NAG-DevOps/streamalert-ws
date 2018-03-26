package xmlService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="payload")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlServicePayload {
	
	@XmlElement(name="type")
	public String type;
	
	@XmlElement(name="uri")
	public String uri;
}
