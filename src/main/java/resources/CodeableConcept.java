package resources;

import java.util.ArrayList;

// A concept that may be defined by a formal reference to a terminology or ontology or may be provided by text
public interface CodeableConcept extends Type {
	public ArrayList<Coding> getCoding();
	public void setCoding(ArrayList<Coding> coding);
	public String getText();
	public void setText(String text);
	public String getPrimary();
	public void setPrimary(String primary);
}