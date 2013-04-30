package models;

import java.util.Iterator;
import java.util.List;

import resources.Extension;

public class ContactImpl implements Contact {
	/**
	 * What kind of contact this is - what communications system is required to
	 * make use of the contact
	 */
	private Enum<ContactSystem> system;

	/**
	 * The actual contact details, in a form that is meaningful to the
	 * designated communication system (i.e. phone number or email address).
	 */
	private String value;

	/**
	 * Identifies the context for the address
	 */
	private Enum<ContactUse> use;

	/**
	 * Time period when the contact was/is in use
	 */
	private Period period;

	public Enum<ContactSystem> getSystem() {
		return this.system;
	}

	public void setSystem(Enum<ContactSystem> value) {
		this.system = value;
	}

	public ContactSystem getSystemSimple() {
		return this.system == null ? null : this.system.valueOf(ContactSystem.class, this.system.name());
	}

	public void setSystemSimple(ContactSystem value) {
		if (value == null)
			this.system = null;
		else
			this.system = value;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueSimple() {
		return this.value == null ? null : this.value;
	}

	public void setValueSimple(String value) {
		if (value == null)
			this.value = null;
		else {
			if (this.value == null)
				this.value = new String();
			this.value = value;
		}
	}

	public Enum<ContactUse> getUse() {
		return this.use;
	}

	public void setUse(Enum<ContactUse> value) {
		this.use = value;
	}

	public ContactUse getUseSimple() {
		return this.use == null ? null : this.use.valueOf(ContactUse.class, this.use.name());
	}

	public void setUseSimple(ContactUse value) {
		if (value == null)
			this.use = null;
		else
			this.use = value;

	}

	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period value) {
		this.period = value;
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
