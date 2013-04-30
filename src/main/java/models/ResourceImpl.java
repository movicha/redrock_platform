package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import resources.Code;
import resources.CodeImpl;
import resources.ResourceType;

public abstract class ResourceImpl implements Resource {

	public abstract ResourceType getResourceType();

	/**
	 * Text summary of resource, for human interpretation
	 */
	private Narrative text;

	/**
	 * The primary/base human language of the content. The value can be any
	 * valid value for xml:lang
	 */
	private Code language;

	private List<Resource> contained = new ArrayList<Resource>();

	public Narrative getText() {
		return text;
	}

	public void setText(Narrative text) {
		this.text = text;
	}

	public Code getLanguage() {
		return this.language;
	}

	public void setLanguage(Code value) {
		this.language = value;
	}

	public String getLanguageSimple() {
		return this.language == null ? null : this.language.getCodeText();
	}

	public void setLanguageSimple(String value) {
		if (value == null)
			this.language = null;
		else {
			if (this.language == null)
				this.language = new CodeImpl();
			this.language.setCodeText(value);
		}
	}

	public List<Resource> getContained() {
		return contained;
	}
}