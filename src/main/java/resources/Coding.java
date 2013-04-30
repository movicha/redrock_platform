package resources;

import utilities.Uri;

//A reference to a code defined by a terminology system
public interface Coding extends Type {
	public Uri getSystem();
	public void setSystem(Uri system);
	public Code getCode();
	public void setCode(Code code);
	public String getDisplay();
	public void setDisplay(String display);
}
