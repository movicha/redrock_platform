package models;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * Defines a Patient in the system. A patient is simply an extension of a person and all that that
 * implies.
 */
public class Patient extends Person implements java.io.Serializable {
	
	public static final long serialVersionUID = 93123L;
	
	// Fields
	
	// private Person person;
	
	private Integer patientId;
	
	private Set<PatientIdentifier> identifiers;
	
	// Constructors
	/** default constructor */
	public Patient() {
	}
	
	/**
	 * This constructor creates a new Patient object from the given {@link Person} object. All
	 * attributes are copied over to the new object. NOTE! All child collection objects are copied
	 * as pointers, each individual element is not copied. <br/>
	 * <br/>
	 * TODO Should the patient specific attributes be copied? (like identifiers)
	 * 
	 * @param person the person object to copy onto a new Patient
	 * @see Person#Person(Person)
	 */
	public Patient(Person person) {
		super(person);
		if (person != null)
			this.patientId = person.getPersonId();
		if (person.getUuid() != null)
			this.setUuid(person.getUuid());
	}
	
	/**
	 * Constructor with default patient id
	 * 
	 * @param patientId
	 */
	public Patient(Integer patientId) {
		super(patientId);
		this.patientId = patientId;
	}
	
	// Property accessors
	
	/**
	 * @return internal identifier for patient
	 */
	public Integer getPatientId() {
		return this.patientId;
	}
	
	/**
	 * Sets the internal identifier for a patient. <b>This should never be called directly</b>. It
	 * exists only for the use of the supporting infrastructure.
	 * 
	 * @param patientId
	 */
	public void setPatientId(Integer patientId) {
		super.setPersonId(patientId);
		this.patientId = patientId;
	}
	
	/**
	 * Overrides the parent setPersonId(Integer) so that we can be sure patient id is also set
	 * correctly.
	 */
	@Override
	public void setPersonId(Integer personId) {
		super.setPersonId(personId);
		this.patientId = personId;
	}
		
	/**
	 * Get all of this patients identifiers -- both voided and non-voided ones. If you want only
	 * non-voided identifiers, use {@link #getActiveIdentifiers()}
	 * 
	 * @return Set of all known identifiers for this patient
	 * @see #getActiveIdentifiers()
	 * @should not return null
	 */
	public Set<PatientIdentifier> getIdentifiers() {
		if (identifiers == null)
			identifiers = new LinkedHashSet<PatientIdentifier>();
		return this.identifiers;
	}
	
	/**
	 * Update all identifiers for patient
	 * 
	 * @param identifiers Set<PatientIdentifier> to set as update all known identifiers for patient
	 * @see org.openmrs.PatientIdentifier
	 */
	public void setIdentifiers(Set<PatientIdentifier> identifiers) {
		this.identifiers = identifiers;
	}
	
	/**
	 * Adds this PatientIdentifier if the patient doesn't contain it already
	 * 
	 * @param patientIdentifier
	 */
	/**
	 * Will only add PatientIdentifiers in this list that this patient does not have already
	 * 
	 * @param patientIdentifiers
	 */
	public void addIdentifiers(Collection<PatientIdentifier> patientIdentifiers) {
		for (PatientIdentifier identifier : patientIdentifiers)
			addIdentifier(identifier);
	}
	
	/**
	 * Will add this PatientIdentifier if the patient doesn't contain it already
	 * 
	 * @param patientIdentifier
	 * @should not fail with null identifiers list
	 * @should add identifier to current list
	 * @should not add identifier that is in list already
	 */
	public void addIdentifier(PatientIdentifier patientIdentifier) {
		patientIdentifier.setPatient(this);
		if (getIdentifiers() == null)
			identifiers = new LinkedHashSet<PatientIdentifier>();
		if (patientIdentifier != null) {
			// make sure the set doesn't already contain an identifier with the same
			// identifier, identifierType
			for (PatientIdentifier currentId : getActiveIdentifiers()) {
				if (currentId.equalsContent(patientIdentifier)) {
					return; // fail silently if someone tries to add a duplicate
				}
			}
		}
		identifiers.add(patientIdentifier);
	}
	
	/**
	 * Convenience method to remove the given identifier from this patient's list of identifiers. If
	 * <code>patientIdentifier</code> is null, nothing is done.
	 * 
	 * @param patientIdentifier the identifier to remove
	 * @should remove identifier if exists
	 */
	public void removeIdentifier(PatientIdentifier patientIdentifier) {
		if (getIdentifiers() != null && patientIdentifier != null)
			identifiers.remove(patientIdentifier);
	}
	
	/**
	 * Convenience method to get the first "preferred" identifier for a patient. Otherwise, returns
	 * the first non-voided identifier Otherwise, null
	 * 
	 * @return Returns the "preferred" patient identifier.
	 */
	public PatientIdentifier getPatientIdentifier() {
		// normally the DAO layer returns these in the correct order, i.e. preferred and non-voided first, but it's possible that someone
		// has fetched a Patient, changed their identifiers around, and then calls this method, so we have to be careful.
		if (getIdentifiers() != null && getIdentifiers().size() > 0) {
			for (PatientIdentifier id : getIdentifiers()) {
				if (id.isPreferred() && !id.isVoided())
					return id;
			}
			for (PatientIdentifier id : getIdentifiers()) {
				if (!id.isVoided())
					return id;
			}
			return null;
		}
		return null;
	}
	
	/**
	 * Returns the first (preferred) patient identifier matching a
	 * <code>PatientIdentifierType</code> Otherwise, returns the first non-voided identifier
	 * Otherwise, null
	 * 
	 * @param pit The PatientIdentifierType of which to return the PatientIdentifier
	 * @return Returns a PatientIdentifier of the specified type.
	 */
	public PatientIdentifier getPatientIdentifier(PatientIdentifierType pit) {
		if (getIdentifiers() != null && getIdentifiers().size() > 0) {
			for (PatientIdentifier id : getIdentifiers()) {
				if (id.isPreferred() && !id.isVoided() && pit.equals(id.getIdentifierType()))
					return id;
			}
			for (PatientIdentifier id : getIdentifiers()) {
				if (!id.isVoided() && pit.equals(id.getIdentifierType()))
					return id;
			}
			return null;
		}
		return null;
	}
	
	/**
	 * Returns the first (preferred) patient identifier matching <code>identifierTypeId</code>
	 * 
	 * @param identifierTypeId
	 * @return preferred patient identifier
	 */
	public PatientIdentifier getPatientIdentifier(Integer identifierTypeId) {
		if (getIdentifiers() != null && getIdentifiers().size() > 0) {
			for (PatientIdentifier id : getIdentifiers()) {
				if (id.isPreferred() && !id.isVoided()
				        && identifierTypeId.equals(id.getIdentifierType().getPatientIdentifierTypeId()))
					return id;
			}
			for (PatientIdentifier id : getIdentifiers()) {
				if (!id.isVoided() && identifierTypeId.equals(id.getIdentifierType().getPatientIdentifierTypeId()))
					return id;
			}
			return null;
		}
		return null;
	}
	
	/**
	 * Returns the (preferred) patient identifier matching <code>identifierTypeName</code>
	 * Otherwise returns that last <code>PatientIdenitifer</code>
	 * 
	 * @param identifierTypeName
	 * @return preferred patient identifier
	 */
	public PatientIdentifier getPatientIdentifier(String identifierTypeName) {
		if (getIdentifiers() != null && getIdentifiers().size() > 0) {
			for (PatientIdentifier id : getIdentifiers()) {
				if (id.isPreferred() && !id.isVoided() && identifierTypeName.equals(id.getIdentifierType().getName()))
					return id;
			}
			for (PatientIdentifier id : getIdentifiers()) {
				if (!id.isVoided() && identifierTypeName.equals(id.getIdentifierType().getName()))
					return id;
			}
			return null;
		}
		return null;
	}
	
	/**
	 * Returns only the non-voided identifiers for this patient. If you want <u>all</u> identifiers,
	 * use {@link #getIdentifiers()}
	 * 
	 * @return list of non-voided identifiers for this patient
	 * @see #getIdentifiers()
	 * @should return preferred identifiers first in the list
	 */
	public List<PatientIdentifier> getActiveIdentifiers() {
		List<PatientIdentifier> ids = new Vector<PatientIdentifier>();
		if (getIdentifiers() != null) {
			List<PatientIdentifier> nonPreferred = new LinkedList<PatientIdentifier>();
			for (PatientIdentifier pi : getIdentifiers()) {
				if (pi.isVoided() == false) {
					if (pi.isPreferred())
						ids.add(pi);
					else
						nonPreferred.add(pi);
				}
			}
			for (PatientIdentifier pi : nonPreferred)
				ids.add(pi);
		}
		return ids;
	}
	
	/**
	 * Returns only the non-voided identifiers for this patient. If you want <u>all</u> identifiers,
	 * use {@link #getIdentifiers()}
	 * 
	 * @return list of non-voided identifiers for this patient
	 * @param pit PatientIdentifierType
	 * @see #getIdentifiers()
	 */
	public List<PatientIdentifier> getPatientIdentifiers(PatientIdentifierType pit) {
		List<PatientIdentifier> ids = new Vector<PatientIdentifier>();
		if (getIdentifiers() != null) {
			for (PatientIdentifier pi : getIdentifiers()) {
				if (pi.isVoided() == false && pit.equals(pi.getIdentifierType()))
					ids.add(pi);
			}
		}
		return ids;
	}
	
	@Override
	public String toString() {
		return "Patient#" + patientId;
	}
	
	@Override
	public Integer getId() {
		return getPatientId();
	}
	
	@Override
	public void setId(Integer id) {
		setPatientId(id);
		
	}
	
	/**
	 * Returns the person represented
	 * @return the person represented by this object
	 */
	public Person getPerson() {
		return this;
	}
}
