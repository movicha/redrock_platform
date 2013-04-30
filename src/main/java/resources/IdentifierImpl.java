package resources;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import models.Period;

import utilities.Uri;
import utilities.UriImpl;

public class IdentifierImpl implements Identifier {
	/**
	 * Identifies the use for this identifier, if known
	 */
	private IdentifierUse use;

	/**
	 * A label for the identifier that can be displayed to a human so they can
	 * recognise the identifier
	 */
	private String label;

	/**
	 * Establishes the namespace in which set of possible id values is unique.
	 */
	private Uri system;

	/**
	 * The portion of the identifier typically displayed to the user and which
	 * is unique within the context of the system.
	 */
	private String id;

	/**
	 * Time period during which identifier was valid for use
	 */
	private Period period;

	/**
	 * Organisation that issued/manages the identifier
	 */
	private ResourceReference assigner;

	public IdentifierUse getUse() {
		return this.use;
	}

	public void setUse(IdentifierUse value) {
		this.use = value;
	}

	public IdentifierUse getUseSimple() {
		return this.use == null ? null : this.use;
	}

	public void setUseSimple(IdentifierUse value) {
		if (value == null)
			this.use = null;
		else {
			this.use = value;
		}
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String value) {
		this.label = value;
	}

	public String getLabelSimple() {
		return this.label == null ? null : this.label;
	}

	public void setLabelSimple(String value) {
		if (value == null)
			this.label = null;
		else {
			if (this.label == null)
				this.label = new String();
			this.label = value;
		}
	}

	public Uri getSystem() {
		return this.system;
	}

	public void setSystem(Uri value) {
		this.system = value;
	}

	public URI getSystemSimple() {
		return this.system == null ? null : this.system.getValue();
	}

	public void setSystemSimple(URI value) {
		if (value == null)
			this.system = null;
		else {
			if (this.system == null)
				this.system = new UriImpl();
			this.system.setValue(value);
		}
	}

	public String getId() {
		return this.id;
	}

	public void setId(String value) {
		this.id = value;
	}

	public String getIdSimple() {
		return this.id == null ? null : this.id;
	}

	public void setIdSimple(String value) {
		if (value == null)
			this.id = null;
		else {
			if (this.id == null)
				this.id = new String();
			this.id = value;
		}
	}

	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period value) {
		this.period = value;
	}

	public ResourceReference getAssigner() {
		return this.assigner;
	}

	public void setAssigner(ResourceReference value) {
		this.assigner = value;
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

	@Override
	public void setUse(Enum<IdentifierUse> enum1) {
		// TODO Auto-generated method stub
		
	}
}
