package resources;

import java.util.*;

import utilities.Uri;
import utilities.UriImpl;
/**
 * Optional Extensions Element - found in all resources
 */
public class ExtensionImpl implements Extension {

    /**
     * Source of the definition for the extension code - a logical name or a URL
     */
    private Uri url;

    /**
     * If this element is set to true, then the containing resource element and its children are only safe to process if the reader understands this extension. 
     */
    private Boolean mustUnderstand;

    /**
     * Value of extension - may be a resource or one of a constrained set of the data types (see Extensibility in the spec for list)
     */
    private Type value;

    /**
     * Nested Complex extensions
     */
    private List<Extension> extension = new ArrayList<Extension>();

    public Uri getUrl() { 
      return this.url;
    }

    public void setUrl(Uri value) { 
      this.url = value;
    }

    public Uri getUrlSimple() { 
      return this.url == null ? null : this.url;
    }

    public void setUrlSimple(Uri value) { 
      if (value == null)
        this.url = null;
      else {
        if (this.url == null)
          this.url = new UriImpl();
        this.url= value;
      }
    }

    public Boolean getMustUnderstand() { 
      return this.mustUnderstand;
    }

    public void setMustUnderstand(Boolean value) { 
      this.mustUnderstand = value;
    }

    public boolean getMustUnderstandSimple() { 
      return this.mustUnderstand == null ? null : this.mustUnderstand;
    }

    public void setMustUnderstandSimple(boolean value) { 
      if (value == false)
        this.mustUnderstand = null;
      else {
        if (this.mustUnderstand == null)
          this.mustUnderstand = new Boolean(value);
        this.mustUnderstand= value;
      }
    }

    public Type getValue() { 
      return this.value;
    }

    public void setValue(Type value) { 
      this.value = value;
    }

    public List<Extension> getExtension() { 
      return this.extension;
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

