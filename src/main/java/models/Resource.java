package models;

import java.util.List;

import resources.Code;
import resources.CodeImpl;
import resources.Element;
import resources.ResourceType;

// Uses MySQL and Cassandra as backing data store
// Needs two implememations - one to play.db.jpa.Model, one to play.db.nosql.Model
public interface Resource extends Element {
	public ResourceType getResourceType();
	public Narrative getText();
	public void setText(Narrative text);
	public Code getLanguage();
	public void setLanguage(Code value);
	public String getLanguageSimple();
	public void setLanguageSimple(String value);
	public List<Resource> getContained();
}