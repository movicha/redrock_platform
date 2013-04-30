package resources;

import models.Period;

public interface HumanId extends Type {
	public enum IdentifierUse {
	    IdentifierUseUusual, // the identifier recommended for display and use in real-world interactions
	    IdentifierUseOfficial, // the identifier considered to be most trusted for the identification of this item
	    IdentifierUseTemp //A temporary identifier
	};
	
	public IdentifierUse getUse();
	public void setUse(IdentifierUse use);
	public String getLabel();
	public void setLabel(String label);
	public Identifier getIdentifier();
	public void setIdentifier(Identifier identifier);
	public Period getPeriod();
	public void setPeriod(Period period);
	public ResourceReference getAssigner();
	public void setAssigner(ResourceReference assigner);
}
