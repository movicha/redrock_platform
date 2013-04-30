package resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CodeableConceptImpl implements CodeableConcept {
	private ArrayList<Coding> coding; //A reference to a code defined by a terminology system. Contains "coding" objects only.
	private String text; //A human language representation of the concept as seen/selected/uttered by the user who entered the data and/or which represents the intended meaning of the user or concept
	private String primary; //Indicates which of the codes in the codings was chosen by a user, if one was chosen directly

	public ArrayList<Coding> getCoding() {
		return coding;
	}
	
	public void setCoding(ArrayList<Coding> coding) {
		this.coding = coding;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getPrimary() {
		return primary;
	}
	
	public void setPrimary(String primary) {
		this.primary = primary;
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
