package models;

import java.util.Iterator;
import java.util.List;

import resources.CodeableConcept;
import resources.Coding;
import resources.DateTime;
import resources.Element;
import resources.Extension;
import resources.HumanName;
import resources.Type;

// Uses MySQL and Cassandra as backing data store
// Needs two implememations - one to play.db.jpa.Model, one to play.db.nosql.Model
public interface Demographics extends Type {
	public class Language implements Element {
		/**
		 * The ISO-639-1 alpha 2 code in lower case for the language, optionally
		 * followed by a hyphen and the ISO-3166-1 alpha 2 code for the region
		 * in upper case. E.g. "en" for English, or "en-US" for American English
		 * versus "en-EN" for England English
		 */
		private CodeableConcept language;

		/**
		 * A value representing the person's method of expression of this
		 * language. Examples: expressed spoken, expressed written, expressed
		 * signed, received spoken, received written, received signed
		 */
		private CodeableConcept mode;

		/**
		 * A code that describes how well the language is spoken
		 */
		private CodeableConcept proficiencyLevel;

		/**
		 * Indicates whether or not the Person prefers this language (over other
		 * languages he masters up a certain level)
		 */
		private Boolean preference;

		public CodeableConcept getLanguage() {
			return this.language;
		}

		public void setLanguage(CodeableConcept value) {
			this.language = value;
		}

		public CodeableConcept getMode() {
			return this.mode;
		}

		public void setMode(CodeableConcept value) {
			this.mode = value;
		}

		public CodeableConcept getProficiencyLevel() {
			return this.proficiencyLevel;
		}

		public void setProficiencyLevel(CodeableConcept value) {
			this.proficiencyLevel = value;
		}

		public Boolean getPreference() {
			return this.preference;
		}

		public void setPreference(Boolean value) {
			this.preference = value;
		}

		public boolean getPreferenceSimple() {
			return this.preference == null ? null : this.preference;
		}

		public void setPreferenceSimple(boolean value) {
			if (value == false)
				this.preference = null;
			else {
				if (this.preference == null)
					this.preference = new Boolean(false);
				this.preference = value;
			}
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

	public List<HumanName> getName();

	public List<Contact> getTelecom();

	public Coding getGender();

	public void setGender(Coding value);

	public DateTime getBirthDate();

	public void setBirthDate(DateTime value);

	public String getBirthDateSimple();

	public void setBirthDateSimple(String value);

	public Boolean getDeceased();

	public void setDeceased(Boolean value);

	public boolean getDeceasedSimple();

	public void setDeceasedSimple(boolean value);

	public List<Address> getAddress();

	public CodeableConcept getMaritalStatus();

	public void setMaritalStatus(CodeableConcept value);

	public List<Language> getLanguage();
}