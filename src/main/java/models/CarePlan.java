package models;

import java.util.*;

import resources.CodeableConcept;
import resources.DateTime;
import resources.Element;
import resources.EnumFactory;
import resources.Extension;
import resources.Identifier;
import resources.Quantity;
import resources.ResourceReference;
import resources.ResourceType;

/**
 * Describes the intention of how one or more providers intend to deliver care for a particular patient for a period of time, possibly limited to care for a specific condition or set of conditions.
 */
public interface CarePlan extends Resource {
	public enum CarePlanStatus {
		planned, // The plan is in development or awaiting use but is not yet intended to be acted upon.
		active,  // The plan is intended to be followed and used as part of patient care
		ended,   // The plan is no longer in use and is not expected to be followed or used in patient care
		Null;    // added to help the parsers
		public static CarePlanStatus fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
			    return null;
			if ("planned".equals(codeString))
				return planned;
			if ("active".equals(codeString))
				return active;
			if ("ended".equals(codeString))
				return ended;
			throw new Exception("Unknown CarePlanStatus code '"+codeString+"'");
        }

        public String toCode() {
        	switch (this) {
            	case planned: return "planned";
	            case active: return "active";
	            case ended: return "ended";
	            default: return "?";
        	}
        }
    }
	public enum CarePlanActivityCategory {
        diet,         // Plan for the patient to consume food of a specified nature
        drug,         // Plan for the patient to consume/receive a drug, vaccine or other product
        encounter,    // Plan to meet or communicate with the patient (in-patient, out-patient, phone call, etc.)
        observation,  // Plan to capture information about a patient (vitals, labs, diagnostic images, etc.)
        procedure,    // Plan to modify the patient in some way (surgery, physio-therapy, education, counselling, etc.)
        supply,       // Plan to provide something to the patient (medication, medical supply, etc.)
        other,        // Some other form of action
        Null;         // added to help the parsers

        public static CarePlanActivityCategory fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
	        if ("diet".equals(codeString))
		          return diet;
	        if ("drug".equals(codeString))
		          return drug;
	        if ("encounter".equals(codeString))
		          return encounter;
	        if ("observation".equals(codeString))
		          return observation;
	        if ("procedure".equals(codeString))
		          return procedure;
	        if ("supply".equals(codeString))
		          return supply;
	        if ("other".equals(codeString))
		          return other;
	        throw new Exception("Unknown CarePlanActivityCategory code '"+codeString+"'");
        }

        public String toCode() {
        	switch (this) {
	            case diet: return "diet";
	            case drug: return "drug";
	            case encounter: return "encounter";
	            case observation: return "observation";
	            case procedure: return "procedure";
	            case supply: return "supply";
	            case other: return "other";
	            default: return "?";
        	}
        }
    }
	public class CarePlanStatusEnumFactory implements EnumFactory {
    	public Enum<?> fromCode(String codeString) throws Exception {
      		if (codeString == null || "".equals(codeString))
            	if (codeString == null || "".equals(codeString))
                	return null;
        	if ("planned".equals(codeString))
          		return CarePlanStatus.planned;
        	if ("active".equals(codeString))
          		return CarePlanStatus.active;
        	if ("ended".equals(codeString))
          		return CarePlanStatus.ended;
        	throw new Exception("Unknown CarePlanStatus code '"+codeString+"'");
        }
    
		public String toCode(Enum<?> code) throws Exception {
      		if (code == CarePlanStatus.planned)
        		return "planned";
      		if (code == CarePlanStatus.active)
        		return "active";
      		if (code == CarePlanStatus.ended)
        		return "ended";
      		return "?";
      	}
    }

	public class CarePlanActivityCategoryEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("diet".equals(codeString))
				return CarePlanActivityCategory.diet;
			if ("drug".equals(codeString))
				return CarePlanActivityCategory.drug;
			if ("encounter".equals(codeString))
				return CarePlanActivityCategory.encounter;
			if ("observation".equals(codeString))
				return CarePlanActivityCategory.observation;
			if ("procedure".equals(codeString))
				return CarePlanActivityCategory.procedure;
			if ("supply".equals(codeString))
				return CarePlanActivityCategory.supply;
			if ("other".equals(codeString))
				return CarePlanActivityCategory.other;
			throw new Exception("Unknown CarePlanActivityCategory code '"+codeString+"'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == CarePlanActivityCategory.diet)
				return "diet";
			if (code == CarePlanActivityCategory.drug)
				return "drug";
			if (code == CarePlanActivityCategory.encounter)
				return "encounter";
			if (code == CarePlanActivityCategory.observation)
				return "observation";
			if (code == CarePlanActivityCategory.procedure)
				return "procedure";
			if (code == CarePlanActivityCategory.supply)
				return "supply";
			if (code == CarePlanActivityCategory.other)
				return "other";
			return "?";
		}
    }

    public class Participant implements Element {
        /**
         * Indicates specific responsibility of an individual within the care plan.  E.g. "Primary physician", "Team coordinator", "Caregiver", etc.
         */
        private CodeableConcept role;

        /**
         * The specific person or organization who is participating/expected to participate in the care plan.
         */
        private ResourceReference member;

        public CodeableConcept getRole() { 
			return this.role;
        }

        public void setRole(CodeableConcept value) { 
			this.role = value;
        }

        public ResourceReference getMember() { 
			return this.member;
        }

        public void setMember(ResourceReference value) { 
			this.member = value;
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

    public class Activity implements Element {
        /**
         * High-level categorization of the type of activity in a care plan.
         */
        private Enum<CarePlanActivityCategory> category;

        /**
         * Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of encounter.
         */
        private CodeableConcept code;

        /**
         * If true, indicates that the described activity is one that must NOT be engaged in when following the plan.
         */
        private Boolean prohibited;

        /**
         * The timing or frequency upon which the described activity is to occur.
         */
        private Schedule schedule;

        /**
         * Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.
         */
        private ResourceReference location;

        /**
         * Identifies who's expected to be involved in the activity.
         */
        private List<ResourceReference> performer = new ArrayList<ResourceReference>();

        /**
         * Identifies the food, drug or other product being consumed or supplied in the activity.
         */
        private ResourceReference product;

        /**
         * Identifies the quantity expected to be consumed in a given day.
         */
        private Quantity dailyAmount;

        /**
         * Identifies the quantity expected to be supplied.
         */
        private Quantity quantity;

        /**
         * This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
         */
        private String details;

        /**
         * Resources resulting from the plan, such as drug prescriptions, encounter records, appointments, etc.
         */
        private List<ResourceReference> action = new ArrayList<ResourceReference>();

        public Enum<CarePlanActivityCategory> getCategory() { 
			return this.category;
        }

        public void setCategory(Enum<CarePlanActivityCategory> value) { 
			this.category = value;
        }

        public CarePlanActivityCategory getCategorySimple() { 
			return this.category == null ? null : this.category.valueOf(CarePlanActivityCategory.class, this.category.name());
        }

        public void setCategorySimple(CarePlanActivityCategory value) { 
			if (value == null)
				this.category = null;
			else {
				this.category = value;
			}
        }

        public CodeableConcept getCode() { 
			return this.code;
        }

        public void setCode(CodeableConcept value) { 
			this.code = value;
        }

        public Boolean getProhibited() { 
			return this.prohibited;
        }

        public void setProhibited(Boolean value) { 
			this.prohibited = value;
        }

        public boolean getProhibitedSimple() { 
			return this.prohibited == null ? null : this.prohibited;
        }

        public void setProhibitedSimple(boolean value) { 
			if (value == false)
				this.prohibited = null;
			else 
				this.prohibited= value;
			}

        public Schedule getSchedule() { 
			return this.schedule;
        }

        public void setSchedule(Schedule value) { 
			this.schedule = value;
        }

        public ResourceReference getLocation() { 
			return this.location;
        }

        public void setLocation(ResourceReference value) { 
			this.location = value;
        }

        public List<ResourceReference> getPerformer() { 
			return this.performer;
        }

        public ResourceReference getProduct() { 
			return this.product;
        }

        public void setProduct(ResourceReference value) { 
			this.product = value;
        }

        public Quantity getDailyAmount() { 
			return this.dailyAmount;
        }

        public void setDailyAmount(Quantity value) { 
			this.dailyAmount = value;
        }

        public Quantity getQuantity() { 
			return this.quantity;
        }

        public void setQuantity(Quantity value) { 
			this.quantity = value;
        }

        public String getDetails() { 
			return this.details;
        }

        public void setDetails(String value) { 
			this.details = value;
        }

        public String getDetailsSimple() { 
			return this.details == null ? null : this.details;
        }

        public void setDetailsSimple(String value) { 
			if (value == null)
				this.details = null;
			else {
				if (this.details == null)
					this.details = new String();
				this.details= value;
			}
        }

        public List<ResourceReference> getAction() { 
        	return this.action;
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

	public Identifier getIdentifier();
	public void setIdentifier(Identifier value);
	public ResourceReference getPatient();
	public void setPatient(ResourceReference value);
	public Enum<CarePlanStatus> getStatus();
	public void setStatus(Enum<CarePlanStatus> value);
	public CarePlanStatus getStatusSimple();
	public void setStatusSimple(CarePlanStatus value);
	public Period getPeriod();
	public void setPeriod(Period value);
	public DateTime getModified();
	public void setModified(DateTime value);
	public String getModifiedSimple();
	public void setModifiedSimple(String value);
	public List<ResourceReference> getConcern();
	public List<Participant> getParticipant();
	public String getGoal();
	public void setGoal(String value);
	public String getGoalSimple();
	public void setGoalSimple(String value);
	public List<Activity> getActivity();
	public ResourceType getResourceType();
}
