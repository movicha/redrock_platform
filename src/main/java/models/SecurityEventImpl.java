package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resources.Code;
import resources.Extension;
import resources.ResourceType;

public class SecurityEventImpl implements SecurityEvent {
	/**
	 * Identifies the name, action type, time, and disposition of the audited
	 * event
	 */
	private Event event;

	/**
	 * A person, a hardware device or software process
	 */
	private List<Participant> participant = new ArrayList<Participant>();

	/**
	 * Application systems and processes
	 */
	private List<Source> source = new ArrayList<Source>();

	/**
	 * Specific instances of data or objects that have been accessed
	 */
	private List<Object> object = new ArrayList<Object>();

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event value) {
		this.event = value;
	}

	public List<Participant> getParticipant() {
		return this.participant;
	}

	public List<Source> getSource() {
		return this.source;
	}

	public List<Object> getObject() {
		return this.object;
	}

	@Override
	public ResourceType getResourceType() {
		return ResourceType.SecurityEvent;
	}

	@Override
	public Narrative getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(Narrative text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Code getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLanguage(Code value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLanguageSimple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLanguageSimple(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Resource> getContained() {
		// TODO Auto-generated method stub
		return null;
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