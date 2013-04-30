package models;

import java.util.Iterator;
import java.util.List;

import resources.DateTime;
import resources.Extension;

public class PeriodImpl implements Period {
	private DateTime start; //The start of the period. The boundary is inclusive.
	private DateTime end; //The end of the period. If the high is missing, it means that the period is ongoing

	public DateTime getStart() {
		return start;
	}
	
	public void setStart(DateTime start) {
		this.start = start;
	}
	
	public DateTime getEnd() {
		return end;
	}
	
	public void setEnd(DateTime end) {
		this.end = end;
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
