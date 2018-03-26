import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="alert")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlertModel {
	
	@XmlElement(name="title")
	public String title;
	
	@XmlElement(name="message")
	public String message;
}
