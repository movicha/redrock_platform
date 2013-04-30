package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resources.CodeableConcept;
import resources.Coding;
import resources.DateTime;
import resources.Extension;
import resources.HumanName;

public class DemographicsImpl implements Demographics {

    /**
     * A name associated with the individual. 
     */
    private List<HumanName> name = new ArrayList<HumanName>();

    /**
     * A contact detail (e.g. a telephone number or an email address) by which the individual may be contacted. 
     */
    private List<Contact> telecom = new ArrayList<Contact>();

    /**
     * Administrative Gender - the gender that the patient is considered to have for administration / record keeping purposes
     */
    private Coding gender;

    /**
     * The birth date for the individual, to the degree of precision now
     */
    private DateTime birthDate;

    /**
     * Indicates if the individual is deceased or not
     */
    private Boolean deceased;

    /**
     * One or more addresses for the individual
     */
    private List<Address> address = new ArrayList<Address>();

    /**
     * This field contains a patient's marital (civil) status.
     */
    private CodeableConcept maritalStatus;

    /**
     * A language spoken by the person, with proficiency
     */
    private List<Language> language = new ArrayList<Language>();

    public List<HumanName> getName() { 
      return this.name;
    }

    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    public Coding getGender() { 
      return this.gender;
    }

    public void setGender(Coding value) { 
      this.gender = value;
    }

    public DateTime getBirthDate() { 
      return this.birthDate;
    }

    public void setBirthDate(DateTime value) { 
      this.birthDate = value;
    }

    public String getBirthDateSimple() { 
      return this.birthDate == null ? null : this.birthDate.getValue();
    }

    public void setBirthDateSimple(String value) { 
      if (value == null)
        this.birthDate = null;
      else {
        if (this.birthDate == null)
          this.birthDate = new DateTime();
        this.birthDate.setValue(value);
      }
    }

    public Boolean getDeceased() { 
      return this.deceased;
    }

    public void setDeceased(Boolean value) { 
      this.deceased = value;
    }

    public boolean getDeceasedSimple() { 
      return this.deceased == null ? null : this.deceased;
    }

    public void setDeceasedSimple(boolean value) { 
      if (value == false)
        this.deceased = null;
      else {
        if (this.deceased == null)
          this.deceased = new Boolean(false);
        this.deceased = value;
      }
    }

    public List<Address> getAddress() { 
      return this.address;
    }

    public CodeableConcept getMaritalStatus() { 
      return this.maritalStatus;
    }

    public void setMaritalStatus(CodeableConcept value) { 
      this.maritalStatus = value;
    }

    public List<Language> getLanguage() { 
      return this.language;
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
