package models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A <code>Patient</code> can have zero to n identifying PatientIdentifier(s). PatientIdentifiers
 * are anything from medical record numbers, to social security numbers, to driver's licenses. The
 * type of identifier is defined by the PatientIdentifierType. A PatientIdentifier also contains a
 * Location.
 */
public class PatientIdentifier extends BasePhmData implements java.io.Serializable, Comparable<PatientIdentifier> {
	
	public static final long serialVersionUID = 1123121L;
	
	private static final Log log = LogFactory.getLog(PatientIdentifier.class);
	
	// Fields
	
	private Integer patientIdentifierId;
	
	private Patient patient;
	
	private String identifier;
	
	private PatientIdentifierType identifierType;
	
	private Location location;
	
	private Boolean preferred = false;
	
	/** default constructor */
	public PatientIdentifier() {
	}
	
	/**
	 * Convenience constructor for creating a basic identifier
	 * 
	 * @param identifier String identifier
	 * @param type PatientIdentifierType
	 * @param location Location of the identifier
	 */
	public PatientIdentifier(String identifier, PatientIdentifierType type, Location location) {
		this.identifier = identifier;
		this.identifierType = type;
		this.location = location;
	}
	
	/**
	 * Compares this PatientIdentifier object to the given otherIdentifier. This method differs from
	 * {@link #equals(Object)} in that this method compares the inner fields of each identifier for
	 * equality. Note: Null/empty fields on <code>otherIdentifier</code> /will not/ cause a false
	 * value to be returned
	 * 
	 * @param otherIdentifier PatientiIdentifier with which to compare
	 * @return boolean true/false whether or not they are the same names
	 */
	public boolean equalsContent(PatientIdentifier otherIdentifier) {
		boolean returnValue = true;
		
		// these are the methods to compare.
		String[] methods = { "getIdentifier", "getIdentifierType", "getLocation" };
		
		Class<? extends PatientIdentifier> identifierClass = this.getClass();
		
		// loop over all of the selected methods and compare this and other
		for (String methodName : methods) {
			try {
				Method method = identifierClass.getMethod(methodName, new Class[] {});
				
				Object thisValue = method.invoke(this);
				Object otherValue = method.invoke(otherIdentifier);
				
				if (otherValue != null)
					returnValue &= otherValue.equals(thisValue);
				
			}
			catch (NoSuchMethodException e) {
				log.warn("No such method for comparison " + methodName, e);
			}
			catch (IllegalAccessException e) {
				log.error("Error while comparing identifiers", e);
			}
			catch (InvocationTargetException e) {
				log.error("Error while comparing identifiers", e);
			}
			
		}
		
		return returnValue;
	}
	
	//property accessors
	
	/**
	 * @return Returns the identifier.
	 */
	public String getIdentifier() {
		return identifier;
	}
	
	/**
	 * @param identifier The identifier to set.
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * @return Returns the identifierType.
	 */
	public PatientIdentifierType getIdentifierType() {
		return identifierType;
	}
	
	/**
	 * @param identifierType The identifierType to set.
	 */
	public void setIdentifierType(PatientIdentifierType identifierType) {
		this.identifierType = identifierType;
	}
	
	/**
	 * @return Returns the location.
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * @param location The location to set.
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * @return Returns the patient.
	 */
	public Patient getPatient() {
		return patient;
	}
	
	/**
	 * @param patient The patient to set.
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@Override
	public String toString() {
		return this.identifier;
	}
	
	/**
	 * @return Returns the preferred.
	 */
	public Boolean getPreferred() {
		return isPreferred();
	}
	
	/**
	 * @param preferred The preferred to set.
	 */
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
	
	/**
	 * @return the preferred status
	 */
	public Boolean isPreferred() {
		return preferred;
	}
	
	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(PatientIdentifier other) {
		int retValue = 0;
		if (other != null) {
			retValue = isVoided().compareTo(other.isVoided());
			if (retValue == 0)
				retValue = other.isPreferred().compareTo(isPreferred());
			if (retValue == 0)
				retValue = OpenmrsUtil.compareWithNullAsLatest(getDateCreated(), other.getDateCreated());
			if (retValue == 0)
				retValue = OpenmrsUtil.compareWithNullAsGreatest(getIdentifierType().getPatientIdentifierTypeId(), other
				        .getIdentifierType().getPatientIdentifierTypeId());
			if (retValue == 0)
				retValue = OpenmrsUtil.compareWithNullAsGreatest(getIdentifier(), other.getIdentifier());
			
			// if we've gotten this far, just check all identifier values.  If they are
			// equal, leave the objects at 0.  If not, arbitrarily pick retValue=1
			// and return that (they are not equal).
			if (retValue == 0 && !equalsContent(other))
				retValue = 1;
		}
		
		return retValue;
	}
	
	public Integer getId() {
		return getPatientIdentifierId();
	}
	
	public void setId(Integer id) {
		setPatientIdentifierId(id);
	}
	
	public Integer getPatientIdentifierId() {
		return patientIdentifierId;
	}
	
	public void setPatientIdentifierId(Integer patientIdentifierId) {
		this.patientIdentifierId = patientIdentifierId;
	}
}
