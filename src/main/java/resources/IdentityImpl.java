package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class IdentityImpl implements Identity {
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
	private Date expiry;
	
	/**
	 * Simple Date Formatter
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public String getGtin() {
		return this.gtin;
	}

	public void setGtin(String value) {
		this.gtin = value;
	}

	public String getGtinSimple() {
		return this.gtin == null ? null : this.gtin;
	}

	public void setGtinSimple(String value) {
		if (value == null)
			this.gtin = null;
		else {
			if (this.gtin == null)
				this.gtin = new String();
			this.gtin = value;
		}
	}

	public String getLot() {
		return this.lot;
	}

	public void setLot(String value) {
		this.lot = value;
	}

	public String getLotSimple() {
		return this.lot == null ? null : this.lot;
	}

	public void setLotSimple(String value) {
		if (value == null)
			this.lot = null;
		else {
			if (this.lot == null)
				this.lot = new String();
			this.lot = value;
		}
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String value) {
		this.serialNumber = value;
	}

	public String getSerialNumberSimple() {
		return this.serialNumber == null ? null : this.serialNumber;
	}

	public void setSerialNumberSimple(String value) {
		if (value == null)
			this.serialNumber = null;
		else {
			if (this.serialNumber == null)
				this.serialNumber = new String();
			this.serialNumber = value;
		}
	}

	public Date getExpiry() {
		return this.expiry;
	}

	public void setExpiry(Date value) {
		this.expiry = value;
	}

	public String getExpirySimple() {
		return this.expiry == null ? null : dateFormat.format(this.expiry);
	}

	public void setExpirySimple(String value) {
		if (value == null)
			this.expiry = null;
		else {
			if (this.expiry == null)
				this.expiry = new Date();
			try {
				this.expiry = dateFormat.parse(value);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

