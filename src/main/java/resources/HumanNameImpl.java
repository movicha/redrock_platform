package resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Period;
import resources.HumanName.NameUse;

public class HumanNameImpl implements HumanName {
	private Enum<NameUse> use; //Identifies the purpose for this name
	private String text; //a full text representation of the name
	private ArrayList<String> family; //Family name, this is the name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father. Array of NSStrings
	private ArrayList<String> given; //Given name. NOTE: Not to be called "first name" since given names do not always come first. Array of NSStrings
	private ArrayList<String> prefix; //Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that comes at the start of the name. Array of NSStrings
	private ArrayList<String> suffix; //Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that comes at the end of the name. Array of NSStrings
	private Period period; //Indicates the period of time when this name was valid for the named person.
	
	public Enum<NameUse> getUse() {
		return use;
	}
	
	public void setUse(Enum<NameUse> use) {
		this.use = use;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public ArrayList<String> getFamily() {
		return family;
	}
	
	public void setFamily(ArrayList<String> family) {
		this.family = family;
	}
	
	public ArrayList<String> getGiven() {
		return given;
	}
	
	public void setGiven(ArrayList<String> given) {
		this.given = given;
	}
	
	public ArrayList<String> getPrefix() {
		return prefix;
	}
	
	public void setPrefix(ArrayList<String> prefix) {
		this.prefix = prefix;
	}
	
	public ArrayList<String> getSuffix() {
		return suffix;
	}
	
	public void setSuffix(ArrayList<String> suffix) {
		this.suffix = suffix;
	}
	
	public Period getPeriod() {
		return period;
	}
	
	public void setPeriod(Period period) {
		this.period = period;
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
