package models;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import resources.Extension;

public class InstantImpl implements Instant {
	private Calendar value;

	public Calendar getValue() {
		return value;
	}

	public void setValue(Calendar value) {
		this.value = value;
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
