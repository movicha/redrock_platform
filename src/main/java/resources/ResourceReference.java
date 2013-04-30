package resources;

import java.net.URI;

import utilities.Uri;

//A reference from one resource to another
public interface ResourceReference extends Type {
	public Code getType();
	public void setType(Code value);
	public String getTypeSimple();
	public void setTypeSimple(String value);
	public Uri getUrl();
	public void setUrl(Uri value);	
	public URI getUrlSimple();	
	public void setUrlSimple(URI value);
	public String getDisplay();
	public void setDisplay(String value);
	public String getDisplaySimple();
	public void setDisplaySimple(String value);
}