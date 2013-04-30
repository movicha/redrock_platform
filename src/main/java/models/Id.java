package models;

import resources.Type;

public interface Id extends Type {
	public String getValue();

	public void setValue(String value);
}
