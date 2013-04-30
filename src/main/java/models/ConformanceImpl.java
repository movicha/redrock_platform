package models;

import java.util.*;

import java.net.*;

import resources.Code;
import resources.DateTime;
import resources.Extension;
import resources.ResourceType;

import utilities.Uri;
import utilities.UriImpl;
/**
 * A conformance statement about how an application or implementation supports FHIR or the set of requirements for a desired implementation
 */
public class ConformanceImpl implements Conformance {
	/**
	 * The mode of this event declaration - whether application is sender or
	 * receiver
	 */
	private DocumentMode mode;

	/**
	 * Describes how the application supports or uses the specified document
	 * profile. For example, when are documents created, what action is taken
	 * with consumed documents, etc.
	 */
	private String documentation;

	/**
	 * Constraint on a resource used in the document
	 */
	private Uri profile;

	public DocumentMode getMode() {
		return this.mode;
	}

	public void setMode(DocumentMode value) {
		this.mode = value;
	}

	public DocumentMode getModeSimple() {
		return this.mode == null ? null : this.mode;
	}

	public void setModeSimple(DocumentMode value) {
		if (value == null)
			this.mode = null;
		else {
			this.mode = value;
		}
	}

	public String getDocumentation() {
		return this.documentation;
	}

	public void setDocumentation(String value) {
		this.documentation = value;
	}

	public String getDocumentationSimple() {
		return this.documentation == null ? null : this.documentation;
	}

	public void setDocumentationSimple(String value) {
		if (value == null)
			this.documentation = null;
		else {
			if (this.documentation == null)
				this.documentation = new String();
			this.documentation = value;
		}
	}

	public Uri getProfile() {
		return this.profile;
	}

	public void setProfile(Uri value) {
		this.profile = value;
	}

	public Uri getProfileSimple() {
		return this.profile == null ? null : this.profile;
	}

	public void setProfileSimple(Uri value) {
		if (value == null)
			this.profile = null;
		else {
			if (this.profile == null)
				this.profile = new UriImpl();
			this.profile = value;
		}
	}
  
    /**
     * Date that the conformance statement is published
     */
    private DateTime date;

    /**
     * The organization that publishes this conformance statement
     */
    private Publisher publisher;

    /**
     * Describes the implementation that is covered by this conformance statement.  Used when the profile describes the capabilities of a specific implementation instance.
     */
    private Implementation implementation;

    /**
     * Describes the software that is covered by this conformance statement.  Used when the profile describes the capabilities of a particular software version, independent of an installation.
     */
    private Software software;

    /**
     * Describes the proposed solution described by this conformance statement.  Used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.
     */
    private Proposal proposal;

    /**
     * The version of the FHIR specification on which this conformance profile is based
     */
    private Id version;

    /**
     * Whether the application accepts unknown non-"must understand" elements as part of a resource. This does not include extensions, but genuine new additions to a resource
     */
    private Boolean acceptUnknown;

    /**
     * Defines the restful capabilities of the solution, if any
     */
    private List<Rest> rest = new ArrayList<Rest>();

    /**
     * Describes the messaging capabilities of the solution
     */
    private List<Messaging> messaging = new ArrayList<Messaging>();

    /**
     * A document definition
     */
    private List<Document> document = new ArrayList<Document>();

    public DateTime getDate() { 
      return this.date;
    }

    public void setDate(DateTime value) { 
      this.date = value;
    }

    public String getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    public void setDateSimple(String value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      }
    }

    public Publisher getPublisher() { 
      return this.publisher;
    }

    public void setPublisher(Publisher value) { 
      this.publisher = value;
    }

    public Implementation getImplementation() { 
      return this.implementation;
    }

    public void setImplementation(Implementation value) { 
      this.implementation = value;
    }

    public Software getSoftware() { 
      return this.software;
    }

    public void setSoftware(Software value) { 
      this.software = value;
    }

    public Proposal getProposal() { 
      return this.proposal;
    }

    public void setProposal(Proposal value) { 
      this.proposal = value;
    }

    public Id getVersion() { 
      return this.version;
    }

    public void setVersion(Id value) { 
      this.version = value;
    }

    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    public void setVersionSimple(String value) { 
      if (value == null)
        this.version = null;
      else {
        if (this.version == null)
          this.version = new IdImpl();
        this.version.setValue(value);
      }
    }

    public Boolean getAcceptUnknown() { 
      return this.acceptUnknown;
    }

    public void setAcceptUnknown(Boolean value) { 
      this.acceptUnknown = value;
    }

    public boolean getAcceptUnknownSimple() { 
      return this.acceptUnknown == null ? null : this.acceptUnknown;
    }

    public void setAcceptUnknownSimple(boolean value) { 
      if (value == false)
        this.acceptUnknown = null;
      else {
        if (this.acceptUnknown == null)
          this.acceptUnknown = new Boolean(value);
        this.acceptUnknown= value;
      }
    }

    public List<Rest> getRest() { 
      return this.rest;
    }

    public List<Messaging> getMessaging() { 
      return this.messaging;
    }

    public List<Document> getDocument() { 
      return this.document;
    }

  // @Override
  public ResourceType getResourceType() {
    return ResourceType.Conformance;
   }

@Override
public Narrative getText() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setText(Narrative text) {
	// TODO Auto-generated method stub
	
}

@Override
public Code getLanguage() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setLanguage(Code value) {
	// TODO Auto-generated method stub
	
}

@Override
public String getLanguageSimple() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setLanguageSimple(String value) {
	// TODO Auto-generated method stub
	
}

@Override
public List<models.Resource> getContained() {
	// TODO Auto-generated method stub
	return null;
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
