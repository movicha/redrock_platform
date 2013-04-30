package resources;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementImpl implements Element {
    private String xmlId;
    private List<Extension> extensions = new ArrayList<Extension>();
  
	public String getXmlId() {
		return xmlId;
	}

	public void setXmlId(String xmlId) {
		this.xmlId = xmlId;
	}
	
    public List<Extension> getExtensions() {
        return extensions;
    }
  
    public boolean hasExtensions() {
        return extensions.size() > 0;
    }
}
