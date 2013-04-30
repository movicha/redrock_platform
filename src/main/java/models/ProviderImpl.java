package models;

import resources.Code;
import resources.CodeableConcept;
import resources.Extension;
import resources.Identifier;
import resources.ResourceReference;
import resources.ResourceType;

import java.util.*;

/**
 * A person who represents an organization, and is authorized to perform actions
 * on its behalf
 */
public class ProviderImpl implements Provider {

	/**
	 * An identifier that applies to this person in this role
	 */
	private List<Identifier> identifier = new ArrayList<Identifier>();

	/**
	 * Provider Demographic details
	 */
	private Demographics details;

	/**
	 * The organisation that is being represented
	 */
	private ResourceReference organization;

	/**
	 * The way in which the person represents the organisation - what role do
	 * they have?
	 */
	private List<CodeableConcept> role = new ArrayList<CodeableConcept>();

	/**
	 * Represents a specific specialty within the healthcare facility under
	 * which the agent acts
	 */
	private List<CodeableConcept> specialty = new ArrayList<CodeableConcept>();

	/**
	 * The time period during which the agent was/is authorised to represent the
	 * organisation.
	 */
	private Period period;

	/**
	 * A postal address for this person playing this role
	 */
	private List<Address> address = new ArrayList<Address>();

	/**
	 * A contact detail address for this person playing this role
	 */
	private List<Contact> contact = new ArrayList<Contact>();

	public List<Identifier> getIdentifier() {
		return this.identifier;
	}

	public Demographics getDetails() {
		return this.details;
	}

	public void setDetails(Demographics value) {
		this.details = value;
	}

	public ResourceReference getOrganization() {
		return this.organization;
	}

	public void setOrganization(ResourceReference value) {
		this.organization = value;
	}

	public List<CodeableConcept> getRole() {
		return this.role;
	}

	public List<CodeableConcept> getSpecialty() {
		return this.specialty;
	}

	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period value) {
		this.period = value;
	}

	public List<Address> getAddress() {
		return this.address;
	}

	public List<Contact> getContact() {
		return this.contact;
	}

	// @Override
	public ResourceType getResourceType() {
		return ResourceType.Provider;
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
	public List<Resource> getContained() {
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