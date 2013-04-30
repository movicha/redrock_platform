package models;

import java.util.Iterator;
import java.util.List;

import resources.CodeableConcept;
import resources.DateTime;
import resources.Element;
import resources.Extension;
import resources.Identifier;
import resources.ResourceReference;
import utilities.Uri;
import utilities.UriImpl;

// Uses MySQL as backing data store
// Needs play.db.jpa.Model implementation
public interface Device extends Resource {
	// if the device is running a FHIR server, the network address should be the
	// root URL from which a conformance statement may be retrieved
	public class Identity implements Element {
		/**
		 * The number assigned to this device by an authorised issuer of Device
		 * GITNs
		 */
		private String gtin;

		/**
		 * Lot number of manufacture
		 */
		private String lot;

		/**
		 * The serial number assigned by the organisation when the device was
		 * manufactured
		 */
		private String serialNumber;

		/**
		 * Date of expiry of this device (if applicable)
		 */
		private DateTime expiry;

		public String getGtin() { 
          return this.gtin;
        }

		public void setGtin(String value) { 
          this.gtin = value;
        }

		public String getLot() { 
          return this.lot;
        }

		public void setLot(String value) { 
          this.lot = value;
        }

		public String getSerialNumber() { 
          return this.serialNumber;
        }

		public void setSerialNumber(String value) { 
          this.serialNumber = value;
        }

		public DateTime getExpiry() { 
          return this.expiry;
        }

		public void setExpiry(DateTime value) { 
          this.expiry = value;
        }

		@Override
		public Iterator iterator() {
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
	}

	public CodeableConcept getType();

	public void setType(CodeableConcept value);

	public String getManufacturer();

	public void setManufacturer(String value);

	public String getManufacturerSimple();

	public void setManufacturerSimple(String value);

	public String getModel();

	public void setModel(String value);

	public String getModelSimple();

	public void setModelSimple(String value);

	public String getVersion();

	public void setVersion(String value);

	public String getVersionSimple();

	public void setVersionSimple(String value);

	public Identity getIdentity();

	public void setIdentity(Identity value);

	public ResourceReference getOwner();

	public void setOwner(ResourceReference value);

	public List<Identifier> getAssignedId();
	
	public ResourceReference getLocation();

	public void setLocation(ResourceReference value);

	public ResourceReference getPatient();

	public void setPatient(ResourceReference value);

	public List<Contact> getContact();

	public Uri getUrl();

	public void setUrl(Uri value);

	public Uri getUrlSimple();

	public void setUrlSimple(Uri value);
}