package models;

import java.util.ArrayList;

import resources.*;

// Uses MySQL and Cassandra as backing data store
// Needs two implementations - one to play.db.jpa.Model, one to play.db.nosql.Model
public interface Patient extends Resource {
	public Integer getGetResourceType();
	public void setGetResourceType(Integer getResourceType);
	public ArrayList<ResourceReference> getLink();
	public void setLink(ArrayList<ResourceReference> link);
	public boolean isActive();
	public void setActive(boolean active);
	public ArrayList<Identifier> getIdentifier();
	public void setIdentifier(ArrayList<Identifier> identifier);
	public Demographics getDetails();
	public void setDetails(Demographics details);
	public ResourceReference getProvider();
	public void setProvider(ResourceReference provider);
	public CodeableConcept getDiet();
	public void setDiet(CodeableConcept diet);
	public CodeableConcept getConfidentiality();
	public void setConfidentiality(CodeableConcept confidentiality);
	public CodeableConcept getRecordLocation();
	public void setRecordLocation(CodeableConcept recordLocation);
}
