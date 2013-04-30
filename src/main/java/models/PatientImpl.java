package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resources.Code;
import resources.CodeableConcept;
import resources.Extension;
import resources.Identifier;
import resources.ResourceReference;
import resources.ResourceType;

public class PatientImpl implements Patient {
	private Integer getResourceType; //override method. Returns integer of specified type, in this case Patient
	private ArrayList<ResourceReference> link; //THIS ARRAY IS FILLED WITH "ResourceReference" OBJECTS ONLY. A linked patient record is a record that concerns the same patient. Records are linked after it is realized that at least one was created in error.
	private boolean active; //Whether the patient record is in use, or has been removed from active use
	private ArrayList<Identifier> identifier; //THIS ARRAY IS FILLED WITH "HumanId" OBJECTS ONLY.. An identifier that applies to this person as a patient
	private Demographics details; //Patient Demographic details
	private ResourceReference provider; //The provider for whom this is a patient record
	private CodeableConcept diet; //Dietary restrictions for the patient
	private CodeableConcept confidentiality; //Confidentiality of the patient records
	private CodeableConcept recordLocation; //The location of the paper record for the patient, if there is one

	public Integer getGetResourceType() {
		return getResourceType;
	}
	
	public void setGetResourceType(Integer getResourceType) {
		this.getResourceType = getResourceType;
	}
	
	public ArrayList<ResourceReference> getLink() {
		return link;
	}
	
	public void setLink(ArrayList<ResourceReference> link) {
		this.link = link;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public ArrayList<Identifier> getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(ArrayList<Identifier> identifier) {
		this.identifier = identifier;
	}
	
	public Demographics getDetails() {
		return details;
	}
	
	public void setDetails(Demographics details) {
		this.details = details;
	}
	
	public ResourceReference getProvider() {
		return provider;
	}
	
	public void setProvider(ResourceReference provider) {
		this.provider = provider;
	}
	
	public CodeableConcept getDiet() {
		return diet;
	}
	
	public void setDiet(CodeableConcept diet) {
		this.diet = diet;
	}
	
	public CodeableConcept getConfidentiality() {
		return confidentiality;
	}
	
	public void setConfidentiality(CodeableConcept confidentiality) {
		this.confidentiality = confidentiality;
	}
	
	public CodeableConcept getRecordLocation() {
		return recordLocation;
	}
	
	public void setRecordLocation(CodeableConcept recordLocation) {
		this.recordLocation = recordLocation;
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
	public ResourceType getResourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
