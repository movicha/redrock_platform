package models;

import java.util.*;

import resources.Code;
import resources.CodeableConcept;
import resources.DateTime;
import resources.ElementImpl;
import resources.Extension;
import resources.Identifier;
import resources.ResourceReference;
import resources.ResourceType;

/**
 * Describes the intention of how one or more providers intend to deliver care for a particular patient for a period of time, possibly limited to care for a specific condition or set of conditions.
 */
public class CarePlanImpl implements CarePlan {
	/**
	 * Unique identifier by which the care plan is known in different business contexts.
	 */
	private Identifier identifier;

	/**
	 * Identifies the patient/subject whose intended care is described by the plan.
	 */
	private ResourceReference patient;

	/**
	 * Indicates whether the plan is currently being acted upon, represents future intentions or is now just historical record.
	 */
	private Enum<CarePlanStatus> status;

	/**
	 * Indicates when the plan did (or is intended to) come into effect and end. 
	 */
	private Period period;

	/**
	 * Identifies the most recent date on which the plan has been revised.
	 */
	private DateTime modified;

	/**
	 * Identifies the problems/concerns/diagnoses/etc. whose management and/or mitigation are handled by this plan.
	 */
	private List<ResourceReference> concern = new ArrayList<ResourceReference>();

	/**
	 * Identifies all people and organizations who are expected to be involved in the care envisioned by this plan.
	 */
	private List<Participant> participant = new ArrayList<Participant>();

	/**
	 * Describes the intended objective(s) of carrying out the Care Plan.
	 */
	private String goal;

	/**
	 * Identifies a planned action to occur as part of the plan.  For example, a medication to be used, lab tests to perform, self-monitoring, education, etc.
	 */
	private List<Activity> activity = new ArrayList<Activity>();

	public Identifier getIdentifier() { 
		return this.identifier;
	}

	public void setIdentifier(Identifier value) { 
		this.identifier = value;
	}

	public ResourceReference getPatient() { 
		return this.patient;
	}

	public void setPatient(ResourceReference value) { 
		this.patient = value;
	}

	public Enum<CarePlanStatus> getStatus() { 
		return this.status;
	}

	public void setStatus(Enum<CarePlanStatus> value) { 
		this.status = value;
	}

	public CarePlanStatus getStatusSimple() { 
		return this.status == null ? null : this.status.valueOf(CarePlanStatus.class, this.status.name());
	}

	public void setStatusSimple(CarePlanStatus value) { 
		if (value == null)
			this.status = null;
		else
		  	this.status = value;
	}

	public Period getPeriod() { 
		return this.period;
	}

	public void setPeriod(Period value) { 
		this.period = value;
	}

	public DateTime getModified() { 
		return this.modified;
	}

	public void setModified(DateTime value) { 
		this.modified = value;
	}

	public String getModifiedSimple() { 
		return this.modified == null ? null : this.modified.getValue() ;
	}

	public void setModifiedSimple(String value) { 
		if (value == null)
			this.modified = null;
		else {
			if (this.modified == null)
				this.modified = new DateTime();
			this.modified.setValue(value);
		}
	}

	public List<ResourceReference> getConcern() { 
		return this.concern;
	}

	public List<Participant> getParticipant() { 
		return this.participant;
	}

	public String getGoal() { 
		return this.goal;
	}

	public void setGoal(String value) { 
		this.goal = value;
	}

	public String getGoalSimple() { 
		return this.goal == null ? null : this.goal;
	}

	public void setGoalSimple(String value) { 
		if (value == null)
		   this.goal = null;
		else {
			if (this.goal == null)
				this.goal = new String();
			this.goal= value;
		}
	}

	public List<Activity> getActivity() { 
		return this.activity;
	}

	@Override
	public ResourceType getResourceType() {
		return ResourceType.CarePlan;
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
