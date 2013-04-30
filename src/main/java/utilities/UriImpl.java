package utilities;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import resources.Extension;

public class UriImpl implements Uri {	
	private URI value;

	public URI getValue() {
		return value;
	}

	public void setValue(URI value) {
		this.value = value;
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