package resources;

import java.util.Iterator;
import java.util.List;

import utilities.Uri;

public class CodingImpl implements Coding {
	private Uri system; //The identification of the system that defines the meaning of the symbol in the code. Can be a simple list of enumerations, a list of codes with meanings or all the way to a complex semantic web such as SNOMED-CT, whether classification, terminology, or ontology
	private Code code; //A symbol in syntax defined by the system. The symbol may be a predefined code or an expression in a syntax defined by the coding system
	private String display; //A representation of the meaning of the code in the system, following the rules laid out by the system.

	public Uri getSystem() {
		return system;
	}
	
	public void setSystem(Uri system) {
		this.system = system;
	}
	
	public Code getCode() {
		return code;
	}
	
	public void setCode(Code code) {
		this.code = code;
	}
	
	public String getDisplay() {
		return display;
	}
	
	public void setDisplay(String display) {
		this.display = display;
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
