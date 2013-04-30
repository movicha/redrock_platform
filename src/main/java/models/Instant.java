package models;

import java.util.Calendar;

import resources.Type;

public interface Instant extends Type {
	public Calendar getValue();
	public void setValue(Calendar value);
}
