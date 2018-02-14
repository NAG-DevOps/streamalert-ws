package marfcat;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="report")
@XmlAccessorType(XmlAccessType.FIELD)
public class Report {
	
	@XmlAttribute(name="tool_name")
	public String toolName;
	
	@XmlAttribute(name="tool_version")
	public String toolVersion;
	
	@XmlElement(name="weakness")
	public List<Weakness> weaknesses = new ArrayList<Weakness>();
}