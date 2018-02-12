import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="file")
@XmlAccessorType(XmlAccessType.FIELD)
public class File {
	
	@XmlAttribute(name="id")
	public String id;
	
	@XmlAttribute(name="path")
	public String path;
	
	@XmlElement(name="meta")
	public Meta meta;
	
	@XmlElement(name="location")
	public Location location;
}
