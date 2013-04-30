package models;

import java.util.Iterator;
import java.util.List;

import resources.CodeableConcept;
import resources.Extension;

public class LanguageImpl implements Language {
	private CodeableConcept language; //The ISO-639-1 alpha 2 code in lower case for the language, optionally followed by a hyphen and the ISO-3166-1 alpha 2 code for the region in upper case. E.g. "en" for English, or "en-US" for American English versus "en-EN" for England English
	private CodeableConcept mode; //A value representing the person's method of expression of this language. Examples: expressed spoken, expressed written, expressed signed, received spoken, received written, received signed
	private CodeableConcept proficiencyLevel; //A code that describes how well the language is spoken
	private boolean preference; //Indicates whether or not the Person prefers this language (over other languages he masters up a certain level)

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
