package resources;

import java.util.Iterator;
import java.util.List;

import utilities.Base64Binary;

public class ImageImpl implements Image {
	private Code mimeType;
	private Base64Binary content;

	public Code getMimeType() {
		return mimeType;
	}
	
	public void setMimeType(Code mimeType) {
		this.mimeType = mimeType;
	}
	
	public Base64Binary getContent() {
		return content;
	}
	
	public void setContent(Base64Binary content) {
		this.content = content;
	}

	@Override
	public String getXmlId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setXmlId(String xmlId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Extension> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasExtensions() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
