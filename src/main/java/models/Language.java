package models;

import resources.CodeableConcept;
import resources.Element;

// Uses MySQL and Cassandra as backing data store
// Needs two implememations - one to play.db.jpa.Model, one to play.db.nosql.Model
public interface Language extends Element {
}