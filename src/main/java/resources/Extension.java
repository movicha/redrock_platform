package resources;

import java.util.List;

import utilities.Uri;

public interface Extension extends Element {
	public Uri getUrl();
	public void setUrl(Uri value);
	public Uri getUrlSimple();
	public void setUrlSimple(Uri value);
	public Boolean getMustUnderstand();
	public void setMustUnderstand(Boolean value);
	public boolean getMustUnderstandSimple();
	public void setMustUnderstandSimple(boolean value);
	public Type getValue();
	public void setValue(Type value);
	public List<Extension> getExtension();
}
