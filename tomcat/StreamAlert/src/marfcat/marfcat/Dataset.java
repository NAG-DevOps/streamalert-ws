package marfcat;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dataset")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dataset {
	
	@XmlAttribute(name="generated-by")
	public String generatedBy;
	
	@XmlAttribute(name="generated-on")
	public String generatedOn;
	
	@XmlElement(name="description")
	public Description description;
	
	@XmlElement(name="file")
	public List<File> files = new ArrayList<File>();
}
