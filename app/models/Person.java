package models;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A Person in the system. This can be either a small person stub, or indicative of an actual
 * Patient in the system. This class holds the generic person things that both the stubs and
 * patients share. Things like birthdate, names, addresses, and attributes are all generified into
 * the person table (and hence this super class)
 */
@Root(strict = false)
public class Person extends BasePhmData implements java.io.Serializable {
	
	public static final long serialVersionUID = 2L;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	protected Integer personId;
	
	private Set<PersonAddress> addresses = null;
	
	private Set<PersonName> names = null;
	
	private Set<PersonAttribute> attributes = null;
	
	private String gender;
	
	private Date birthdate;
	
	private Date personDateCreated;
	
	private Boolean personVoided = false;
	
	private Date personDateVoided;
	
	private boolean isPatient;
	
	/**
	 * Convenience map from PersonAttributeType.name to PersonAttribute.<br/>
	 * <br/>
	 * This is "cached" for each user upon first load. When an attribute is changed, the cache is
	 * cleared and rebuilt on next access.
	 */
	Map<String, PersonAttribute> attributeMap = null;
	
	/**
	 * default empty constructor
	 */
	public Person() {
	}
	
	/**
	 * This constructor is used to build a new Person object copy from another person object
	 * (usually a patient or a user subobject). All attributes are copied over to the new object.
	 * NOTE! All child collection objects are copied as pointers, each individual element is not
	 * copied. <br/>
	 * 
	 * @param person Person to create this person object from
	 */
	public Person(Person person) {
		if (person == null)
			return;
		
		personId = person.getPersonId();
		setUuid(person.getUuid());
		addresses = person.getAddresses();
		names = person.getNames();
		attributes = person.getAttributes();
		
		gender = person.getGender();
		birthdate = person.getBirthdate();
		birthdateEstimated = person.getBirthdateEstimated();
		deathdateEstimated = person.getDeathdateEstimated();
		dead = person.isDead();
		deathDate = person.getDeathDate();
		causeOfDeath = person.getCauseOfDeath();
		
		// base creator/voidedBy/changedBy info is not copied here
		// because that is specific to and will be recreated
		// by the subobject upon save
		
		setPersonCreator(person.getPersonCreator());
		setPersonDateCreated(person.getPersonDateCreated());
		setPersonChangedBy(person.getPersonChangedBy());
		setPersonDateChanged(person.getPersonDateChanged());
		setPersonVoided(person.isPersonVoided());
		setPersonVoidedBy(person.getPersonVoidedBy());
		setPersonDateVoided(person.getPersonDateVoided());
		setPersonVoidReason(person.getPersonVoidReason());
	}
	
	/**
	 * Default constructor taking in the primary key personId value
	 * 
	 * @param personId Integer internal id for this person
	 * @should set person id
	 */
	public Person(Integer personId) {
		this.personId = personId;
	}
	
	// Property accessors
	
	/**
	 * @return Returns the personId.
	 */
	@Attribute(required = true)
	public Integer getPersonId() {
		return personId;
	}
	
	/**
	 * @param personId The personId to set.
	 */
	@Attribute(required = true)
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	/**
	 * @return person's gender
	 */
	@Attribute(required = false)
	public String getGender() {
		return this.gender;
	}
	
	/**
	 * @param gender person's gender
	 */
	@Attribute(required = false)
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return person's date of birth
	 */
	@Element(required = false)
	public Date getBirthdate() {
		return this.birthdate;
	}
	
	/**
	 * @param birthdate person's date of birth
	 */
	@Element(required = false)
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	/**
	 * @return list of known addresses for person
	 * @should not get voided addresses
	 * @should not fail with null addresses
	 */
	@ElementList(required = false)
	public Set<PersonAddress> getAddresses() {
		if (addresses == null)
			addresses = new TreeSet<PersonAddress>();
		return this.addresses;
	}
	
	/**
	 * @param addresses Set<PersonAddress> list of known addresses for person
	 */
	@ElementList(required = false)
	public void setAddresses(Set<PersonAddress> addresses) {
		this.addresses = addresses;
	}
	
	/**
	 * @return all known names for person
	 * @should not get voided names
	 * @should not fail with null names
	 */
	@ElementList
	public Set<PersonName> getNames() {
		if (names == null)
			names = new TreeSet<PersonName>();
		return this.names;
	}
	
	/**
	 * @param names update all known names for person
	 */
	@ElementList
	public void setNames(Set<PersonName> names) {
		this.names = names;
	}
	
	/**
	 * @return all known attributes for person
	 * @should not get voided attributes
	 * @should not fail with null attributes
	 */
	@ElementList
	public Set<PersonAttribute> getAttributes() {
		if (attributes == null)
			attributes = new TreeSet<PersonAttribute>();
		return this.attributes;
	}
	
	/**
	 * Returns only the non-voided attributes for this person
	 * 
	 * @return list attributes
	 * @should not get voided attributes
	 * @should not fail with null attributes
	 */
	public List<PersonAttribute> getActiveAttributes() {
		List<PersonAttribute> attrs = new Vector<PersonAttribute>();
		for (PersonAttribute attr : getAttributes()) {
			if (!attr.isVoided())
				attrs.add(attr);
		}
		return attrs;
	}
	
	/**
	 * @param attributes update all known attributes for person
	 */
	@ElementList
	public void setAttributes(Set<PersonAttribute> attributes) {
		this.attributes = attributes;
		attributeMap = null;
	}
					
	public Date getPersonDateCreated() {
		return personDateCreated;
	}
	
	public void setPersonDateCreated(Date dateCreated) {
		this.personDateCreated = dateCreated;
	}
	
	public Date getPersonDateVoided() {
		return personDateVoided;
	}
	
	public void setPersonDateVoided(Date dateVoided) {
		this.personDateVoided = dateVoided;
	}
	
	public void setPersonVoided(Boolean voided) {
		this.personVoided = voided;
	}
	
	public Boolean getPersonVoided() {
		return isPersonVoided();
	}
	
	public Boolean isPersonVoided() {
		return personVoided;
	}
	
	/**
	 * @return true/false whether this person is a patient or not
	 */
	public boolean isPatient() {
		return isPatient;
	}
	
	/**
	 * This should only be set by the database layer by looking at whether a row exists in the
	 * patient table
	 * 
	 * @param isPatient whether this person is a patient or not
	 */
	@SuppressWarnings("unused")
	private void setPatient(boolean isPatient) {
		this.isPatient = isPatient;
	}
	
	/**
	 * @return true/false whether this person is a user or not
	 * @deprecated use {@link UserService#getUsersByPerson(Person, boolean)}
	 */
	public boolean isUser() {
		return false;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Person(personId=" + personId + ")";
	}
	
	/**
	 * @since 1.5
	 * @see org.openmrs.OpenmrsObject#getId()
	 */
	public Integer getId() {
		
		return getPersonId();
	}
	
	/**
	 * @since 1.5
	 * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
	 */
	public void setId(Integer id) {
		setPersonId(id);
		
	}
	
}
