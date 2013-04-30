package resources;

import utilities.Base64Binary;

public interface Image extends Element {
	public Code getMimeType();
	public void setMimeType(Code mimeType);
	public Base64Binary getContent();
	public void setContent(Base64Binary content);
}
