package marfcat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="description")
@XmlAccessorType(XmlAccessType.FIELD)
public class Description {
	
	@XmlElement(name="file-type-tool")
	public String fileTypeTool;
	
	@XmlElement(name="find-tool")
	public String findTool;
	
	@XmlElement(name="marf-tool")
	public String marfTool;
}
