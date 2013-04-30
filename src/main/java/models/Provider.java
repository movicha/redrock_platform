package models;

import java.util.List;

import resources.CodeableConcept;
import resources.Identifier;
import resources.ResourceReference;

// Uses MySQL as backing data store
// Needs play.db.jpa.Model implementation
public interface Provider extends Resource {
	public List<Identifier> getIdentifier();
	public Demographics getDetails();
	public void setDetails(Demographics value);
	public ResourceReference getOrganization();
	public void setOrganization(ResourceReference value);
	public List<CodeableConcept> getRole();
	public List<CodeableConcept> getSpecialty();
	public Period getPeriod();
	public void setPeriod(Period value);
	public List<Address> getAddress();
	public List<Contact> getContact();
}