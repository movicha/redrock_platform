package resources;

import java.util.List;

public interface Element extends Iterable {
	public String getXmlId();
	public void setXmlId(String xmlId);
    public List<Extension> getExtensions();
    public boolean hasExtensions();
}