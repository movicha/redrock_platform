package models;

import resources.DateTime;
import resources.Type;

// Uses MySQL and Cassandra as backing data store
// Needs two implememations - one to play.db.jpa.Model, one to play.db.nosql.Model
public interface Period extends Type {
	public DateTime getStart();
	public void setStart(DateTime start);
	public DateTime getEnd();
	public void setEnd(DateTime end);
}
