package marfcat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="grade")
@XmlAccessorType(XmlAccessType.FIELD)
public class Grade {
	
	@XmlAttribute(name="severity")
	public String severity;
	
	@XmlAttribute(name="probability")
	public String probability;
	
	@XmlAttribute(name="tool_specific_rank")
	public String toolSpecificRank;
}