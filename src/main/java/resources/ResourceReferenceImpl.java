package resources;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import utilities.Uri;
import utilities.UriImpl;

public class ResourceReferenceImpl implements ResourceReference {

	/**
	 * The name of one of the resource types defined in this specification to
	 * identify the type of the resource being referenced
	 */
	private Code type;

	/**
	 * A literal URL that resolves to the location of the resource. The URL may
	 * be relative or absolute. Relative Ids contain the logical id of the
	 * resource. The reference may be version specific or not. If the reference
	 * is not to a FHIR RESTful server, then it should be assumed to be version
	 * specific
	 */
	private Uri url;

	/**
	 * Plain text narrative that identifies the resource in addition to the
	 * resource reference
	 */
	private String display;

	public Code getType() {
		return this.type;
	}

	public void setType(Code value) {
		this.type = value;
	}

	public String getTypeSimple() {
		return this.type == null ? null : this.type.getCodeText();
	}

	public void setTypeSimple(String value) {
		if (value == null)
			this.type = null;
		else {
			if (this.type == null)
				this.type = new CodeImpl();
			this.type.setCodeText(value);
		}
	}

	public Uri getUrl() {
		return this.url;
	}

	public void setUrl(Uri value) {
		this.url = value;
	}

	public URI getUrlSimple() {
		return this.url == null ? null : this.url.getValue();
	}

	public void setUrlSimple(URI value) {
		if (value == null)
			this.url = null;
		else {
			if (this.url == null)
				this.url = new UriImpl();
			this.url.setValue(value);
		}
	}

	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String value) {
		this.display = value;
	}

	public String getDisplaySimple() {
		return this.display == null ? null : this.display;
	}

	public void setDisplaySimple(String value) {
		if (value == null)
			this.display = null;
		else {
			if (this.display == null)
				this.display = new String();
			this.display = value;
		}
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