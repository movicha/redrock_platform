package models;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.Device.Identity;

import resources.Code;
import resources.CodeableConcept;
import resources.Extension;
import resources.Identifier;
import resources.ResourceReference;
import resources.ResourceType;
import utilities.Uri;
import utilities.UriImpl;

public class DeviceImpl implements Device {
	/**
	 * Describes what kind of device that this
	 */
	private CodeableConcept type;

	/**
	 * The name of the manufacturer
	 */
	private String manufacturer;

	/**
	 * The "model" - an identifier assigned by the manufacturer to identify the
	 * product by it's type. This number is shared by the all devices sold as
	 * the same type
	 */
	private String model;

	/**
	 * The version of the device, if the device has multiple releases under the
	 * same model, or if the device is software or carries firmware
	 */
	private String version;

	/**
	 * Universal Device Id fields
	 */
	private Identity identity;

	/**
	 * The organization that is responsible for the provision and ongoing
	 * maintenance of the device
	 */
	private ResourceReference owner;

	/**
	 * Identifiers assigned to this device by various organizations (unless
	 * other specific fields exist for them)
	 */
	private List<Identifier> assignedId = new ArrayList<Identifier>();

	/**
	 * The resource may be found in a literal location (i.e. GPS coordinates), a
	 * logical place (i.e. "in/with the patient"), or a coded location
	 */
	private ResourceReference location;

	/**
	 * If the resource is affixed to a person
	 */
	private ResourceReference patient;

	/**
	 * Contact details for an organization or a particular human that is
	 * responsible for the device
	 */
	private List<Contact> contact = new ArrayList<Contact>();

	/**
	 * A network address on which the device may be contacted directly
	 */
	private Uri url;
		
	public CodeableConcept getType() {
		return this.type;
	}

	public void setType(CodeableConcept value) {
		this.type = value;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String value) {
		this.manufacturer = value;
	}

	public String getManufacturerSimple() {
		return this.manufacturer == null ? null : this.manufacturer;
	}

	public void setManufacturerSimple(String value) {
		if (value == null)
			this.manufacturer = null;
		else {
			if (this.manufacturer == null)
				this.manufacturer = new String();
			this.manufacturer= value;
		}
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String value) {
		this.model = value;
	}

	public String getModelSimple() {
		return this.model == null ? null : this.model;
	}

	public void setModelSimple(String value) {
		if (value == null)
			this.model = null;
		else {
			if (this.model == null)
				this.model = new String();
			this.model= value;
		}
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String value) {
		this.version = value;
	}

	public String getVersionSimple() {
		return this.version == null ? null : this.version;
	}

	public void setVersionSimple(String value) {
		if (value == null)
			this.version = null;
		else {
			if (this.version == null)
				this.version = new String();
			this.version= value;
		}
	}

	public Identity getIdentity() {
		return this.identity;
	}

	public void setIdentity(Identity value) {
		this.identity = value;
	}

	public ResourceReference getOwner() {
		return this.owner;
	}

	public void setOwner(ResourceReference value) {
		this.owner = value;
	}

	public List<Identifier> getAssignedId() {
		return this.assignedId;
	}

	public ResourceReference getLocation() {
		return this.location;
	}

	public void setLocation(ResourceReference value) {
		this.location = value;
	}

	public ResourceReference getPatient() {
		return this.patient;
	}

	public void setPatient(ResourceReference value) {
		this.patient = value;
	}

	public List<Contact> getContact() {
		return this.contact;
	}

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

	// @Override
	public ResourceType getResourceType() {
		return ResourceType.Device;
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
