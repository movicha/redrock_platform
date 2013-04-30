package resources;

import java.util.Iterator;
import java.util.List;

import models.Period;
import resources.HumanId.IdentifierUse;

public class HumanIdImpl implements HumanId {
	private IdentifierUse use; //Identifies the use for this identifier, if known
	private String label; //A label for the identifier that can be displayed to a human so they can recognise the identifier
	private Identifier identifier; //The identifier itself
	private Period period; //Time period during which identifier was valid for use
	private ResourceReference assigner; //Organisation that issued/manages the identifier
	
	public IdentifierUse getUse() {
		return use;
	}

	public void setUse(IdentifierUse use) {
		this.use = use;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Identifier getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}
	
	public Period getPeriod() {
		return period;
	}
	
	public void setPeriod(Period period) {
		this.period = period;
	}
	
	public ResourceReference getAssigner() {
		return assigner;
	}
	
	public void setAssigner(ResourceReference assigner) {
		this.assigner = assigner;
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
