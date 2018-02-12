import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="meta")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meta {
	
	@XmlElement(name="type")
	public String type;
	
	@XmlElement(name="length")
	public Length length;
}
