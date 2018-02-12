import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="length")
@XmlAccessorType(XmlAccessType.FIELD)
public class Length {
	
	@XmlAttribute(name="lines")
	public String lines;
	
	@XmlAttribute(name="words")
	public String words;
	
	@XmlAttribute(name="bytes")
	public String bytes;

}
